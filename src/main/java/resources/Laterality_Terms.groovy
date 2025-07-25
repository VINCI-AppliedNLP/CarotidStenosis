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


    "Right_Laterality" {
        expressions = [

                '\br ',
                '\brt',
                'right-sided',
                '(on\\s*the\\s*)?right side',
                '(on\\s*the\\s*)?right',
                'on the- Right side:',
                'Right\\s*side\\s*:',
                'of\\s*the\\s*Right(\\s*side)?',
                'at\\s*the\\s*right\\s*side',
                'on the Right side:',
                'for\\s*the\\s*right',
                'on\\s*right',
                //Stenosis ratio specific -- will be looking for overlaps in the pattern
                'RICA',
                'RCCA'


        ]
        concept_feature_value = "Right"
        outputType = "gov.va.vinci.types.Laterality"
    }
    "Left_Laterality" {
        expressions = [

                '\bl ',
                '\blt',
                'left-sided',
                '(on\\s*the\\s*)?left side',
                'Left\\s*side\\s*:',
                '(on\\s*the\\s*)?left',
                'on the- left side:',
                'on the left side:',
                'for\\s*the\\s*left',
                'on\\s*left',
                'of\\s*the\\s*left(\\s*side)?',
                'at\\s*the\\s*left\\s*side',
                'LICA',
                'LCCA'

        ]
        concept_feature_value = "Left"
        outputType = "gov.va.vinci.types.Laterality"
    }

    "Bi_Laterality" {
        expressions = [


                'bilateral(ly)?',
                'both',
                'right\\s*and\\s*left',
                'left\\s*and\\s*right',
                'left\\s*or\\s*right',
                'right\\s*or\\s*left',
        ]
        concept_feature_value = "Bilateral"
        outputType = "gov.va.vinci.types.Laterality"
    }


}





