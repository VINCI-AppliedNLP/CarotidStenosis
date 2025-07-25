package resources

import gov.va.vinci.leo.regex.types.RegularExpressionType

/* An arbitrary name for this annotator. Used in the pipeline for the name of this annotation. */
name = "carotid stenosis"

configuration {
    /* All configuration for this annotator. */
    defaults {
        /* Global for all configrations below if a property specified here is not overridden in a section below. */

        outputType = RegularExpressionType.class.canonicalName
        case_sensitive = false
        matchedPatternFeatureName = "pattern"
        concept_feature_name = "concept"
        groupFeatureName = "group"
    }

/*
Carotid stenosis
Possible additions to modifiers or section types
Exam type:
Carotid Duplex study:

*/



    "Procedure_graft" {
        expressions = [

                'bypass\\s*graft'

        ]
        concept_feature_value = "Bypass_Graft"
        outputType = "gov.va.vinci.types.Procedure"
    }

    "Procedure_Stent" {
        expressions = [

                'stent',
                'stented',

        ]
        concept_feature_value = "stent"
        outputType = "gov.va.vinci.types.Procedure"
    }



}





