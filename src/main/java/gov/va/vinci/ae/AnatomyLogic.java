package gov.va.vinci.ae;

import gov.va.vinci.leo.AnnotationLibrarian;
import gov.va.vinci.leo.ae.LeoBaseAnnotator;
import gov.va.vinci.leo.annotationpattern.types.AnnotationPatternType;
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

public class AnatomyLogic extends LeoBaseAnnotator {

    /*@Override
    public void initialize(UimaContext context) throws ResourceInitializationException {
        super.initialize(context);
    }
*/
    @Override
    public void annotate(JCas aJCas) throws AnalysisEngineProcessException {
        FSIterator<Annotation> patternit = this.getAnnotationListForType(aJCas, Anatomy_Pattern.class.getCanonicalName());
        while (patternit.hasNext()) try {
            Anatomy_Pattern current_pattern = (Anatomy_Pattern) patternit.next();
            if (current_pattern != null) {
                ArrayList<Annotation> AnatomyList = new ArrayList<Annotation>();
                ArrayList<Annotation> LocationList = new ArrayList<Annotation>();

                //ArrayList<AnnotationPatternType> LPMList = new ArrayList<AnnotationPatternType>();

                //Respiration concepts
                AnatomyList.addAll(AnnotationLibrarian.getAllOverlappingAnnotationsOfType(current_pattern, Anatomy.type, false));
                LocationList.addAll(AnnotationLibrarian.getAllOverlappingAnnotationsOfType(current_pattern, Anatomy_Location.type, false));

                //Get complete extraction
                if (AnatomyList.size() > 0) {
                    Annotation anchor = AnatomyList.get(0);
                    current_pattern.setVessel_Type(((Anatomy) anchor).getConcept());
                    current_pattern.setVessel_Term((anchor).getCoveredText());
                }
                if (LocationList.size() > 0) {
                    Annotation location = LocationList.get(0);
                    current_pattern.setLocation_Type(((Anatomy_Location) location).getConcept());
                    current_pattern.setLocation_Term((location).getCoveredText());

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
