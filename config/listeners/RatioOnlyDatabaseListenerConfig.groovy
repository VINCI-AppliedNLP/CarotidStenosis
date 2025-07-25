package listeners




import gov.va.vinci.listeners.BasicDatabaseListener
import gov.va.vinci.leo.tools.LeoUtils
import gov.va.vinci.types.Ratio_Pattern
import gov.va.vinci.types.Stenosis_Pattern


int batchSize = 1000

// Example database configuration - update with your database details
String url = "jdbc:sqlserver://your-server:1433;databasename=your_database;integratedSecurity=true"
String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
String dbUser = ""
String dbPwd = ""

String dbsName = "your_database"
String timeStamp = LeoUtils.getTimestampDateUnderscoreTime().substring(0, 8);

// Example output table names - update based on your schema
//String tableName = "[your_schema].[stenosis_ratio_output]"
String tableName = "[your_schema].[stenosis_ratio_output_" + timeStamp + "]"


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
