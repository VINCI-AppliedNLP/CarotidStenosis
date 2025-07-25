package listeners




import gov.va.vinci.listeners.BasicDatabaseListener
import gov.va.vinci.leo.tools.LeoUtils
import gov.va.vinci.types.Ratio_Pattern
import gov.va.vinci.types.Stenosis_Pattern


int batchSize = 1000

String url = "jdbc:sqlserver://vhacdwrb03:1433;databasename=VINCI_Cardiac;integratedSecurity=true"
String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
String dbUser = ""
String dbPwd = ""

String dbsName = "VINCI_Cardiac"
String timeStamp = LeoUtils.getTimestampDateUnderscoreTime().substring(0, 8);


//String tableName = "[ETL].[Stenosis_Notes_Radiology_temp_output]"
String tableName = "[ETL].[Stenosis_Notes_TIU_temp_output]"


fieldList = [

        ["text_id", "0", "varchar(200)"],
        ["Sourcetbl", "1", "varchar(200)"],
       //["DocumentID", "2", "bigint"],
        ["Pattern_Text", "-1", "varchar(500)"],
        ["Laterality", "-1", "varchar(300)"],
        ["Ratio_Term", "-1", "varchar(300)"],
        ["Ratio_1", "-1", "varchar(300)"],
        ["Ratio_2", "-1", "varchar(300)"],
        ["Ratio_Descriptor", "-1", "varchar(300)"],
        ["Ratio_Qualifier", "-1", "varchar(300)"],
        ["Section", "-1", "varchar(300)"],
        ["SpanStart", "-1", "int"],
        ["SpanEnd", "-1", "int"]
       // ["Snippets", "-1", "varchar(5000)"]

]

boolean dropExisting = false;
listener = BasicDatabaseListener.createNewListener(driver, url, dbUser, dbPwd, dbsName,
        tableName, batchSize, fieldList,

        Ratio_Pattern.getCanonicalName()
        )

//create table
listener.createTable(dropExisting)
