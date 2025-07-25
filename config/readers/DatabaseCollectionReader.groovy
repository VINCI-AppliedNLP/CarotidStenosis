package readers
String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
String url = "jdbc:sqlserver://your-server:1433;databasename=your_database;integratedSecurity=true"


String query =
      ''' Select procID as text_id, TextSource as Sourcetbl, reporttext  From  
[VINCI_Cardiac].[nlp].[ana_ratio_train_test_notes_2023]
where reporttext is not null
'''
  
reader = new gov.va.vinci.leo.cr.DatabaseCollectionReader(driver, url,
        "", "", query, "text_id","reporttext"
);


