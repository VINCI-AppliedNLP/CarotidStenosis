package listeners

import gov.va.vinci.listeners.BasicCsvListener;
fieldList = [
        ["DocID", "-1", "bigint"],
      //  ["Pattern", "-1", "varchar(210)"],
        ["Snippets", "-1", "varchar(1000)"],
       // ["Concept", "-1", "varchar(50)"]
]

File filePath = new File("Data\\Output\\outputData.csv")
listener = new BasicCsvListener(filePath, true , fieldList, "gov.va.vinci.kttr.types.Positive");
listener.writeHeaders()
