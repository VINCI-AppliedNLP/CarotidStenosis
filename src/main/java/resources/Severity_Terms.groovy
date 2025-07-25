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
// TODO Extract Percentage as just a value instead of the normalized case here


    "Percent_value" {
        expressions = [

               // '\\d{0,3}(%)?\\s*(-|to)*\\s*\\d{2,3}\\s*(%|percent)'
                '\\d{1,3}'

        ]
        concept_feature_value = "Percent"
        outputType = "gov.va.vinci.types.Percent_Value"
    }

    "Ratio_value" {
        expressions = [

                // '\\d{0,3}(%)?\\s*(-|to)*\\s*\\d{2,3}\\s*(%|percent)'

                '\\d\\.\\d\\d?',
                //it can not have another number in front of it
                '0\\d\\.\\d\\d?',
                '\\d\\.\\d\\d?',
                '\\d\\.\\d',
                '(\\b| )\\.\\d',
                //ICA/CCA is
                // equals [2.2].
                '\\[\\s*\\d\\.\\d\\s*\\]',
                //[ 1.3]
                // input 89/23
                // ratio component comp1, comp2
                // ratio value pattern comp1/comp2
                // if ratio value is pattern, then set value as comp1/comp2
                // output 3.87
                '\\d\\d\\d?/\\d\\d\\d?',
                '\\d\\d\\d?/\\d\\d\\d?',
                '\\d\\d\\d?/\\d\\d\\d?',
                // 60.7/68.9
                '\\d\\d\\.\\d/\\d\\d\\.\\d',
                //117.2/69.3
                '\\d\\d\\d\\.\\d/\\d\\d\\.\\d',
                //Strict acceptance on larger numbers (frequently incorrect)
                '\\b0\\b',
                '\\b1\\b',
                '\\b2\\b',
                '\\b3\\b',
                '\\b4\\b',
                '\\b5\\b',
                '\\b6\\b',
                '\\b7\\b',
                '\\b8\\b',
                '\\b9\\b',
                '\\b10\\b',
                '\\b10\\.\\d\\b',
                '\\b11\\b',
                '\\b11\\.\\d\\b',
                '\\b12\\b',
                '\\b12\\.\\d\\b',
                '\\b13\\b',
                '\\b13\\.\\d\\b',
                '\\b14\\b',
                '\\b14\\.\\d\\b',
                '\\b15\\b',
                '\\b15\\.\\d\\b',
                '\\b16\\b',
                '\\b16\\.\\d\\b',
                '\\b17\\b',
                '\\b18\\b',
                '\\b19\\b',
                '\\b20\\b',
                '\\b20\\.\\d\\b',
                //Not really seeing larger numbers discussed as text
                'one',
                'two',

        ]
        concept_feature_value = "ratio"
        outputType = "gov.va.vinci.types.Ratio_Value"
    }
    "Ratio_value_exclusion" {
        expressions = [

                // '\\d{0,3}(%)?\\s*(-|to)*\\s*\\d{2,3}\\s*(%|percent)'
                //Two digit floats aren't possible ration patterns (double check on the actual max)
                '\\d\\d\\.\\d\\d?',
                //it can not have another number infront of it

        ]
        concept_feature_value = "ratio_exclude"
        outputType = "gov.va.vinci.types.Ratio_Value_Exclusion"
    }

    "Ratio_normal" {
        expressions = [

                // '\\d{0,3}(%)?\\s*(-|to)*\\s*\\d{2,3}\\s*(%|percent)'
                'normal',
                'unremarkable',
                'normal\\s*(measuring|at|showing)',
                'within\\s*normal\\s*limits',
                'within\\s*normal\\s*range',
                'within\\s*normal\\s*limits\\s*(measuring|at|showing)',
                //IC/CC ratio is upper limits of normal at 1.40.
                '(upper|lower)\\s*limits\\s*of\\s*normal(\\s*at)?',
                'near\\s*the\\s*(upper|lower)\\s*limits\\s*of\\s*normal'
                //Full text version put here and treated as normal instead of as a specific value



        ]
        concept_feature_value = "normal"
        outputType = "gov.va.vinci.types.Ratio_Qualifier"
    }
    "Ratio_increased" {
        expressions = [

                // '\\d{0,3}(%)?\\s*(-|to)*\\s*\\d{2,3}\\s*(%|percent)'

                'increased\\s*(to|measuring|showing|at|,)',
                'is\\s*elevated\\s*(to|measuring|showing|at|,)',
                'is\\s*abnormally\\s*elevated\\s*(to|measuring|showing|at|,)',


        ]
        concept_feature_value = "increased"
        outputType = "gov.va.vinci.types.Ratio_Qualifier"
    }
    "Ratio_decreased" {
        expressions = [

                // '\\d{0,3}(%)?\\s*(-|to)*\\s*\\d{2,3}\\s*(%|percent)'

                'decreased\\s*(to|measuring|showing|at)'


        ]
        concept_feature_value = "deacreased"
        outputType = "gov.va.vinci.types.Ratio_Qualifier"
    }
    "Ratio_abnormal" {
        expressions = [

                // '\\d{0,3}(%)?\\s*(-|to)*\\s*\\d{2,3}\\s*(%|percent)'

                'abnormal'


        ]
        concept_feature_value = "abnormal"
        outputType = "gov.va.vinci.types.Ratio_Qualifier"
    }
    "Percent_Descriptor_greater" {
        expressions = [

                // '\\d{0,3}(%)?\\s*(-|to)*\\s*\\d{2,3}\\s*(%|percent)'
                '>',
                'greater\\s*than',
                'slightly\\s*greater\\s*than'

        ]
        concept_feature_value = "GreaterThan"
        outputType = "gov.va.vinci.types.Percent_Descriptor"
    }
    "Percent_Descriptor_less" {
        expressions = [

                // '\\d{0,3}(%)?\\s*(-|to)*\\s*\\d{2,3}\\s*(%|percent)'
                '<',
                ',?Less\\s*Than',
                'slightly\\s*less\\s*than',
                'below',
                'or\\s*less',


        ]
        concept_feature_value = "LessThan"
        outputType = "gov.va.vinci.types.Percent_Descriptor"
    }

    "Percent_Descriptor_approximate" {
        expressions = [

                // '\\d{0,3}(%)?\\s*(-|to)*\\s*\\d{2,3}\\s*(%|percent)'
                'approximate',
                'approximately',
                '~',
                'estimated\\s*to\\s*be'

        ]
        concept_feature_value = "Approximately"
        outputType = "gov.va.vinci.types.Percent_Descriptor"
    }


    /*Combinations are set to the higher of the two.
    e.g mild to moderate = moderate

     */
    "CAROTID_NONCRITICAL_Term" {
        expressions = [

                'minimal'
                ,'mild'
                ,'normal'
                ,'WITHIN NORMAL LIMITS',
                'Minimal spectral broadening'


        ]
        concept_feature_value = "Disease:Mild"
        outputType = "gov.va.vinci.types.Severity_Term"
    }
    "CAROTID_Moderate" {
        expressions = [

                'moderate'
                ,'mild to moderate'
                ,'Mild-to-moderate'
                ,'Significant stenosis'
                ,'Moderate spectral broadening'




        ]
        concept_feature_value = "Disease:Moderate"
        outputType = "gov.va.vinci.types.Severity_Term"
    }




    "CAROTID_strandness" {
        expressions = [

               'strandness\\s*class\\s*A'
               ,'strandness\\s*class\\s*B'
               ,'Strandness class B \\(1-15% diameter\\s*stenosis\\)'
               ,'strandness\\s*class\\s*C'
               ,'strandness\\s*class\\s*D'


        ]
        concept_feature_value = "CAROTID_strandness"
        outputType = "gov.va.vinci.types.Severity_Term"
    }

    "CAROTID_CRITICAL_Term" {
        expressions = [


                //, 'Moderate \\(50-79%\\) '

                 'moderate to severe'
                , 'moderate\\s*-\\s*severe'
                , 'significant'
                , 'high(-|\\s*)grade'
                , 'High-grade hemodynamically significant'
                ,'High-grade hemodynamically significant'
                , 'severe'
                ,'SEVERE\\s*TO\\s*NEAR-CRITICAL'
                , 'complete'
                , 'occluded'
                , 'Chronic\\s*occlusion'
                , 'evidence of total occlusion'
                , 'total occlusion'
                , 'complete\\s*occlusion'
                , 'no\\s*flow'
                , 'showed no flow'
                , 'poorly visualized and showed no flow'
                , 'EXTENSIVE\\s*AMOUNT OF ECHOGENIC SHADOWING PLAQUE'
                , 'EXTENSIVE\\s*ECHOGENIC SHADOWING PLAQUE'
                , 'EXTENSIVE\\s*SHADOWING PLAQUE'


        ]
        concept_feature_value = "Disease:CRITICAL"
        outputType = "gov.va.vinci.types.Severity_Term"
    }

    "Carotid_ratio" {
        expressions = [


                //TODO Set middle stuff/ratio pattern types
                //terms

                'ICA/CCA',
                'ICA?/CCA?\\s*RATIOs?',
                'ICa?/CCa?\\s*systolic',
                'ICa?/CCa?\\s*systolic\\s*ratios?',
                'Ratios?',
                'Ratio\\s*and\\s*velocities',
                //With space
                'ICA?\\s*/\\s*CCA?\\s*ratios?',
                //missed ICA
                'CCA\\s*Ratios?',
                'ICa?/CCa?\\s*ratios?\\s*\\(EDV\\)',
                'internal\\s*carotid-to-common carotid artery ratios',
                'internal\\s*carotid\\s*to\\s*common\\s*carotid\\s*artery\\s*ratios?',
                'peak\\s*systolic\\s*velocity\\s*ratio\\s*of\\s*the\\s*internal\\s*carotid\\s*artery\\s*to\\s*common\\s*carotid\\s*artery',
                'ratio\\s*of\\s*the\\s*velocity\\s*of\\s*the\\s*common\\s*carotid\\s*/\\s*internal\\s*carotid\\s*artery',
                'ratio\\s*of\\s*the\\s*velocity\\s*common\\s*carotid\\s*(/|and)\\s*internal\\s*carotid\\s*artery',
                'VICA/VCCA',
                'VICA/VCCA\\s*ratios?',
                //'IC/CC\\s*ratio',
                //'ICA/CCA\\s*ratio',
                'ICa?/CCa?\\s*\\(ratios?\\) ',
                //'IC/CC\\s*\\(ratio\\) ',
               'internal\\s*carotid/common\\s*carotid\\s*artery\\s*ratios?',
                'internal\\s*to\\s*common\\s*carotid\\s*artery\\s*peak\\s*systolic\\s*velocity\\s*ratios?',
                'Peak\\s*systolic\\s*velocity\\s*ratio\\s*in\\s*the\\s*(right|left)\\s*internal\\s*carotid\\s*artery',
                'velocity\\s*ratio\\s*between\\s*the\\s*(right|left)\\s*internal\\s*carotid\\s*artery\\s*and\\s*the\\s*(right|left)\\s*common\\s*carotid\\s*artery',
                'PEAK\\s*SYSTOLIC\\s*VELOCITY\\s*RATIOs?',
                //TODO: Identify/separate ICA/ECA ratios (including the value modifier here)
                'ICA/ECA,\\s*>',
                //The velocity ratio on the right is 1.43 indicating about 35 to 40% stenosis of the right
                'The\\s*velocity\\s*ratio',
               // 'The\\s*velocity\\s*ratio\\s*on\\s*the\\s*(right|left)',
                //ICA/CCA ratio on the right is 1.22.
                //'ICA/CCA\\s*ratio\\s*on\\s*the\\s*(right|left|right\\s*side|left\\s*side)\\s*is',
                //ICA/CCA PSV ratio:  2.48.
                'ICA?/CCA?\\s*PSV\\s*ratios?',
                'ICA\\s*to\\s*CCA\\s*ratios?',
                'ICA\\s*to\\s*CCA\\s*systolic\\s*ratios?',
                //ICA/CCA RATIO     3.9
                //The left ICA/CCA systolic velocity ratio measures 0.8.
                'ICA/CCA\\s*systolic\\s*velocity\\s*ratios?',
                'CCA\\s*systolic\\s*velocity\\s*ratios?',
                '(velocity\\s*)?ratio',
                //  0.99 RIGHT ICA/CCA RATIO
                //TODO Occaionsl Left sided ratio ... will want to investigate before creating pattern
                '\\d\\.\\d\\d?\\s*(RIGHT|left)\\s*ICA/CCA\\s*RATIO',
                //TODO, should be captured by complete patterns now
                'Normal\\s*bilateral\\s*ICA\\s*to\\s*CCA\\s*ratios',
                'ICA/CCA\\s*ratio\\s*in\\s*systole',
                'velocity\\s*ratio\\s*\\(ICA/CCA\\)',
                'ICA/CCA\\s*peak\\s*systolic\\s*velocity\\s*ratios?',
                //Added 04-14
                'ICA\\s*to\\s*CCA',
                'ICA/CCA\\s*peak\\s*systolic\\s*ratios?',
                'PSV\\s*ICA?/CCA?\\s*ratios',
                //'CCA:ICA\\s*ratio',
                'ICA?/CCA?\\s*Peak\\s*systolic\\s*ratios?',
                'ICa?/CCa?\\s*peak\\s*systolic\\s*velocit(ies|y)\\s*ratios',
                'Common\\s*carotid\\s*to\\s*internal\\s*carotid\\s*ratio',
                'ica\\s*cca\\s*ratio',
                //TODO LAterality Specific terms
                'LICA/LCCA\\s*ratio',
                'RICA/RCCA\\s*ratio',
                //TODO, teview - added 2022-06-28
                'LICA\\s*\\(PSV\\)\\s*/LCCA\\(PSV\\)',
                'RICA\\s*\\(PSV\\)\\s*/RCCA\\(PSV\\)',
                'ICA/CCA\\s*Peak\\s*systolic\\s*velocity\\s*ratios ',
                'ICA/distal\\s*CCA\\s*peak\\s*systolic\\s*velocity\\s*ratio',
                'ICA/CC\\s*ratio',
                'ICA?/CCA?\\s*SYST\\s*VEL\\s*RATIO',
                /*
                ADDITIONAL ITERATIVE HUNT AND PECK PATTERNS BELOW
                Should still consider iteration on a pattern annotator
                */
                //Post 2022-06-22 Review
                'ICA/CCA\\s*PS(\\s*Ratios?)?',
                //ADDED 2022-07-11
                'ICA?/CCA?\\s*velocity\\s*ratios?',
                'peak\\s*systolic\\s*velocity\\s*ratio\\s*\\(ICA/CCA\\)',
                'SVR\\(ICA/CCA\\)',
                // The left ICA/CCA ratio in systole is .8 and in diastole 1.5.
                'ICA?/CCA?\\s*ratio\\s*in\\s*systole',
                'ICA/CCA\\s*systolic\\s*ratios?',
                'ICA?:CCA?',
                'ICA:CCA\\s*RATIOs?',
                'ICA\\s*over\\s*CCA\\s*ratio',
                'ICA\\s*over\\s*CCA',
                'RICA/CCA',
                'LICA/CCA',
                'internal/common\\s*ratio',
                '\\(ICA/CCA\\)',
                'ICA/CCA systolic:',
                'ICA/CCA\\s*ratio\\s*\\(PS\\):',
                'ICA/CCA:\\s*Systolic',
                //Overlap should remove this 2.0 value
                'ICA/CCA\\s*\\(normal less than 2.0\\):',
                'ICA/CCA\\s*\\(right\\):',
                'ICA/CCA\\s*\\(left\\):',
                'ICA/CCA\\s*index',
                'Systolic\\s*ICA/CCA\\s*Velocity\\s*ratios',
                'ICA/CCA\\s*systolic\\s*Velocity\\s*ratios',
                'ICA/CCA\\s*RATIOS\\s*\\(normal < 2\\):',
                'ICA\\s*to\\s*CCA\\s*ratios',
                'ICA/CC\\s*ratios',
                'ICA/CCA:\\s*Peak\\s*systolic',
                'IC/CC\\s*ratios',
                // systolic and diasolic ratios are normal (can keep diastolic here when both are mentioned)
                'systolic\\s*and\\s*diasolic\\s*ratios',
                'ICA//CCA\\s*ratios?',
                //keeping middle term here
                'ICA/CCA\\s*RATIO:',
                'ICA/CCA\\s*PSV',
                'ICA/CCA\\s*ratios',
                'ICA/CCA\\s*ratio\\s*systole',
                'Peak systolic velocity ratio of internal\\s*carotid artery/common carotid artery',
                'ICa?/CCa?\\s*ratios\\s*throughout\\s*the\\s*right\\s*internal\\s*carotid\\s*artery\\s*are(\\s*all)?',
                'IC/CCA\\s*ratios?',
                'ICA:\\s*CCA\\s*Ratios?',
                'ratio of the peak systolic velocity of the right internal\\s*carotid artery to the peak systolic velocity of the right common\\s*carotid artery',
                'Common\\s*carotid\\s*to\\s*internal\\s*carotid\\s*flow\\s*ratios?',
                'internal/common\\s*carotid\\s*ratio',
                'PSA\\s*ratio\\s*\\(ICA?/CCA?\\)',
                'peak\\s*systolic\\s*carotid\\s*artery\\s*ratio',
                'systolic\\s*velocity\\s*ratio\\s*between\\s*the\\s*left\\s*internal\\s*carotid\\s*artery\\s*and\\s*the\\s*right\\s*common\\s*carotid\\s*artery',
                'ratio\\s*between\\s*the\\s*left\\s*internal\\s*carotid\\s*artery\\s*and\\s*the\\s*right\\s*common\\s*carotid\\s*artery',
                'ICA\\s*to\\s*CCA\\s*ratio\\s*of\\s*the\\s*peak\\s*systolic\\s*velocities',
                'Ratio\\s*of\\s*peak\\s*systolic\\s*velocity\\s*of\\s*the\\s*distal\\s*ICA\\s*to\\s*the\\s*distal\\s*CCA',
                //Any additional Gina notes
                'IC to CC ratio'


        ]
        concept_feature_value = "Ratio"
        outputType = "gov.va.vinci.types.Ratio_Term"
    }


    "Diastolic_Ratio" {
        expressions = [

                //Only interested in Systolic Ratios for the carotid stenosis study, setting diastolic as separate category
                'ICA/CCA\\s*end\\s*diastolic\\s*RATIO',
                'ICA/CCA\\s*diastolic\\s*RATIO',
                /*
                    ICA/CCA SYST VEL RATIO: 1.0        ICA/CCA SYST VEL RATIO: 1.1
                ICA/CCA end diastolic ratio: 1.5   ICA/CCA end diastolic ratio: 1.2

         */
                //Post 2022-06-22 Review
                'ICA/CCA\\s*ED(\\s*Ratio)?',
                'ICA/CCA\\s*ratio\\s*\\(ED\\):',
                //TODO - look into expanding/search of 'EDV' concepts here
                'ICA/CCA\\s*EDV\\s*RATIO',
                'ICA/CCA\\s*diastolic:',
                'ICA/CCA\\s*diastolic\\s*velocity\\s*ratios?'

        ]
        concept_feature_value = "Ratio_Diastolic"
        outputType = "gov.va.vinci.types.Ratio_Term"
    }


    "None" {
        expressions = [

                'no Doppler evidence',
                'no\\s*evidence\\s*of',
                'NO\\s*EVIDENCE\\s*(FOR|of)\\s*(HEMODYNAMICALLY|surgically)\\s*SIGNIFICANT',
                'no\\s*Doppler\\s*evidence\\s*for\\s*significant',
                'without\\s*evidence\\s*(of|for)\\s*hemodynamically\\s*significant',
                'non(-|\\s*)?hemodynamically\\s*significant',
                'NO\\s*EVIDENCE\\s*OF\\s*(A\\s*)?HEMODYNAMICALLY\\s*SIGNIFICANT',
                'exam does not suggest hemodynamically significant',
                '(do|does)\\s*not\\s*result\\s*in\\s*hemodynamically\\s*significant',
                'does\\s*not\\s*suggest\\s*hemodynamically\\s*significant',
                'No\\s*carotid\\s*duplex\\s*findings\\s*of\\s*hemodynamically\\s*significant',
                '\\bno ',
                'showed\\s*no',
                'without',
                'free of ulcerations and',
                'scattered\\s*atheromatous\\s*plaques',
                'No\\s*ultrasonographic\\s*evidence',
                'No\\s*ultrasonographic\\s*evidence\\s*of',
                'flow within normal limits',
                'are within normal limits',
                'Normal flow',
                //will likely need to separate as separate condition that rolls up to stenosis
                'No spectral broadening',
                'no\\s*flow\\s*limiting'



        ]
        concept_feature_value = "None"
        outputType = "gov.va.vinci.types.Severity_Term"
    }


    "No Significant" {
        expressions = [




                'no\\s*critical',
                'no\\s*significant',
                'no\\s*flow-limiting',
                'No\\s*hemodynamic(ally)?\\s*significant\\s*stenosis',
                'NO\\s*CLINICALLY\\s*SIGNIFICANT',
                'NO\\s*SONOGRAPHIC\\s*EVIDENCE\\s*OF\\s*HEMODYNAMICALLY\\s*SIGNIFICANT',
                '(no|showed\\s*no|without|non-?)\\s*hemodynamically\\s*significant',
                'no\\s*hemodynamically\\s*significant',
                'no\\s*evidence\\s*of\\s*hemodynamically\\s*significant',
                'no\\s*evidence\\s*of\\s*hemodynamically\\s*significant',
                'No evidence of significant',
                'no\\s*signs\\s*of\\s*significant',
                'no\\s*signs\\s*of\\s*significant\\s*segmental',
                'no\\s*signs of significant segmental',
                'This plaque did not appear to narrow the vessel lumen significantly',
                'plaque did not appear to narrow',
                'fails to demonstrate any significant'


        ]
        concept_feature_value = "No_Significant"
        outputType = "gov.va.vinci.types.Severity_Term"
    }

    "Middle" {
        expressions = [

              //  'disease'
                'Doppler\\s*'
                ,'in\\s*the\\s*'
                //,'of\\s*(the\\s*)?proximal\\s*'
                ,'of\\s*the\\s*'
                ,'of\\s*'
                ,'THERE IS\\s*'
                //TODO review the removal of this.  Was causing merge issues with multiple diseases, but may cause other issues/missed patterns
                //,',\\s*'
                ,'-'

        ]
        concept_feature_value = "Middle"
        outputType = "gov.va.vinci.types.Middle"
    }

    "Ratio_Middle" {
        expressions = [

                //  'disease'
                // Duplicate of middle terms, but specific to ratio patterns which may have middle terms that should not be incorporated into the previous logic
                'Doppler\\s*'
                ,'in\\s*the\\s*'
                //,'of\\s*(the\\s*)?proximal\\s*'
                ,'of\\s*the\\s*'
                ,'of\\s*'
                ,'THERE IS\\s*'
                //TODO review the removal of this.  Was causing merge issues with multiple diseases, but may cause other issues/missed patterns
                //,',\\s*'
                ,'-'
                ,":",
                '(shows|of|is):?',
                '\\s*of\\s*approximately',
                '(reaches|shows|showing|is|were|are)\\s*approximately',
                'approximately',
                'reaches',
                '--',
                '=',
                'measuring',
                'measured',
                'measures?',
                'is',
                'is:',
                'were',
                'is\\s*a',
                'was',
                'are',
                'are\\s*also',
                'of',
                '(equals|=)',
                'is\\s*equal\\s*to',
                'at',
                'shows',
                'showing',
                'to',
                //'calculated\\s*to\\s*be',
                '(is\\s*)?calculated(\\s*to\\s*be|\\s*at)?',
                'demonstrated',
                '(is|are)\\s*demonstrated(\\s*to\\s*be|\\s*at)?',

        ]
        concept_feature_value = "Middle"
        outputType = "gov.va.vinci.types.Ratio_Middle"
    }

    "Ratio_Exclude" {
        expressions = [

                //  'disease'
                // Duplicate of middle terms, but specific to ratio patterns which may have middle terms that should not be incorporated into the previous logic
                'PSV of >125 cm/s and end diastolic velocity \\(EDV\\) <110 cm/s or\\s*ICA/CCA\\s*PSV\\s*ratio >2',
                'Pansystolic spectral broadening with PSV >230 cm/s or EDV >110\\s*cm/s or ICA/CCA PSV ratio',
                //Common Guideline/template exclusions
                'ICA/CCA Peak Systolic Ratios\\s*2:1 correlates with',
                'ICA/CCA Peak Systolic Ratios\\s*< 2 correlates with',
                'Classification:  Normal - no carotid plaque or stenosis, normal hemodynamics PSV <150,   EDV <60, ICA/CCA <1.7',
                'Mild stenosis \\(<40%\\) - mild plaque, normal hemodynamics PSV <150, EDV   <60, ICA/CCA <1.7 ',
                'Findings are consistent with mild bilateral stenosis    Normal - no carotid plaque or stenosis, normal hemodynamics PSV <150,   EDV <60, ICA/CCA <1.7',
                'Moderate stenosis \\(40% - 60%\\) - moderate plaque PSV <250, EDV <60,   ICA/CCA <1.7',
                'Severe stenosis \\(60% - 80%\\) - operative threshold for symptomatic   disease, severe plaque PSV >250, EDV >60, ICA/CCA >1.7',
                'Critical stenosis \\(80% - 99%\\) - operative threshold for asymptomatic   disease, severe plaque PSV >325, EDV >100, ICA/CCA >2.5'


        ]
        concept_feature_value = "Exclude"
        outputType = "gov.va.vinci.types.Ratio_Term"
    }


}





