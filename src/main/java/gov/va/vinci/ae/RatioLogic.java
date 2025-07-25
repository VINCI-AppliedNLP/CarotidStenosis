package gov.va.vinci.ae;

import gov.va.vinci.leo.AnnotationLibrarian;
import gov.va.vinci.leo.ae.LeoBaseAnnotator;
import gov.va.vinci.leo.descriptors.LeoTypeSystemDescription;
import gov.va.vinci.leo.sentence.types.AnchoredSentence;
import gov.va.vinci.types.*;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import java.util.ArrayList;

//import gov.va.vinci.leo.context.types.TermContext;

public class RatioLogic extends LeoBaseAnnotator {

    /*@Override
    public void initialize(UimaContext context) throws ResourceInitializationException {
        super.initialize(context);
    }
*/
    @Override
    public void annotate(JCas aJCas) throws AnalysisEngineProcessException {
        FSIterator<Annotation> patternit = this.getAnnotationListForType(aJCas, Ratio_Pattern.class.getCanonicalName());
        while (patternit.hasNext()) try {
            Ratio_Pattern current_pattern = (Ratio_Pattern) patternit.next();
            if (current_pattern != null) {
                ArrayList<Annotation> valueList = new ArrayList<Annotation>();
                ArrayList<Annotation> termList = new ArrayList<Annotation>();
                ArrayList<Annotation> desciptorList = new ArrayList<Annotation>();
                ArrayList<Annotation> lateralityList = new ArrayList<Annotation>();
                ArrayList<Annotation> qualifierList = new ArrayList<Annotation>();
                ArrayList<Annotation> sectionList = new ArrayList<Annotation>();


                //ArrayList<AnnotationPatternType> LPMList = new ArrayList<AnnotationPatternType>();

                //Respiration concepts
                //TODO Add the percent descriptor here as well
                valueList.addAll(AnnotationLibrarian.getAllOverlappingAnnotationsOfType(current_pattern, Ratio_Value.type, false));
                termList.addAll(AnnotationLibrarian.getAllOverlappingAnnotationsOfType(current_pattern, Ratio_Term.type, false));
                desciptorList.addAll(AnnotationLibrarian.getAllOverlappingAnnotationsOfType(current_pattern, Percent_Descriptor.type, false));
                lateralityList.addAll(AnnotationLibrarian.getAllOverlappingAnnotationsOfType(current_pattern, Laterality.type, false));

                qualifierList.addAll(AnnotationLibrarian.getAllOverlappingAnnotationsOfType(current_pattern, Ratio_Qualifier.type, false));
                sectionList.addAll(AnnotationLibrarian.getAllOverlappingAnnotationsOfType(current_pattern, Section.type, false));

                //Get complete extraction
                current_pattern.setPattern_Text(current_pattern.getCoveredText());
                //TODO get ratio categories as well ICA/CCA ICA/ECA etc.

                if (termList.size() > 0) {
                    Annotation rterm = termList.get(0);
                    current_pattern.setRatio_Term(((Ratio_Term) rterm).getCoveredText());
                    if (((Ratio_Term) rterm).getConcept() == "Exclude" || ((Ratio_Term) rterm).getConcept() == "Ratio_Diastolic"){
                        current_pattern.setRatio_Descriptor("Exclude");
                    }
                }
                /*
                      if(p.getTypeIndexID()==ABIPatternLeftTemplate.type){
              ABI_score c = (ABI_score) p.getAnchor();
              value = c.getCoveredText();
              laterality_value = "Left";
            }
                 */

                if (valueList.size() > 0) {
                    Annotation ratio = valueList.get(0);
                    //If the pattern has a right/left tabular pattern then it will have an anchor/target.  Currently setting all anchors to right
                    if (current_pattern.getAnchor() != null){
                        Annotation ratio1 = current_pattern.getAnchor();
                        current_pattern.setRatio_1(ratio1.getCoveredText());
                        current_pattern.setLaterality("Right_Left_Template");
                        if (current_pattern.getTarget() != null){
                            Annotation ratio2 = current_pattern.getTarget();
                            current_pattern.setRatio_2(ratio2.getCoveredText());

                        }

                    }
                    //If the value is one of the free text cases set to int here
                    //          'one',
                    //                'two',
                    if(((Ratio_Value) ratio).getCoveredText().equalsIgnoreCase("one")) {
                        current_pattern.setRatio_1("1");
                    } else if (((Ratio_Value) ratio).getCoveredText().equalsIgnoreCase("two")) {
                        current_pattern.setRatio_1("2");
                    } else {
                    current_pattern.setRatio_1(((Ratio_Value) ratio).getCoveredText());
                 }}
                if (desciptorList.size() > 0) {
                    Annotation rdescriptor = desciptorList.get(0);
                    current_pattern.setRatio_Descriptor(((Percent_Descriptor) rdescriptor).getConcept());
                }

                if (sectionList.size() > 0) {
                    Annotation sec = sectionList.get(0);
                    current_pattern.setSection(((gov.va.vinci.types.Section) sec).getSectionHeaderText());
                    //Delete instances found in irrelevant sections
                    // if( ((Section) sec).getSectionHeaderText().equalsIgnoreCase("Medications")){
                   //  todelete.add(current_cont);
                   // }

                }

                //If the laterality isn't already set earlier by the anchor/target logic, then apply laterality here by
                // 1st applying it in the pattern
                // 2nd applying it if overlapped by a laterality "section"
                // 3rd/finally by attaching the closest laterality to the left and found in the same section
                if (current_pattern.getLaterality() == null) {
                    if (lateralityList.size() > 0) {
                        Annotation rlaterality = lateralityList.get(0);
                        current_pattern.setLaterality(((Laterality) rlaterality).getConcept());
                    } else if (current_pattern.getSection() == "stenosis_laterality_left") {
                        current_pattern.setLaterality("Left");
                        //current_pattern.setLaterality_Term("laterality_section");

                    } else if (current_pattern.getSection() == "stenosis_laterality_right") {
                        current_pattern.setLaterality("Right");
                        //current_pattern.setLaterality_Term("laterality_section");
                    }else if (sectionList.size() > 0) { // if there is no value in the pattern, look in the sentence
                        Annotation sect = sectionList.get(0);
                        // use an array list to get the value closest to the anchor term - First take the closest left, otherwise take the closest right
                        ArrayList<Annotation> lateralityblist = new ArrayList<Annotation>();
                        lateralityblist.addAll(AnnotationLibrarian.getAllOverlappingAnnotationsOfType(sect, Laterality.type, false));
                        ArrayList<Annotation> closest_l_values = (ArrayList<Annotation>) AnnotationLibrarian.getPreviousClosestAnnotations(current_pattern, lateralityblist);
                        if (closest_l_values.size() > 0)  {
                            Annotation left = closest_l_values.get(0);
                            current_pattern.setLaterality(((Laterality) left).getConcept());
                        }


                    }

                }


                if (qualifierList.size() > 0) {
                    Annotation rqualifier = qualifierList.get(0);
                    current_pattern.setRatio_Qualifier(((Ratio_Qualifier) rqualifier).getConcept());
                }




            }
/*
headingtype = leftright
headingtype = right;left
*/
 }   catch (CASException e) {
            logger.error(e.getStackTrace());

            //

        }
        //Sections to potentially Remove
       /* for(Annotation a:todelete){
            a.removeFromIndexes();
        } */

    }
    @Override
    public LeoTypeSystemDescription getLeoTypeSystemDescription() {
        return new LeoTypeSystemDescription();
    }
}
