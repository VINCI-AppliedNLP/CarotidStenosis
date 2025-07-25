# Installation Guide

## Prerequisites

### Software Requirements

- **Java 8 or higher**: Required for UIMA framework
- **Maven 3.6+**: For dependency management and building
- **Git**: For version control (optional)

### System Requirements

- Minimum 4GB RAM (8GB recommended for large datasets)
- 1GB free disk space for installation
- Additional space for input data and output results

## Installation Steps

### 1. Clone the Repository

```bash
git clone <repository-url>
cd CarotidStenosis
```

### 2. Install Dependencies

```bash
mvn clean install
```

This will download all required dependencies including:

- Apache UIMA framework
- Leo NLP library components
- Database drivers (if using database input)

### 3. Verify Installation

```bash
mvn test
```

### 4. Configure Data Sources

#### For File-based Processing:

1. Copy your text files to `data/input/` directory
2. Update `config/readers/FileCollectionReaderConfig.groovy` if needed

#### For Database Processing:

1. Update database connection details in relevant config files:
   - `config/readers/DatabaseCollectionReader.groovy`
   - `config/listeners/DatabaseListenerConfig.groovy`
2. Ensure database drivers are available in classpath

## Next Steps

After installation:

1. Review the main README.md for usage instructions
2. Examine sample data in `data/input/`
3. Run a test with the provided sample data
4. Configure for your specific data sources
5. Review output format documentation
