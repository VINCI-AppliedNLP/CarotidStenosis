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





//Radiology ETL Processing Query
//270,000 docs to process 2024/06/03
/*
String query = ''' Select note.RadiologyNuclearMedicineReportSID, note.Sourcetbl, note.documentID, texts.ImpressionText as ReportText,  note.rowno 
 from   VINCI_Cardiac.etl.Stenosis_Notes_Radiology_to_process note  
 join   
 [cdwwork].[SPatientText].[RadiologyImpressions]  texts  
 on note.DocumentID=texts.RadiologyImpressionsSID  
 and note.Sourcetbl = 'ImpressionText'  
 and ImpressionText is not null 
 and rowno >{min} and rowno<{max} 
'''
*/

/*
String query =  ''' select  note.RadiologyNuclearMedicineReportSID, note.Sourcetbl, note.documentID, ReportText,  note.rowno 
 from   
 VINCI_Cardiac.etl.Stenosis_Notes_Radiology_to_process note  join   
 [cdwwork].[SPatientText].[RadiologyReportText] texts 
 on note.DocumentID=texts.RadiologyReportTExtSID 
 and note.Sourcetbl = 'ReportText' 
 and texts.ReportTExt is not null 
 and rowno >{min} and rowno<{max}
'''

 */


        /*
'''
Select a.ProcID, a.SourceTbl, a.reportTExt, a.rowno From 
 [nlp].[ratio_structure_format_notes] a
 --join  [nlp].[ratio_structure_simplified_format_20220711_requires_fix] b 
 --on a.procid=b.procid 
 where reporttext is not null 
 and rowno >{min} and rowno<{max} 

'''
*/
        /*'''

select a.[RadiologyNuclearMedicineReportSID], a.[Source], a.note as reporttext, b.rowno  
from   
[ORD_Damrauer_202109029D].[CST].[RadiologyDocuments] a
join [cst].[nlp_distinct_patients_ID_20230309] b
on a.patienticn=b.patienticn
where rowno >{min} and rowno<{max} 
and a.note is not null

'''

         */

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

