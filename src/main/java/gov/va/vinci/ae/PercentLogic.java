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

public class PercentLogic extends LeoBaseAnnotator {

    /*@Override
    public void initialize(UimaContext context) throws ResourceInitializationException {
        super.initialize(context);
    }
*/
    @Override
    public void annotate(JCas aJCas) throws AnalysisEngineProcessException {
        FSIterator<Annotation> patternit = this.getAnnotationListForType(aJCas, Percent_Pattern.class.getCanonicalName());
        while (patternit.hasNext()) try {
            Percent_Pattern current_pattern = (Percent_Pattern) patternit.next();
            if (current_pattern != null) {
                ArrayList<Annotation> desciptorList = new ArrayList<Annotation>();

                //ArrayList<AnnotationPatternType> LPMList = new ArrayList<AnnotationPatternType>();

                //Respiration concepts
                desciptorList.addAll(AnnotationLibrarian.getAllOverlappingAnnotationsOfType(current_pattern, Percent_Descriptor.type, false));
                //Get complete extraction
                //percents done as anchors in severitylogic
                if (desciptorList.size() > 0) {
                    Annotation percentdescriptor = desciptorList.get(0);
                    current_pattern.setPercent_Descriptor(((Percent_Descriptor) percentdescriptor).getConcept());
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
