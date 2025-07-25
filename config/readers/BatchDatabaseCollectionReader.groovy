import gov.va.vinci.leo.cr.BatchDatabaseCollectionReader;

String dbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

// Example database connection - update with your database details
String url = "jdbc:sqlserver://your-server:1433;databasename=your_database;integratedSecurity=true"

// keep username and password blank for Windows authentication
String username = "";
String password = "";

// Example queries for different data sources
// Uncomment and modify as needed for your database schema

//String query = "select document_id, report_text, row_number from sample_documents where row_number >{min} and row_number<{max}";
//String query = "select text_id, report_text, row_number from radiology_reports where row_number >{min} and row_number<{max}";

// Example ETL Processing Query
String query = ''' select a.DocumentID, 'RADIOLOGY' as source_table, report_text, a.row_number  
 from   
 your_schema.carotid_stenosis_documents a 
 join your_schema.document_text c 
 on a.DocumentID=c.DocumentID
 where row_number >{min} and row_number<{max} 
 and c.report_text is not null 
 
'''





String docIDColumnName = "rowno"
String docTextColumnName = "reporttext"

int startingIndex = 0;
int endingIndex = 100000;
int batchSize = 5000;

reader = new BatchDatabaseCollectionReader(dbDriver, url ,
        "", "",
        query,
        docIDColumnName.toLowerCase(),
        docTextColumnName.toLowerCase(),
        startingIndex, endingIndex,
        batchSize);

