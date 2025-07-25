package gov.va.vinci;

import gov.va.vinci.leo.annotationpattern.ae.AnnotationPatternAnnotator;
import gov.va.vinci.leo.context.ae.ContextAnnotator;
import gov.va.vinci.leo.descriptors.LeoTypeSystemDescription;
import gov.va.vinci.leo.descriptors.TypeDescriptionBuilder;
import gov.va.vinci.leo.merger.ae.MergeAnnotator;
import gov.va.vinci.leo.regex.ae.RegexAnnotator;
import gov.va.vinci.leo.whitespace.ae.WhitespaceTokenizer;
import gov.va.vinci.leo.sentence.ae.AnchoredSentenceAnnotator;
import gov.va.vinci.leo.sentence.ae.SentenceAnnotator;
import gov.va.vinci.leo.types.TypeLibrarian;
import gov.va.vinci.leo.window.ae.WindowAnnotator;
//import opennlp.tools.tokenize.WhitespaceTokenizer;

import java.io.File;

public class TypeSystem {
    //TYPE_SIMPLE_CONCEPT_TERM
    private static String TYPE_ANATOMY_TERM = "gov.va.vinci.types.Anatomy";
    private static String TYPE_ANATOMY_LOCATION = "gov.va.vinci.types.Anatomy_Location";
    private static String TYPE_DISEASE_TERM = "gov.va.vinci.types.Disease";
    private static String TYPE_PERCENT_VALUE = "gov.va.vinci.types.Percent_Value";
    private static String TYPE_PERCENT_DESCRIPTOR = "gov.va.vinci.types.Percent_Descriptor";
    private static String TYPE_SEVERITY_TERM = "gov.va.vinci.types.Severity_Term";
    private static String TYPE_SEVERITY_MODIFIER = "gov.va.vinci.types.Severity_Modifier";
    private static String TYPE_LATERALITY_TERM = "gov.va.vinci.types.Laterality";
    private static String TYPE_PROCEDURE_TERM = "gov.va.vinci.types.Procedure";
    private static String TYPE_RATIO_TERM = "gov.va.vinci.types.Ratio_Term";
    private static String TYPE_RATIO_VALUE_TERM = "gov.va.vinci.types.Ratio_Value";
    private static String TYPE_RATIO_QUALIFIER = "gov.va.vinci.types.Ratio_Qualifier";
    private static String TYPE_RATIO_VALUE_EXCLUSION = "gov.va.vinci.types.Ratio_Value_Exclusion";
    private static String TYPE_MIDDLE_TERM = "gov.va.vinci.types.Middle";
    private static String TYPE_RATIO_MIDDLE_TERM = "gov.va.vinci.types.Ratio_Middle";
    private static String TYPE_MODIFIER_TERM = "gov.va.vinci.types.Modifier";

    //Section Types
    private static String TYPE_SECTION_HEADER = "gov.va.vinci.types.SectionHeader";
    private static String TYPE_SECTION = "gov.va.vinci.types.Section";

    //Pattern Types
    private static String TYPE_ANATOMY_PATTERN = "gov.va.vinci.types.Anatomy_Pattern";
    private static String TYPE_STENOSIS_PATTERN = "gov.va.vinci.types.Stenosis_Pattern";
    private static String TYPE_DISEASE_PATTERN = "gov.va.vinci.types.Disease_Pattern";
    private static String TYPE_SEVERITY_PATTERN = "gov.va.vinci.types.Severity_Pattern";
    private static String TYPE_RATIO_PATTERN = "gov.va.vinci.types.Ratio_Pattern";
    private static String TYPE_PERCENT_PATTERN = "gov.va.vinci.types.Percent_Pattern";


    //Window and Context
    private static String TYPE_EXCLUDE_WINDOW = "gov.va.vinci.types.Exclude_Window";
    private static String TYPE_SECTION_WINDOW = "gov.va.vinci.types.Section_Window";
    private static String TYPE_INPUT_WINDOW = "gov.va.vinci.types.Input_Window";
    private static String TYPE_CONTEXT = "gov.va.vinci.leo.context.types.TermContext";


