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

public class DiseaseLogic extends LeoBaseAnnotator {

    /*@Override
    public void initialize(UimaContext context) throws ResourceInitializationException {
        super.initialize(context);
    }
*/
    @Override
    public void annotate(JCas aJCas) throws AnalysisEngineProcessException {
        FSIterator<Annotation> patternit = this.getAnnotationListForType(aJCas, Disease_Pattern.class.getCanonicalName());
        while (patternit.hasNext()) try {
            Disease_Pattern current_pattern = (Disease_Pattern) patternit.next();
            if (current_pattern != null) {
                ArrayList<Annotation> DiseaseList = new ArrayList<Annotation>();
                ArrayList<Annotation> SeverityList = new ArrayList<Annotation>();



                //Disease concepts
                DiseaseList.addAll(AnnotationLibrarian.getAllOverlappingAnnotationsOfType(current_pattern, Disease.type, false));
                SeverityList.addAll(AnnotationLibrarian.getAllOverlappingAnnotationsOfType(current_pattern, Severity_Pattern.type, false));

                if (DiseaseList.size() > 0) {
                    Annotation anchor = DiseaseList.get(0);
                    current_pattern.setDisease_Type(((Disease) anchor).getConcept());
                    current_pattern.setDisease_Term(((Disease) anchor).getCoveredText());
                }
               if (SeverityList.size() > 0) {
                    Annotation severity = SeverityList.get(0);
                    current_pattern.setSeverity_Type(((Severity_Pattern) severity).getSeverity_Type());
                    current_pattern.setSeverity_Term((severity).getCoveredText());
                    current_pattern.setPercent_1(((Severity_Pattern) severity).getPercent_1());
                    current_pattern.setPercent_2(((Severity_Pattern) severity).getPercent_2());
                    current_pattern.setPercent_Descriptor(((Severity_Pattern) severity).getPercent_Descriptor());

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
