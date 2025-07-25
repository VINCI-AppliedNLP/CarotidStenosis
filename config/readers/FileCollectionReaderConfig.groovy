package readers

import gov.va.vinci.leo.cr.FileCollectionReader;

// Path to directory containing input text files
// Update this path to point to your input data directory
String pathToFiles = "data/input"
// Alternative examples:
//String pathToFiles = "/path/to/your/medical/reports"
//String pathToFiles = "C:\\data\\carotid_reports"

// Set to true to process files in subdirectories recursively
boolean recurse = false

reader = new FileCollectionReader(new File(pathToFiles), recurse);

