package readers
String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
String url = "jdbc:sqlserver://your-server:1433;databasename=your_database;integratedSecurity=true"


String query =
      ''' Select document_id as text_id, source_table as Sourcetbl, report_text  From  
[your_database].[your_schema].[carotid_stenosis_documents]
where report_text is not null
'''
  
reader = new gov.va.vinci.leo.cr.DatabaseCollectionReader(driver, url,
        "", "", query, "text_id","report_text"
);


