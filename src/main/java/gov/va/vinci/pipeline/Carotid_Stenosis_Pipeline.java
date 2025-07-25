package gov.va.vinci.pipeline;

/*
*
* Simple Pipeline to output a sentence and snippet around simple regexes
* for intital concept extraction, identification, and expansion
*
 */

import gov.va.vinci.TypeSystem;
import gov.va.vinci.ae.*;
import gov.va.vinci.leo.annotationpattern.ae.AnnotationPatternAnnotator;
import gov.va.vinci.leo.descriptors.LeoAEDescriptor;
import gov.va.vinci.leo.descriptors.LeoTypeSystemDescription;
import gov.va.vinci.leo.filter.ae.FilterAnnotator;
import gov.va.vinci.leo.merger.ae.MergeAnnotator;
import gov.va.vinci.leo.regex.ae.RegexAnnotator;
import gov.va.vinci.leo.sentence.ae.AnchoredSentenceAnnotator;

import java.util.ArrayList;
import java.util.HashMap;


public class Carotid_Stenosis_Pipeline extends BasePipeline {





    //Regex Types
    private static String TYPE_ANATOMY_TERM = "gov.va.vinci.types.Anatomy";
    private static String TYPE_ANATOMY_LOCATION_TERM = "gov.va.vinci.types.Anatomy_Location";
    private static String TYPE_DISEASE_TERM = "gov.va.vinci.types.Disease";
    private static String TYPE_SEVERITY_TERM = "gov.va.vinci.types.Severity_Term";
    private static String TYPE_PERCENT_VALUE = "gov.va.vinci.types.Percent_Value";
    private static String TYPE_LATERALITY_TERM = "gov.va.vinci.types.Laterality";
    private static String TYPE_PROCEDURE_TERM = "gov.va.vinci.types.Procedure";
    private static String TYPE_RATIO_TERM = "gov.va.vinci.types.Ratio_Term";
    private static String TYPE_RATIO_VALUE = "gov.va.vinci.types.Ratio_Value";
    private static String TYPE_RATIO_VALUE_EXCLUDE = "gov.va.vinci.types.Ratio_Value_Exclusion";
    private static String TYPE_MIDDLE_TERM = "gov.va.vinci.types.Middle";
    private static String TYPE_MODIFIER_TERM = "gov.va.vinci.types.Modifier";
    private static String TYPE_STENOSIS_PATTERN = "gov.va.vinci.types.Stenosis_Pattern";
    private static String TYPE_ANATOMY_PATTERN = "gov.va.vinci.types.Anatomy_Pattern";
    private static String TYPE_PERCENT_PATTERN = "gov.va.vinci.types.Percent_Pattern";
    private static String TYPE_RATIO_PATTERN = "gov.va.vinci.types.Ratio_Pattern";
    private static String TYPE_DISEASE_PATTERN = "gov.va.vinci.types.Disease_Pattern";
    private static String TYPE_SEVERITY_PATTERN = "gov.va.vinci.types.Severity_Pattern";
    private static String TYPE_SECTION_HEADER = "gov.va.vinci.types.SectionHeader";
    private static String TYPE_SECTION = "gov.va.vinci.types.Section";

    //Pattern Types
    private String RESOURCE_PATH = "src/main/java/resources/";
    private String RESOURCE_STENOSIS_PATTERN = "stenosis_pattern.pattern";
    private String RESOURCE_DISEASE_PATTERN = "disease_pattern.pattern";
    private String RESOURCE_ANATOMY_PATTERN = "anatomy_pattern.pattern";
    private String RESOURCE_RATIO_PATTERN = "ratio_pattern.pattern";
    private String RESOURCE_SEVERITY_PATTERN = "severity_pattern.pattern";
    private String RESOURCE_PERCENT_PATTERN = "percent_pattern.pattern";
    private String RESOURCE_DISEASE = "Disease_Terms.groovy";
    private String RESOURCE_ANATOMY = "Anatomy_Terms.groovy";
    private String RESOURCE_LATERALITY = "Laterality_Terms.groovy";
    private String RESOURCE_SEVERITY = "Severity_Terms.groovy";
    private String RESOURCE_PROCEDURE = "Procedure_Terms.groovy";
    private String RESOURCE_SECTION_HEADER = "sectionHeaders.groovy";

    private String RESOURCE_CONTEXT = "contextRules.txt";
    private String TYPE_WINDOW_FEATURE = "Anchor";
    private String SENTENCE_TYPE = "gov.va.vinci.leo.sentence.types.AnchoredSentence";
    private String TYPE_CONTEXT = "gov.va.vinci.leo.context.types.TermContext";

    /**
     * Constructors
     *
     * @throws Exception
     */


