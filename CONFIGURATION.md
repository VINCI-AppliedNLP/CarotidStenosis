# Configuration Guide

This document provides information about configuring the Carotid Stenosis NLP system for different data sources and output formats.

## Overview

The system uses Groovy configuration files to define:

- **Input sources** (files, databases)
- **Processing pipelines** (annotation types, patterns)
- **Output destinations** (databases, files)
- **System parameters** (memory, threading)

## Input Configuration

### File-based Input

Edit `config/readers/FileCollectionReaderConfig.groovy`:

```groovy
// Basic configuration
String pathToFiles = "data/input"
boolean recurse = false  // Set true to process subdirectories

// Advanced options
String fileExtension = ".txt"  // Process only specific file types
String encoding = "UTF-8"     // File encoding
```

### Database Input

Edit `config/readers/DatabaseCollectionReader.groovy`:

```groovy
// Database connection
String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
String url = "jdbc:sqlserver://your-server:1433;databasename=your_db"
String username = "your_username"
String password = "your_password"

// Query configuration
String query = """
    SELECT document_id, report_text, created_date
    FROM radiology_reports 
    WHERE report_type = 'carotid_duplex'
    ORDER BY created_date
"""

// Column mappings
String idColumnName = "document_id"
String textColumnName = "report_text"
```

### Batch Processing

For large datasets, use `config/readers/BatchDatabaseCollectionReader.groovy`:

```groovy
// Batch parameters
int batchSize = 1000
int maxRecords = 100000

// Query with pagination
String query = """
    SELECT document_id, report_text, row_number
    FROM radiology_reports 
    WHERE row_number > {min} AND row_number <= {max}
"""
```

## Output Configuration

### Database Output

Edit `config/listeners/DatabaseListenerConfig.groovy`:

```groovy
// Connection details
String url = "jdbc:sqlserver://your-server:1433;databasename=results_db"
String tableName = "[schema].[carotid_results]"

// Output fields
fieldList = [
    'document_id',
    'anatomy',
    'laterality', 
    'stenosis_severity',
    'stenosis_percentage',
    'ratio_value',
    'confidence_score'
]

// Batch settings
int batchSize = 1000
boolean createTable = true  // Auto-create output table
```

### CSV Output

Edit `config/listeners/CsvListenerConfig.groovy`:

```groovy
// Output file
String outputFile = "results/carotid_stenosis_results.csv"

// CSV format
String delimiter = ","
boolean includeHeader = true
String encoding = "UTF-8"

// Fields to include
fieldList = [
    'DocumentID',
    'ReportText', 
    'Anatomy',
    'Laterality',
    'StenosisType',
    'SeverityTerm',
    'PercentageValue',
    'RatioValue'
]
```

## Processing Configuration

### Annotation Patterns

The system uses regex patterns defined in `config/desc/` XML files. Key patterns include:

#### Anatomy Patterns

- Carotid artery references
- Internal vs external carotid
- Common carotid artery

#### Severity Patterns

- Qualitative terms (mild, moderate, severe)
- Percentage ranges (50-69%, 70-99%)
- Critical/occlusion indicators

#### Ratio Patterns

- ICA/CCA velocity ratios
- Peak systolic velocity measurements
- Doppler-specific terminology

### Custom Pattern Development

To add new patterns:

1. Create new XML descriptor in `config/desc/`
2. Define regex patterns with capture groups
3. Specify output annotation types
4. Add to pipeline configuration

Example pattern XML:

```xml
<regex>
    <pattern>(?i)(internal\s+carotid|ICA).*?(\d{1,2}-\d{1,2}%|\d{1,2}%)\s*stenosis</pattern>
    <outputType>gov.va.vinci.types.Stenosis_Pattern</outputType>
    <features>
        <feature>
            <name>Anatomy</name>
            <value>internal_carotid</value>
        </feature>
        <feature>
            <name>Percentage</name>
            <value>$2</value>
        </feature>
    </features>
</regex>
```

## Pipeline Configuration

### Service Configuration

Edit `config/ServiceConfig.groovy`:

```groovy
// UIMA AS Broker
brokerURL = "tcp://localhost:61616"

// Processing parameters
numberOfInstances = 4      // Parallel processing instances
casPoolSize = 10          // CAS object pool size
timeout = 300000          // Processing timeout (ms)

// Performance tuning
initialHeapSize = "2g"
maxHeapSize = "8g"
```

### Client Configuration

Edit `config/ClientConfig.groovy`:

```groovy
// Service endpoint
String serviceHost = "localhost"
int servicePort = 61616
String queueName = "carotidStenosisQueue"

// Client parameters
int casPoolSize = 5
int timeout = 60000
boolean async = true
```

## Advanced Configuration

### Custom Logic Annotators

To implement custom processing logic:

1. Create Java class extending `LeoBaseAnnotator`
2. Implement `annotate()` method
3. Add to pipeline in main service class
