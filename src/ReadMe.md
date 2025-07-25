# Carotid Stenosis Data Extraction System

This project contains NLP pipelines designed to extract clinical variables related to carotid stenosis from medical text, particularly radiology reports and clinical notes.

## System Overview

Building upon previous work to identify carotid stenosis severity using qualitative descriptions, this system extracts:
- **Explicit percentages** (e.g., "70-80% stenosis")
- **Qualitative severity terms** (e.g., "mild", "moderate", "severe")
- **Peak systolic velocity ratios** (ICA/CCA ratios)
- **Anatomical references** (internal carotid, common carotid)
- **Laterality** (left, right, bilateral)

The extracted values are combined in post-processing to create comprehensive stenosis assessments for each radiology report.

## Pipeline Architecture

The system implements two complementary pipelines:

### Pipeline 1: Qualitative Stenosis Severity and Percentage Extraction

This pipeline identifies and extracts:
- **Stenosis_Pattern** annotations with attributes for:
  - Anatomy (internal carotid artery, common carotid artery)
  - Disease type (stenosis, occlusion)
  - Severity (mild, moderate, severe, critical)
  - Percentage values (when explicitly stated)
  - Laterality (left, right, bilateral)

#### Processing Logic:
1. **Anatomy Identification**: First identifies anatomical terms (carotid, ICA, CCA)
2. **Disease Recognition**: Detects stenosis-related terminology
3. **Severity Mapping**: Links qualitative and quantitative severity indicators
4. **Pattern Matching**: Uses annotation patterns to create structured output

### Pipeline 2: Peak Systolic Velocity Ratio Extraction

Extracts internal to common carotid artery peak systolic velocity ratios:
- **Ratio_Pattern** annotations containing:
  - Numerical ratio values (e.g., 2.5, 3.8)
  - Associated anatomical context
  - Velocity measurements when available

## Key Components

### Annotation Types
- `Anatomy`: Carotid vessel references
- `Disease`: Stenosis, occlusion terms
- `Severity_Term`: Qualitative severity indicators
- `Percent_Value`: Numerical percentage values
- `Ratio_Term`: Velocity ratio indicators
- `Ratio_Value`: Numerical ratio measurements
- `Laterality`: Left/right/bilateral indicators

### Processing Modules
- **RegexAnnotator**: Pattern-based entity recognition
- **AnnotationPatternAnnotator**: Complex pattern matching
- **StenosisLogic**: Post-processing business logic
- **MergeAnnotator**: Combining related annotations

## Configuration

### Input Sources
Configure data input in `/config/readers/`:
- `FileCollectionReaderConfig.groovy`: Process files from directory
- `DatabaseCollectionReader.groovy`: Database-based input
- `BatchDatabaseCollectionReader.groovy`: Batch processing

### Output Configuration
Configure outputs in `/config/listeners/`:
- `DatabaseListenerConfig.groovy`: Database output
- `CsvListenerConfig.groovy`: CSV file output
- `SimpleXmiListenerConfig.groovy`: UIMA XMI format

### Regex Patterns
Pattern definitions in `/config/desc/` directory:
- Anatomy patterns
- Disease terminology
- Severity indicators
- Percentage matching
- Ratio extraction patterns

## Usage Examples

### Processing File-based Input
1. Place text files in `data/input/` directory
2. Configure `FileCollectionReaderConfig.groovy`
3. Run the pipeline

### Database Processing
1. Configure database connection in appropriate reader config
2. Update SQL queries for your schema
3. Configure output listener for results

## Output Format

The system produces structured annotations containing:
```
Stenosis_Pattern:
- Anatomy: "internal carotid artery"
- Side: "right"
- Disease: "stenosis"
- Severity: "moderate"
- Percentage: "60-70%"
- Confidence: 0.95

Ratio_Pattern:
- Anatomy: "ICA/CCA"
- Side: "left"
- Ratio_Value: 2.8
- Context: "peak systolic velocity"
```

## Extension Points

The system can be extended by:
- Adding new regex patterns for additional terminology
- Implementing custom logic annotators
- Creating new output formats
- Adding quality metrics and validation 


 