    public Carotid_Stenosis_Pipeline(HashMap args) throws Exception {
    /* List for holding our annotators. This list, and the order of the list created the annotator pipeline. */
        ArrayList<LeoAEDescriptor> annotators = new ArrayList<LeoAEDescriptor>();


        //1. Regex annotation

        annotators.add(new RegexAnnotator()
                .setGroovyConfigFile(RESOURCE_PATH + RESOURCE_ANATOMY) //Simple Terms
                .setName("Concept Regex")
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));
        annotators.add(new RegexAnnotator()
                .setGroovyConfigFile(RESOURCE_PATH + RESOURCE_DISEASE) //Simple Terms
                .setName("Concept Regex")
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));
        annotators.add(new RegexAnnotator()
                .setGroovyConfigFile(RESOURCE_PATH + RESOURCE_LATERALITY) //Simple Terms
                .setName("Modifier Regex")
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));
        annotators.add(new RegexAnnotator()
                .setGroovyConfigFile(RESOURCE_PATH + RESOURCE_PROCEDURE) //Simple Terms
                .setName("Modifier Regex")
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));
        annotators.add(new RegexAnnotator()
                .setGroovyConfigFile(RESOURCE_PATH + RESOURCE_SEVERITY) //Simple Terms
                .setName("Modifier Regex")
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));


        //2. Merge  overlapped terms
        annotators.add(new MergeAnnotator()
                .setTypesToMerge(new String[]{TYPE_ANATOMY_TERM})
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));
        annotators.add(new MergeAnnotator()
                .setTypesToMerge(new String[]{TYPE_DISEASE_TERM})
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));
        annotators.add(new MergeAnnotator()
                .setTypesToMerge(new String[]{TYPE_LATERALITY_TERM})
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));
        annotators.add(new MergeAnnotator()
                .setTypesToMerge(new String[]{TYPE_ANATOMY_LOCATION_TERM})
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));
        annotators.add(new MergeAnnotator()
                .setTypesToMerge(new String[]{TYPE_PROCEDURE_TERM})
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));

        annotators.add(new MergeAnnotator()
                .setTypesToMerge(new String[]{TYPE_RATIO_TERM})
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));
        annotators.add(new MergeAnnotator()
                .setTypesToMerge(new String[]{TYPE_RATIO_VALUE})
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));



        annotators.add(new MergeAnnotator()
                .setTypesToMerge(new String[]{TYPE_MODIFIER_TERM})
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));
        annotators.add(new MergeAnnotator()
                .setTypesToMerge(new String[]{TYPE_DISEASE_TERM})
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));
        annotators.add(new MergeAnnotator()
                .setTypesToMerge(new String[]{TYPE_SEVERITY_TERM})
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));
        annotators.add(new MergeAnnotator()
                .setTypesToMerge(new String[]{TYPE_PERCENT_VALUE})
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));
        annotators.add(new MergeAnnotator()
                .setTypesToMerge(new String[]{TYPE_MIDDLE_TERM})
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));

        //An anatomy found within a disease term is removed here.
        // i.e [carotid occlusive disease](disease) in the [ICA](anatomy)
        // Instead of [carotid](anatomy) [occlusive disease](disease) in the [ICA](anatomy)
        annotators.add(new FilterAnnotator()
                .setTypesToKeep(new String[]{TYPE_DISEASE_TERM})
                .setTypesToDelete(new String[]{TYPE_ANATOMY_TERM})
                .setRemoveOverlapping(true)
                .setName("OverlapFilter")
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));

        annotators.add(new FilterAnnotator()
                .setTypesToKeep(new String[]{TYPE_RATIO_VALUE_EXCLUDE})
                .setTypesToDelete(new String[]{TYPE_RATIO_VALUE})
                .setRemoveOverlapping(true)
                .setName("ValueOverlapFilter")
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));

        //A value completely overlapped by an original term is removed here. i.e Ratio (normal is 2.0):
        annotators.add(new FilterAnnotator()
                .setTypesToKeep(new String[]{TYPE_RATIO_TERM})
                .setTypesToDelete(new String[]{TYPE_RATIO_VALUE})
                .setRemoveOverlapping(true)
                .setName("ValueOverlapFilter")
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));

        //2. Section Regex annotation
        annotators.add(new RegexAnnotator()
                .setGroovyConfigFile(RESOURCE_PATH + RESOURCE_SECTION_HEADER)
                .setName("SectionHeader Regex")
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));
        annotators.add(new FilterAnnotator()
                .setTypesToKeep(new String[]{TYPE_SECTION_HEADER})
                .setRemoveOverlapping(true)
                .setName("OverlapFilter")
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));


        annotators.add(new RegexBasedSectionizer()
                .setName("SectionAnnotator")
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));
        annotators.add(new MergeAnnotator()
                .setTypesToMerge(new String[]{TYPE_SECTION})
                .setFeaturesToMatch(new String[]{"SectionHeaderText"})
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));

        //Same idea as the anatomy removal above for non-specific headers
        annotators.add(new FilterAnnotator()
                .setTypesToKeep(new String[]{TYPE_SECTION_HEADER})
                .setTypesToDelete(new String[]{TYPE_ANATOMY_TERM})
                .setRemoveOverlapping(true)
                .setName("OverlapFilter")
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));

        annotators.add(new AnnotationPatternAnnotator()
                .setResource(RESOURCE_PATH + RESOURCE_PERCENT_PATTERN)
                .setOutputType(TYPE_PERCENT_PATTERN)
                .getLeoAEDescriptor().setName("Anatomy Pattern Annotator")
                .setTypeSystemDescription(getLeoTypeSystemDescription()));
        annotators.add(new MergeAnnotator()
                .setTypesToMerge(new String[]{TYPE_PERCENT_PATTERN})
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));

        annotators.add(new AnnotationPatternAnnotator()
                .setResource(RESOURCE_PATH + RESOURCE_RATIO_PATTERN)
                .setOutputType(TYPE_RATIO_PATTERN)
                .getLeoAEDescriptor().setName("Anatomy Pattern Annotator")
                .setTypeSystemDescription(getLeoTypeSystemDescription()));
        annotators.add(new MergeAnnotator()
                .setTypesToMerge(new String[]{TYPE_RATIO_PATTERN})
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));



        annotators.add(new AnnotationPatternAnnotator()
                .setResource(RESOURCE_PATH + RESOURCE_ANATOMY_PATTERN)
                .setOutputType(TYPE_ANATOMY_PATTERN)
                .getLeoAEDescriptor().setName("Anatomy Pattern Annotator")
                .setTypeSystemDescription(getLeoTypeSystemDescription()));
        annotators.add(new MergeAnnotator()
                .setTypesToMerge(new String[]{TYPE_ANATOMY_PATTERN})
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));



        /*
        annotators.add(new AnnotationPatternAnnotator()
                .setResource(RESOURCE_PATH + RESOURCE_PERCENT_PATTERN)
                .setOutputType(TYPE_PERCENT_PATTERN)
                .getLeoAEDescriptor().setName("Severity Pattern Annotator")
                .setTypeSystemDescription(getLeoTypeSystemDescription()));

         */
        annotators.add(new AnnotationPatternAnnotator()
                .setResource(RESOURCE_PATH + RESOURCE_SEVERITY_PATTERN)
                .setOutputType(TYPE_SEVERITY_PATTERN)
                .getLeoAEDescriptor().setName("Severity Pattern Annotator")
                .setTypeSystemDescription(getLeoTypeSystemDescription()));
        annotators.add(new MergeAnnotator()
                .setTypesToMerge(new String[]{TYPE_SEVERITY_PATTERN})
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));


        annotators.add(new AnnotationPatternAnnotator()
                .setResource(RESOURCE_PATH + RESOURCE_DISEASE_PATTERN)
                .setOutputType(TYPE_DISEASE_PATTERN)
                .getLeoAEDescriptor().setName("Concept Pattern Annotator")
                .setTypeSystemDescription(getLeoTypeSystemDescription()));


        //Merge disease patterns if they are for the same disease
        annotators.add(new MergeAnnotator()
                .setTypesToMerge(new String[]{TYPE_DISEASE_PATTERN})
                //.setFeaturesToMatch(new String[]{"Disease_Type"})
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));



        annotators.add(new AnnotationPatternAnnotator()
                .setResource(RESOURCE_PATH + RESOURCE_STENOSIS_PATTERN)
                .setOutputType(TYPE_STENOSIS_PATTERN)
                .getLeoAEDescriptor().setName("Concept Pattern Annotator")
                .setTypeSystemDescription(getLeoTypeSystemDescription()));
        annotators.add(new MergeAnnotator()
                .setTypesToMerge(new String[]{TYPE_STENOSIS_PATTERN})
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));
        //3 Sentence and context annotation
        annotators.add(new AnchoredSentenceAnnotator()
                .setSpanSize(100)
                .setAnchorFeature(TYPE_WINDOW_FEATURE)
                .setInputTypes(TYPE_STENOSIS_PATTERN)
                .setOutputType(SENTENCE_TYPE)
                .setName("TermSentenceAnnotator")
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));

        annotators.add(new RatioLogic()
                .setName("Ratio Logic Annotator")
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));
        annotators.add(new SeverityLogic()
                .setName("Severity Logic Annotator")
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));

        //Set the severity of diseases when identified as a pattern.  Afterwards delete any severity terms covered by a pattern annotator so they aren't picked up in
        // A separate disease terms window logic

        annotators.add(new PercentLogic()
                .setName("Percent Logic Annotator")
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));
        annotators.add(new DiseaseLogic()
                .setName("Disease Logic Annotator")
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));
        annotators.add(new FilterAnnotator()
                .setTypesToKeep(new String[]{TYPE_DISEASE_PATTERN})
                .setTypesToDelete(new String[]{TYPE_SEVERITY_TERM})
                .setRemoveOverlapping(true)
                .setName("OverlapFilter")
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));


        //Set Anatomy Logic
        annotators.add(new AnatomyLogic()
                .setName("Anatomy Logic Annotator")
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));
        annotators.add(new StenosisLogic()
                .setName("Stenosis Logic Annotator")
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));

        this.pipeline = new LeoAEDescriptor(annotators);
        this.pipeline.setTypeSystemDescription(getLeoTypeSystemDescription());
    }


    protected LeoTypeSystemDescription defineTypeSystem() throws Exception {
        LeoTypeSystemDescription description = TypeSystem.getLeoTypeSystemDescription();
        return description;
    }
}
