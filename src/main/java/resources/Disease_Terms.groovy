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

    "Disease_stenosis" {
        expressions = [

                'stenosis'
                ,'stenotic lesions'
                ,'stenoses'
                ,'obstruction'
                ,'steno[a-z]*'
                ,'atherosclerotic\\s*stenosis'
                ,'plaque\\s*or\\s*stenosis'
                ,'intraluminal\\s*plaque\\s*or\\s*stenosis'
                //,''\b|\b(no |lack of |diminished )(contrast )?enhancement\b|\bno filling|(no |slow |diminished |paucity of |reduced )flow)'


        ]
        concept_feature_value = "Stenosis"
        outputType = "gov.va.vinci.types.Disease"
    }

    "Disease_aneurysm" {
        expressions = [

                'aneurysm',
                'pseudoaneurysm'
                //,''\b|\b(no |lack of |diminished )(contrast )?enhancement\b|\bno filling|(no |slow |diminished |paucity of |reduced )flow)'

        ]
        concept_feature_value = "aneurysm"
        outputType = "gov.va.vinci.types.Disease"
    }
    "Disease_plaque" {
        expressions = [

                'plaque',
                'plaquing',
                'atheromatous plaquing',


        ]
        concept_feature_value = "plaque"
        outputType = "gov.va.vinci.types.Disease"
    }

    "Disease_atherosclerosis" {
        expressions = [

                'atherosclerosis'

        ]
        concept_feature_value = "atherosclerosis"
        outputType = "gov.va.vinci.types.Disease"
    }

    "Disease_flow_restriction" {
        expressions = [

                'flow\\s*restriction',
                'flow\\s*disturbances',
                'narrowing'


        ]
        concept_feature_value = "flow_restriction"
        outputType = "gov.va.vinci.types.Disease"
    }



    "Disease_occulsion" {
        expressions = [

                'occluded',
                'extensive\\s*arterial\\s*occlusion'
                ,'occlusion'
                ,'obstruction'
                ,'occlusive\\s*disease'
                ,'carotid\\s*occlusive\\s*disease'
                ,'carotid\\s*arterial\\s*occlusive\\s*disease'
                ,'occlusive'
                //,''\b|\b(no |lack of |diminished )(contrast )?enhancement\b|\bno filling|(no |slow |diminished |paucity of |reduced )flow)'

        ]
        concept_feature_value = "Occlusion"
        outputType = "gov.va.vinci.types.Disease"
    }



    "CAROTID Disease" {
        expressions = [
            //   '(lumenal |diameter |flow )(reduction|narrowing)'
            '\bfilling\bdefect[a-z]*\b'
            ,'\baneurysm[a-z]*\b'
            //,'(\boccluded\b|\bocclusion\b|\bobstruction\b|\b(no |lack of |diminished )(contrast )?enhancement\b|\bno filling|(no |slow |diminished |paucity of |reduced )flow)'
            ,'(\bpseudoaneurysm\b|\boutpouching\b)'
            //  ,'(ulcer[a-z]* |unstable |calcif[a-z]* |mixed )(density |calcif[a-z]*| atheroma |atheromatous |(atherosclero[a-z]* |soft )?plaque)'
            //  ,'(arteritis|vasculitis|wall thickening)'
            //  ,'(spasm|vasospasm|transient narrowing|beading|beaded)'
            ,'\bfibromuscular\\s+dysplasia\b'
            ,'(\barteriovenous malformation\b|\bAVM\b)'
            ,'carotid?\\s*?\\w*\\s*dissection'
            //TODO currently setting 'disease' to middle, may set back to disease pending precision

        ]
        concept_feature_value = "Disease:other"
        outputType = "gov.va.vinci.types.Disease"
    }

/*
    "Middle" {
        expressions = [

                'disease'
                ,'Doppler\\s*'
                ,'in\\s*the\\s*'
                ,'of\\s*(the\\s*)?proximal\\s*'
                ,'of\\s*the\\s*'
                ,'of\\s*'
                ,'THERE IS\\s*'
                ,',\\s*'

        ]
        concept_feature_value = "Middle"
        outputType = "gov.va.vinci.types.Middle"
    }
*/

}





