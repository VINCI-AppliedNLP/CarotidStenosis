package resources

import gov.va.vinci.leo.regex.types.RegularExpressionType

/* An arbitrary name for this annotator. Used in the pipeline for the name of this annotation. */
name = "Simple Concept Terms"

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
	Laterality
	Vessel
	Condition
		Severity
	Location

 */

    "Pad_Concepts" {
        expressions = [

                '(distal|proximal)\\s*(aorto|aorto-bi|bi\\s*)?ill?iac(artery|arteries)?',
                'aortic',
                'aortic',
                'anterior\\s*tibial',
                'posterior\\s*tibial',
                'EXTERNAL\\s*ILIAC\\s*ARTERY'

        ]
        concept_feature_value = "Anatomy:Other"
        outputType = "gov.va.vinci.types.Anatomy"
    }

    "Anatomy_distal" {
        expressions = [

                'distal',
                //'dorsalis',
               // 'pedal',

        ]
        concept_feature_value = "distal"
        outputType = "gov.va.vinci.types.Anatomy_Location"
    }
    "Anatomy_Location" {
        expressions = [


                'proximal',

        ]
        concept_feature_value = "proximal"
        outputType = "gov.va.vinci.types.Anatomy_Location"
    }

    "Pad_femoral" {
        expressions = [

                'femoral',
                'common\\s*femoral\\s*artery',
                'profunda\\s*femoral\\s*artery',
                '(deep|superficial)\\s*femoral\\s*arter(ies|y)',
                'SFA\\b',


        ]
        concept_feature_value = "Anatomy_Vessel:Femoral"
        outputType = "gov.va.vinci.types.Anatomy"
    }

    "Pad_artery" {
        expressions = [

                'arter(y|ies)',

        ]
        concept_feature_value = "Artery:NonDescript"
        outputType = "gov.va.vinci.types.Anatomy"
    }


    "Anatomy_Interal" {
        expressions = [

                //'(internal carotid( artery)?|ICA |\bica\b)'
                'internal\\s*carotid',
                'internal\\s*carotid\\s*artery',
                'proximal\\s*internal\\s*carotid',
                //'carotid(\\s*artery)'
                '\\bICA\\b',
                '\\bLICA',
                '\\bRICA',
                '\\bICA\\b',
                ' IC/CC',
                'proximal\\s*ICA ',
                'internal\\s*to\\s*common\\s*carotid\\s*artery'

        ]
        concept_feature_value = "Internal_Carotid"
        outputType = "gov.va.vinci.types.Anatomy"
    }

    "Anatomy_Interal_plural" {
        expressions = [

                //When describe as plural, logic annotator sets laterality to bilateral
                'internal\\s*carotid\\s*arteries',
                'ICA\'s',
                'both\\s*ICAs',
                'proximal\\s*internal\\s*systems',
                'proximal\\s*internal\\s*arteries',
                'proximal\\s*internal\\s*and\\s*external\\s*carotid\\s*arteries',

        ]
        concept_feature_value = "Internal_Carotid_plural"
        outputType = "gov.va.vinci.types.Anatomy"
    }


    "Anatomy_NonSpecific" {
        expressions = [

                //'(internal carotid( artery)?|ICA |\bica\b)'

                'carotid(\\s*artery)',
                'carotid\\s*system',
                'carotid',
                'internal,\\s*external\\s*and\\s*common\\s*carotid',


        ]
        concept_feature_value = "NonSpecific_Carotid"
        outputType = "gov.va.vinci.types.Anatomy"
    }

    "Anatomy_Other" {
        expressions = [
                //Keeping external and common extracted to disambiguate cases when possible
                //'(internal carotid( artery)?|ICA |\bica\b)'
                //'internal carotid(\\s*artery)'
                //'carotid(\\s*artery)'
                //,' ICA '
                'VERTEBRAL\\s*ARTERIES',
                'vertebral\\s*artery',
                'extracranial',
                 'common\\s*carotid(\\s*artery)?',
                 '\bcca\b',
                 'common\\s*carotid(\\s*artery)?',
                // ,' CCA '
                //,'(bulb|carotid bulb)'
                // ,'bulb'
                'carotid bulb',
                'ECA',
                'external carotid(\\s*artery)?'
                //,'(BCA |\bbca\b|brachicephalic artery)'
                //,'brachicephalic artery'
                //,'SCA'
                //,'(SCA|\bsca\b|subclavian artery)'
                // ,'subclavian artery'
                //,'(LSCA|\blsca\b|left subclavian artery)'
                //  ,'left subclavian artery'
                //,'(RSCA|\brsca\b|right subclavian artery)'
                // ,'right subclavian artery'
        ]
        concept_feature_value = "Carotid_other"
        outputType = "gov.va.vinci.types.Anatomy"
    }




}






