# Carotid Stenosis NLP Extraction System

A natural language processing system for extracting carotid stenosis-related clinical variables from medical text, specifically radiology reports and clinical notes.

## Overview

This system identifies and extracts:
- **Qualitative stenosis severity** (mild, moderate, severe, etc.)
- **Quantitative percentages** (50-69%, 70-99%, etc.)
- **Peak systolic velocity ratios** (ICA/CCA ratios)
- **Anatomical references** (internal carotid artery, common carotid artery)
- **Laterality information** (left, right, bilateral)

## Features

- **Dual Pipeline Architecture**: Separate pipelines for qualitative/percentage extraction and ratio extraction
- **UIMA-based Processing**: Built on Apache UIMA framework using the Leo NLP library
- **Configurable Annotation Patterns**: Regex and pattern-based entity recognition
- **Post-processing Logic**: Combines multiple extraction types for comprehensive results
- **Flexible Output**: Supports database, CSV, and XMI output formats

## Prerequisites

- Java 8+
- Maven 3.6+
- Apache UIMA framework
- Leo NLP library dependencies

## Quick Start

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd CarotidStenosis
   ```

2. **Build the project**
   ```bash
   mvn clean compile
   ```

3. **Configure your data source**
   - Edit configuration files in `config/readers/` directory
   - Update database connections or file paths as needed
   - See `config/readers/FileCollectionReaderConfig.groovy` for file-based processing

4. **Run the pipeline**
   ```bash
   mvn exec:java -Dexec.mainClass="gov.va.vinci.StenosisService"
   ```

## Configuration

### Input Sources
The system supports multiple input sources:
- **File-based**: Process text files from a directory
- **Database**: Connect to SQL databases for batch processing
- **Streaming**: Real-time processing via UIMA AS

### Output Formats
- **Database**: Direct database insertion with configurable schema
- **CSV**: Structured output for analysis
- **XMI**: UIMA standard format for further processing

## Sample Data

The `data/input/` directory contains synthetic medical notes that demonstrate the types of text the system can process. These examples show:
- Radiology reports with carotid stenosis findings
- Clinical notes with vascular assessments
- Various reporting styles and terminology

## Documentation

- See `src/ReadMe.md` for detailed pipeline documentation
- Configuration examples in `config/` subdirectories
- Type system definitions in `src/main/java/gov/va/vinci/TypeSystem.java`

## Research Paper

This system was developed as part of research into automated clinical variable extraction from medical text. When using this system, please cite the associated research paper.

## License

This software was created by the U.S. Department of Veterans Affairs. It is a work of the US Government and not subject to copyright protection in the United States (17 U.S.C. §105). Foreign copyrights may apply. This software is provided "as-is". The U.S. Department of Veterans Affairs make no warranties regarding its accuracy or suitability and is not liable for any damage arising from its use.

See the LICENSE file for complete terms.

## Support

For questions about configuration or usage, please refer to the documentation or open an issue in the repository.
