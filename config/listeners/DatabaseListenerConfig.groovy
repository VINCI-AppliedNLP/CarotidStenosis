package listeners




import gov.va.vinci.listeners.BasicDatabaseListener
import gov.va.vinci.leo.tools.LeoUtils
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
//String tableName = "[your_schema].[stenosis_output]"
String tableName = "[your_schema].[stenosis_output_" + timeStamp + "]"


fieldList = [


        ["text_id", "0", "varchar(200)"],
        ["Sourcetbl", "1", "varchar(200)"],

        ["Pattern_Text", "-1", "varchar(250)"],
        ["Anatomy_Type", "-1", "varchar(100)"],
        ["Anatomy_Term", "-1", "varchar(300)"],
        ["Disease_Type", "-1", "varchar(100)"],
        ["Disease_Term", "-1", "varchar(300)"],
        ["Procedure_Type", "-1", "varchar(300)"],
        ["Procedure_Term", "-1", "varchar(300)"],
        ["Section", "-1", "varchar(300)"],
        ["Laterality_Type", "-1", "varchar(100)"],
        ["Laterality_Term", "-1", "varchar(300)"],
        ["Severity_Term_Type", "-1", "varchar(100)"],
        ["Severity_Term", "-1", "varchar(300)"],
        ["Percent_1", "-1", "varchar(300)"],
        ["Percent_2", "-1", "varchar(300)"],
        ["Percent_Descriptor", "-1", "varchar(300)"],
        ["Ratio_1", "-1", "varchar(300)"],
        ["Ratio_2", "-1", "varchar(300)"],
        ["Anchored_Sentence", "-1", "varchar(5000)"],

        ["SpanStart", "-1", "int"],
        ["SpanEnd", "-1", "int"],

]

boolean dropExisting = false;
listener = BasicDatabaseListener.createNewListener(driver, url, dbUser, dbPwd, dbsName,
        tableName, batchSize, fieldList,

        Stenosis_Pattern.getCanonicalName()
        )

//create table
listener.createTable(dropExisting)
