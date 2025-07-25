package gov.va.vinci.ae;

import gov.va.vinci.leo.AnnotationLibrarian;
import gov.va.vinci.leo.ae.LeoBaseAnnotator;
import gov.va.vinci.leo.annotationpattern.AnnotationPattern;
import gov.va.vinci.leo.annotationpattern.types.AnnotationPatternType;
import gov.va.vinci.leo.descriptors.LeoTypeSystemDescription;
import gov.va.vinci.leo.sentence.types.AnchoredSentence;
import gov.va.vinci.leo.window.types.Window;
import gov.va.vinci.types.*;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import java.util.ArrayList;

//import gov.va.vinci.leo.context.types.TermContext;

public class StenosisLogic extends LeoBaseAnnotator {

    /*@Override
    public void initialize(UimaContext context) throws ResourceInitializationException {
        super.initialize(context);
    }
*/
    @Override
    public void annotate(JCas aJCas) throws AnalysisEngineProcessException {
        FSIterator<Annotation> patternit = this.getAnnotationListForType(aJCas, Stenosis_Pattern.class.getCanonicalName());
        while (patternit.hasNext()) try {
            Stenosis_Pattern current_pattern = (Stenosis_Pattern) patternit.next();
            if (current_pattern != null) {
                ArrayList<Annotation> severityTermList = new ArrayList<Annotation>();
                ArrayList<Annotation> severityPatternList = new ArrayList<Annotation>();
                ArrayList<Annotation> LateralityList = new ArrayList<Annotation>();
                ArrayList<Annotation> RatioList = new ArrayList<Annotation>();
                ArrayList<Annotation> SentenceList = new ArrayList<Annotation>();
                ArrayList<Annotation> sectionList = new ArrayList<Annotation>();
                ArrayList<Annotation> anatomypatternList = new ArrayList<Annotation>();
                ArrayList<Annotation> diseasepatternList = new ArrayList<Annotation>();
                ArrayList<Annotation> ProcedureList = new ArrayList<Annotation>();

                //Respiration concepts
                anatomypatternList.addAll(AnnotationLibrarian.getAllOverlappingAnnotationsOfType(current_pattern, Anatomy_Pattern.type, false));
                diseasepatternList.addAll(AnnotationLibrarian.getAllOverlappingAnnotationsOfType(current_pattern, Disease_Pattern.type, false));
                severityTermList.addAll(AnnotationLibrarian.getAllOverlappingAnnotationsOfType(current_pattern, Severity_Term.type, false));
                severityPatternList.addAll(AnnotationLibrarian.getAllOverlappingAnnotationsOfType(current_pattern, Severity_Pattern.type, false));
                LateralityList.addAll(AnnotationLibrarian.getAllOverlappingAnnotationsOfType(current_pattern, Laterality.type, false));
                RatioList.addAll(AnnotationLibrarian.getAllOverlappingAnnotationsOfType(current_pattern, Ratio_Pattern.type, false));
                SentenceList.addAll(AnnotationLibrarian.getAllOverlappingAnnotationsOfType(current_pattern, AnchoredSentence.type, false));
                ProcedureList.addAll(AnnotationLibrarian.getAllOverlappingAnnotationsOfType(current_pattern, Procedure.type, false));
                sectionList.addAll(AnnotationLibrarian.getAllOverlappingAnnotationsOfType(current_pattern, Section.type, false));


                //Get complete extraction
                current_pattern.setPattern_Text(current_pattern.getCoveredText());

                //TODO Likely just want to normalize complete vessel names rather than breaking apart attributes.  TBD
                if (anatomypatternList.size() > 0) {
                    Annotation anatomypattern = anatomypatternList.get(0);
                    // if(Anatomy_Pattern.get)
                    if (((Anatomy_Pattern) anatomypattern).getVessel_Type() != null) {
                        current_pattern.setAnatomy_Type(((Anatomy_Pattern) anatomypattern).getVessel_Type());
                        current_pattern.setAnatomy_Term(((Anatomy_Pattern) anatomypattern).getVessel_Term());
                    }
                    if (((Anatomy_Pattern) anatomypattern).getLocation_Type() != null) {
                        current_pattern.setAnatomy_Attribute(((Anatomy_Pattern) anatomypattern).getLocation_Type());
                        current_pattern.setAnatomy_Attribute_Term(((Anatomy_Pattern) anatomypattern).getLocation_Term());
                    }

                }
                else if (SentenceList.size()> 0) {
                    Annotation sent = SentenceList.get(0);
                    ArrayList<Annotation> blist = new ArrayList<Annotation>();
                    ArrayList<Annotation> bList = (ArrayList<Annotation>) AnnotationLibrarian.getAllCoveredAnnotationsOfType(sent, Anatomy_Pattern.type, false);
                    if (bList.size() > 0) {
                        Annotation anatomypattern = bList.get(0);
                        // if(Anatomy_Pattern.get)
                        if (((Anatomy_Pattern) anatomypattern).getVessel_Type() != null) {
                            current_pattern.setAnatomy_Type(((Anatomy_Pattern) anatomypattern).getVessel_Type());
                            current_pattern.setAnatomy_Term(((Anatomy_Pattern) anatomypattern).getVessel_Term());
                        }
                        if (((Anatomy_Pattern) anatomypattern).getLocation_Type() != null) {
                            current_pattern.setAnatomy_Attribute(((Anatomy_Pattern) anatomypattern).getLocation_Type());
                            current_pattern.setAnatomy_Attribute_Term(((Anatomy_Pattern) anatomypattern).getLocation_Term());
                        }

                    }
                }





                //Severity term and disease in the pattern, otherwise look in the severity pattern, otherwise finally look in the window
                if (diseasepatternList.size() > 0) {
                    Annotation diseasePattern = diseasepatternList.get(0);
                    current_pattern.setDisease_Type(((Disease_Pattern) diseasePattern).getDisease_Type());
                    current_pattern.setDisease_Term(((Disease_Pattern) diseasePattern).getDisease_Term());
                    current_pattern.setSeverity_Term_Type(((Disease_Pattern) diseasePattern).getSeverity_Type());
                    current_pattern.setSeverity_Term(((Disease_Pattern) diseasePattern).getSeverity_Term());
                    current_pattern.setPercent_1(((Disease_Pattern) diseasePattern).getPercent_1());
                    current_pattern.setPercent_2(((Disease_Pattern) diseasePattern).getPercent_2());
                    current_pattern.setPercent_Descriptor(((Disease_Pattern) diseasePattern).getPercent_Descriptor());

                }
                //TODO check on this redundancy.  Severity is set in the disease pattern, but the disease pattern does not always contain severity
                // IDEALLY there will be a complete disease phrase, but that's not always the case so we are also allowing severity patterns on their own here
                if (severityPatternList.size() > 0){
                    Annotation severityPattern = severityPatternList.get(0);
                    current_pattern.setSeverity_Term_Type(((Severity_Pattern) severityPattern).getSeverity_Type());
                    current_pattern.setSeverity_Term(((Severity_Pattern) severityPattern).getSeverity_Term());
                    current_pattern.setPercent_1(((Severity_Pattern) severityPattern).getPercent_1());
                    current_pattern.setPercent_2(((Severity_Pattern) severityPattern).getPercent_2());
                    current_pattern.setPercent_Descriptor(((Severity_Pattern) severityPattern).getPercent_Descriptor());
                }
                //If neither of those patterns exist, then we search in the window (but only populate if the term was null)
                if (SentenceList.size() > 0) { // if there is no value in the pattern, look in the sentence
                    Annotation sent = SentenceList.get(0);
                    // use an array list to get the value closest to the anchor term

                    ArrayList<Annotation> bList = (ArrayList<Annotation>) AnnotationLibrarian.getAllCoveredAnnotationsOfType(sent, Severity_Pattern.type, false);
                    ArrayList<Annotation> closest_r_values = (ArrayList<Annotation>) AnnotationLibrarian.getNextClosestAnnotations(((AnchoredSentence)sent).getAnchor(), bList);
                    ArrayList<Annotation> closest_l_values = (ArrayList<Annotation>) AnnotationLibrarian.getPreviousClosestAnnotations(((AnchoredSentence)sent).getAnchor(), bList);

                    //Use the value found to the right first if it exists, else use the value to the left

                    //Set severity terms
                    if (closest_r_values.size() > 0 & current_pattern.getSeverity_Term() == null)  {
                        Annotation v = closest_r_values.get(0);
                        current_pattern.setSeverity_Term_Type(((Severity_Pattern) v).getSeverity_Type());
                        current_pattern.setSeverity_Term(((Severity_Pattern) v).getSeverity_Term());
                    }     else if (closest_l_values.size() > 0 & current_pattern.getSeverity_Term() == null)  {
                        Annotation v = closest_l_values.get(0);
                        current_pattern.setSeverity_Term_Type(((Severity_Pattern) v).getSeverity_Type());
                        current_pattern.setSeverity_Term(((Severity_Pattern) v).getSeverity_Term());

                    }
                    //Set percentages
                    if (closest_r_values.size() > 0 & current_pattern.getPercent_1() == null)  {
                        Annotation v = closest_r_values.get(0);
                        current_pattern.setPercent_1(((Severity_Pattern) v).getPercent_1());
                        current_pattern.setPercent_2(((Severity_Pattern) v).getPercent_2());
                        current_pattern.setPercent_Descriptor(((Severity_Pattern) v).getPercent_Descriptor());
                    }     else if (closest_l_values.size() > 0 & current_pattern.getPercent_1() == null)  {
                        Annotation v = closest_l_values.get(0);
                        current_pattern.setPercent_1(((Severity_Pattern) v).getPercent_1());
                        current_pattern.setPercent_2(((Severity_Pattern) v).getPercent_2());
                        current_pattern.setPercent_Descriptor(((Severity_Pattern) v).getPercent_Descriptor());
                    }


                }

                //Procedure if in pattern, else in window
                if (ProcedureList.size() > 0) {
                    Annotation proc = ProcedureList.get(0);
                    current_pattern.setProcedure_Type(((Procedure) proc).getConcept());
                    current_pattern.setProcedure_Term(( proc).getCoveredText());
                } else if (SentenceList.size()> 0) {
                    Annotation sent = SentenceList.get(0);
                    ArrayList<Annotation> blist = new ArrayList<Annotation>();
                    ArrayList<Annotation> bList = (ArrayList<Annotation>) AnnotationLibrarian.getAllCoveredAnnotationsOfType(sent, Procedure.type, false);
                    if (bList.size() > 0) {
                        Annotation procedureb = bList.get(0);
                        current_pattern.setProcedure_Type(((Procedure) procedureb).getConcept());
                            current_pattern.setProcedure_Term(((Procedure) procedureb).getCoveredText());
                        }

                }


                /*
                Laterality takes the pattern instance is found,
                else it looks left within the anchored sentence first, then right

                else, plural ICA anatomy is used to indicate bilateral instances
                finally if nothing else, the sectionizer is used for explicit laterality sections
                 */

                if (LateralityList.size() > 0) {
                    Annotation lat = LateralityList.get(0);
                    current_pattern.setLaterality_Type(((Laterality) lat).getConcept());
                    current_pattern.setLaterality_Term(((Laterality) lat).getCoveredText());
                }else if (SentenceList.size() > 0) { // if there is no value in the pattern, look in the sentence
                    Annotation sent = SentenceList.get(0);
                    // use an array list to get the value closest to the anchor term
                    ArrayList<Annotation> bList = (ArrayList<Annotation>) AnnotationLibrarian.getAllCoveredAnnotationsOfType(sent, Laterality.type, false);
                    ArrayList<Annotation> closest_r_values = (ArrayList<Annotation>) AnnotationLibrarian.getNextClosestAnnotations(((AnchoredSentence)sent).getAnchor(), bList);
                    ArrayList<Annotation> closest_l_values = (ArrayList<Annotation>) AnnotationLibrarian.getPreviousClosestAnnotations(((AnchoredSentence)sent).getAnchor(), bList);
                    //if len(closest_r_dates) > 0: v = first element in bList (index0)
                    //LAterality will take the next closest to the left first, then right
                    if (closest_l_values.size() > 0) {
                        Annotation v = closest_l_values.get(0);
                        current_pattern.setLaterality_Type(((Laterality) v).getConcept());
                        current_pattern.setLaterality_Term(((Laterality) v).getCoveredText());
                    }
                    else  if (closest_r_values.size() > 0)  {
                        Annotation v = closest_r_values.get(0);
                        current_pattern.setLaterality_Type(((Laterality) v).getConcept());
                        current_pattern.setLaterality_Term(((Laterality) v).getCoveredText());
                    }
                    //Finally if no anatomy is found, but the anatomy is described in plural form set to bilateral
                    else if (current_pattern.getAnatomy_Type() == "Internal_Carotid_plural"){
                        current_pattern.setLaterality_Type("Bilateral");
                        current_pattern.setLaterality_Term("Plural_anatomy");
                    }

                    //Then check if the instance is in a section that contained lateralityi.e. Right: .....
                    else if (current_pattern.getSection() == "stenosis_laterality_left"){
                        current_pattern.setLaterality_Type("Left");
                        current_pattern.setLaterality_Term("laterality_section");

                    } else if (current_pattern.getSection() == "stenosis_laterality_right") {
                        current_pattern.setLaterality_Type("Right");
                        current_pattern.setLaterality_Term("laterality_section");

                    }
                }



                if (RatioList.size() > 0) {
                    Annotation ratiot = RatioList.get(0);
                    //If ratio is extracted then put the value, otherwise just put the covered text
                    current_pattern.setRatio_1(((Ratio_Pattern) ratiot).getRatio_1());
                }else if (SentenceList.size() > 0) { // if there is no value in the pattern, look in the sentence
                    Annotation sent = SentenceList.get(0);
                    // use an array list to get the value closest to the anchor term
                    ArrayList<Annotation> bList = (ArrayList<Annotation>) AnnotationLibrarian.getAllCoveredAnnotationsOfType(sent, Ratio_Term.type, false);
                    ArrayList<Annotation> closest_r_values = (ArrayList<Annotation>) AnnotationLibrarian.getNextClosestAnnotations(((AnchoredSentence)sent).getAnchor(), bList);
                    ArrayList<Annotation> closest_l_values = (ArrayList<Annotation>) AnnotationLibrarian.getPreviousClosestAnnotations(((AnchoredSentence)sent).getAnchor(), bList);
                    //if len(closest_r_dates) > 0: v = first element in bList (index0)
                    //LAterality will take the next closest to the left first, then right
                    if (closest_l_values.size() > 0) {
                        Annotation v = closest_l_values.get(0);
                        current_pattern.setRatio_1(((Ratio_Term) v).getCoveredText());
                    }
                    else  if (closest_r_values.size() > 0)  {
                        Annotation v = closest_r_values.get(0);
                        current_pattern.setRatio_1(((Ratio_Term) v).getCoveredText());
                    }

                }

                if (SentenceList.size() > 0) {
                    Annotation sent = SentenceList.get(0);
                    current_pattern.setAnchored_Sentence(sent.getCoveredText());
                }

                }







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
