package gov.va.vinci.ae;

import gov.va.vinci.leo.AnnotationLibrarian;
import gov.va.vinci.leo.ae.LeoBaseAnnotator;
import gov.va.vinci.leo.descriptors.LeoTypeSystemDescription;
import gov.va.vinci.types.*;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import java.util.ArrayList;

//import gov.va.vinci.leo.context.types.TermContext;

public class SeverityLogic extends LeoBaseAnnotator {

    /*@Override
    public void initialize(UimaContext context) throws ResourceInitializationException {
        super.initialize(context);
    }
*/
    @Override
    public void annotate(JCas aJCas) throws AnalysisEngineProcessException {
        FSIterator<Annotation> patternit = this.getAnnotationListForType(aJCas, Severity_Pattern.class.getCanonicalName());
        while (patternit.hasNext()) try {
            Severity_Pattern current_pattern = (Severity_Pattern) patternit.next();
            if (current_pattern != null) {
                ArrayList<Annotation> severityList = new ArrayList<Annotation>();
                ArrayList<Annotation> percentList = new ArrayList<Annotation>();
                ArrayList<Annotation> desciptorList = new ArrayList<Annotation>();

                //ArrayList<AnnotationPatternType> LPMList = new ArrayList<AnnotationPatternType>();

                //Respiration concepts
                desciptorList.addAll(AnnotationLibrarian.getAllOverlappingAnnotationsOfType(current_pattern, Percent_Descriptor.type, false));

                severityList.addAll(AnnotationLibrarian.getAllOverlappingAnnotationsOfType(current_pattern, Severity_Term.type, false));
                percentList.addAll(AnnotationLibrarian.getAllOverlappingAnnotationsOfType(current_pattern, Percent_Pattern.type, false));

                //Get complete extraction
                if (severityList.size() > 0) {
                    Annotation severityterm = severityList.get(0);
                    current_pattern.setSeverity_Type(((Severity_Term) severityterm).getConcept());
                    current_pattern.setSeverity_Term((severityterm).getCoveredText());
                }

                if (percentList.size() > 0) {
                    Percent_Pattern percentpattern = (Percent_Pattern)  percentList.get(0);
                    current_pattern.setPercent_Descriptor((percentpattern).getPercent_Descriptor());
                    if (percentpattern.getAnchor() != null) {
                        current_pattern.setPercent_1(percentpattern.getAnchor().getCoveredText());
                    }
                    if (percentpattern.getTarget() != null) {
                        current_pattern.setPercent_2(percentpattern.getTarget().getCoveredText());
                    }
                    if (desciptorList.size() > 0) {
                        Annotation percentdescriptor = desciptorList.get(0);
                        current_pattern.setPercent_Descriptor(((Percent_Descriptor) percentdescriptor).getConcept());
                    }

                }


            }



 }   catch (CASException e) {
            logger.error(e.getStackTrace());

            //

        }


    }
    @Override
    public LeoTypeSystemDescription getLeoTypeSystemDescription() {
        return new LeoTypeSystemDescription();
    }
}