    public static void main(String[] args) {
        try {
            LeoTypeSystemDescription types = new LeoTypeSystemDescription();
            types.addTypeSystemDescription(getLeoTypeSystemDescription());

            File srcDir = new File("generated-types/src");
            srcDir.mkdirs();

            File classesDir = new File("generated-types/classes");
            classesDir.mkdirs();

            types.jCasGen(srcDir.getCanonicalPath(), classesDir.getCanonicalPath());

            File resDir = new File("generated-types/resources/types");
            resDir.mkdirs();

            types.toXML(resDir.getCanonicalPath() + "/TypeSystem.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static LeoTypeSystemDescription getLeoTypeSystemDescription() {
        LeoTypeSystemDescription types = new LeoTypeSystemDescription();
        /** Leo Bones **/
        types.addType(TypeLibrarian.getCSITypeSystemDescription());
        types.addTypeSystemDescription(new WindowAnnotator().getLeoTypeSystemDescription());
        types.addTypeSystemDescription(new RegexAnnotator().getLeoTypeSystemDescription());
        types.addTypeSystemDescription(new AnnotationPatternAnnotator().getLeoTypeSystemDescription());
        types.addTypeSystemDescription(new AnchoredSentenceAnnotator().getLeoTypeSystemDescription());
        types.addTypeSystemDescription(new SentenceAnnotator().getLeoTypeSystemDescription());
        types.addTypeSystemDescription(new ContextAnnotator().getLeoTypeSystemDescription());
        types.addTypeSystemDescription(new MergeAnnotator().getLeoTypeSystemDescription());
        types.addTypeSystemDescription(new WhitespaceTokenizer().getLeoTypeSystemDescription());


        types.addTypeSystemDescription(getSpecificTypeSystemDescription());


        return types;
    }

    public static LeoTypeSystemDescription getSpecificTypeSystemDescription() {
        LeoTypeSystemDescription types = new LeoTypeSystemDescription();

        try {
            //Simple Concept Extraction
            types.addType(TYPE_ANATOMY_TERM, "", PARENT_CLASS.TYPE_REGEX.type);
            types.addType(TYPE_ANATOMY_LOCATION, "", PARENT_CLASS.TYPE_REGEX.type);
            types.addType(TYPE_DISEASE_TERM, "", PARENT_CLASS.TYPE_REGEX.type);
            types.addType(TYPE_PERCENT_VALUE, "", PARENT_CLASS.TYPE_REGEX.type);
            types.addType(TYPE_PERCENT_DESCRIPTOR, "", PARENT_CLASS.TYPE_REGEX.type);
            types.addType(TYPE_SECTION_HEADER, "", PARENT_CLASS.TYPE_REGEX.type);
            types.addType(TYPE_SECTION_WINDOW, "", PARENT_CLASS.TYPE_WINDOW.type);
            types.addType(TYPE_SEVERITY_TERM, "", PARENT_CLASS.TYPE_REGEX.type);
            types.addType(TYPE_PROCEDURE_TERM, "", PARENT_CLASS.TYPE_REGEX.type);
            types.addType(TYPE_SEVERITY_MODIFIER, "", PARENT_CLASS.TYPE_REGEX.type);
            types.addType(TYPE_RATIO_TERM, "", PARENT_CLASS.TYPE_REGEX.type);
            types.addType(TYPE_RATIO_VALUE_TERM, "", PARENT_CLASS.TYPE_REGEX.type);
            types.addType(TYPE_RATIO_VALUE_EXCLUSION, "", PARENT_CLASS.TYPE_REGEX.type);
            types.addType(TYPE_LATERALITY_TERM, "", PARENT_CLASS.TYPE_REGEX.type);
            types.addType(TYPE_MODIFIER_TERM, "", PARENT_CLASS.TYPE_REGEX.type);
            types.addType(TYPE_MIDDLE_TERM, "", PARENT_CLASS.TYPE_REGEX.type);
            types.addType(TYPE_RATIO_MIDDLE_TERM, "", PARENT_CLASS.TYPE_REGEX.type);
            types.addType(TYPE_RATIO_QUALIFIER, "", PARENT_CLASS.TYPE_REGEX.type);


            types.addType(TypeDescriptionBuilder.create(TYPE_STENOSIS_PATTERN, "", PARENT_CLASS.TYPE_PATTERN.type)
                    .addFeature("Anatomy_Type", "", "uima.cas.String")
                    .addFeature("Anatomy_Term", "", "uima.cas.String")
                    .addFeature("Anatomy_Attribute", "", "uima.cas.String")
                    .addFeature("Anatomy_Attribute_Term", "", "uima.cas.String")
                    .addFeature("Disease_Type", "", "uima.cas.String")
                    .addFeature("Disease_Term", "", "uima.cas.String")
                    .addFeature("Section", "", "uima.cas.String")
                    .addFeature("Section_Header", "", "uima.cas.String")
                    .addFeature("Procedure_Type", "", "uima.cas.String")
                    .addFeature("Procedure_Term", "", "uima.cas.String")
                    .addFeature("Laterality_Type", "", "uima.cas.String")
                    .addFeature("Laterality_Term", "", "uima.cas.String")
                    .addFeature("Severity_Term_Type", "", "uima.cas.String")
                    .addFeature("Severity_Term", "", "uima.cas.String")
                    .addFeature("Percent_1", "", "uima.cas.String")
                    .addFeature("Percent_2", "", "uima.cas.String")
                    .addFeature("Percent_Descriptor", "", "uima.cas.String")
                    .addFeature("Ratio_1", "", "uima.cas.String")
                    .addFeature("Ratio_2", "", "uima.cas.String")
                    .addFeature("Pattern_Text", "", "uima.cas.String")
                    .addFeature("Anchored_Sentence", "", "uima.cas.String")
                    .getTypeDescription());
            types.addType(TypeDescriptionBuilder.create(TYPE_DISEASE_PATTERN, "", PARENT_CLASS.TYPE_PATTERN.type)
                    .addFeature("Disease_Type", "", "uima.cas.String")
                    .addFeature("Disease_Term", "", "uima.cas.String")
                    .addFeature("Severity_Type", "", "uima.cas.String")
                    .addFeature("Severity_Term", "", "uima.cas.String")
                    .addFeature("Percent_1", "", "uima.cas.String")
                    .addFeature("Percent_2", "", "uima.cas.String")
                    .addFeature("Percent_Descriptor", "", "uima.cas.String")
                    .getTypeDescription());
            types.addType(TypeDescriptionBuilder.create(TYPE_ANATOMY_PATTERN, "", PARENT_CLASS.TYPE_PATTERN.type)
                    .addFeature("Vessel_Type", "", "uima.cas.String")
                    .addFeature("Vessel_Term", "", "uima.cas.String")
                    .addFeature("Location_Type", "", "uima.cas.String")
                    .addFeature("Location_Term", "", "uima.cas.String")
                    .getTypeDescription());

            types.addType(TypeDescriptionBuilder.create(TYPE_SEVERITY_PATTERN, "", PARENT_CLASS.TYPE_PATTERN.type)
                    .addFeature("Severity_Term", "", "uima.cas.String")
                    .addFeature("Severity_Type", "", "uima.cas.String")
                    .addFeature("Percent_1", "", "uima.cas.String")
                    .addFeature("Percent_2", "", "uima.cas.String")
                    .addFeature("Percent_Descriptor", "", "uima.cas.String")
                    .getTypeDescription());
            types.addType(TypeDescriptionBuilder.create(TYPE_RATIO_PATTERN, "", PARENT_CLASS.TYPE_PATTERN.type)
                    .addFeature("Pattern_Text", "", "uima.cas.String")
                    .addFeature("Laterality", "", "uima.cas.String")
                    .addFeature("Ratio_Term", "", "uima.cas.String")
                    .addFeature("Ratio_1", "", "uima.cas.String")
                    .addFeature("Ratio_2", "", "uima.cas.String")
                    .addFeature("Ratio_Descriptor", "", "uima.cas.String")
                    .addFeature("Ratio_Qualifier", "", "uima.cas.String")
                    .addFeature("Section", "", "uima.cas.String")
                    .getTypeDescription());
            types.addType(TypeDescriptionBuilder.create(TYPE_PERCENT_PATTERN, "", PARENT_CLASS.TYPE_PATTERN.type)
                    .addFeature("Percent_1", "", "uima.cas.String")
                    .addFeature("Percent_2", "", "uima.cas.String")
                    .addFeature("Percent_Descriptor", "", "uima.cas.String")
                    .getTypeDescription());
            types.addType(TypeDescriptionBuilder.create(TYPE_SECTION, "Section Type", "uima.tcas.Annotation")
                    .addFeature("SectionHeader", "Anchor annotation around which the section was created", "uima.tcas.Annotation")
                    .addFeature("SectionHeaderText", "text of the header", "uima.cas.String")
                    .addFeature("Term", "text of the header", "uima.cas.String")
                    .addFeature("Snippet", "text", "uima.cas.String")
                    .getTypeDescription());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return types;
    }



    public enum PARENT_CLASS {
        TYPE_WINDOW("gov.va.vinci.leo.window.types.Window"),
        TYPE_REGEX("gov.va.vinci.leo.regex.types.RegularExpressionType"),
        TYPE_PATTERN("gov.va.vinci.leo.annotationpattern.types.AnnotationPatternType"),
        CONTEXT("gov.va.vinci.leo.context.types.Context"),
        LINK("gov.va.vinci.leo.conceptlink.types.Link");

        public String type;

        private PARENT_CLASS(String type) {

            this.type = type;
        }

        public String getType() {

            return this.type;
        }

    }

}
