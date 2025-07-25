package listeners


import gov.va.vinci.listeners.ChexListener
import gov.va.vinci.leo.tools.LeoUtils
import gov.va.vinci.types.Stenosis_Pattern


String timeStamp = LeoUtils.getTimestampDateDotTime().replaceAll("[.]", "_")


String chexSchema = "validation"  //
String chexSuffix = "_fourth_chex_iteration_" + timeStamp.substring(0, 8) // Change the suffix for each run, otherwise the data WILL BE OVERWRITTEN!
def chexTypes= [Stenosis_Pattern.class.getCanonicalName()] // when blank, SimanListener outputs all annotations


boolean chexOverwrite = true
String chexDocumentTextSelectQuery = ""
String chexColumnPrefix = "["
String chexColumnSuffix ="]"
int batchSize = 1000
String url = "jdbc:sqlserver://vhacdwrb03:1433;databasename=VINCI_Cardiac;integratedSecurity=true"
String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"



listener = ChexListener.newChexListener(
        driver,
        url,
        chexDocumentTextSelectQuery,
        chexSchema,
        chexSuffix,
        chexColumnPrefix,
        chexColumnSuffix,
        chexTypes,
        batchSize,
        chexOverwrite)
