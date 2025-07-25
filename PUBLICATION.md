# Publication and Citation Guide

## About This Research

This Carotid Stenosis NLP system was developed as part of research into automated clinical variable extraction from medical text. The system demonstrates novel approaches to combining qualitative and quantitative stenosis assessment from radiology reports.

## Research Context

### Objectives

- Develop automated extraction of carotid stenosis severity from clinical text
- Compare qualitative descriptors with quantitative measurements
- Create structured data from unstructured radiology reports
- Enable large-scale retrospective analysis of carotid stenosis patterns

### Methodology

- **NLP Framework**: Apache UIMA with Leo NLP library
- **Pattern Recognition**: Regex-based entity extraction with annotation patterns
- **Dual Pipeline Architecture**: Separate processing for qualitative and quantitative measures
- **Post-processing Logic**: Business rules for combining multiple assessment types

### Validation

The system was developed and validated using:

- Expert-annotated gold standard datasets
- Inter-annotator agreement studies
- Clinical outcome correlation analysis

## Citation Information

When using this system in your research, please cite the associated research paper:

```
[Citation information will be added upon paper publication]


```

## Reproducibility

### Data Availability

- **Synthetic Data**: Included in this repository for reference (`data/input/`)
- **Configuration Files**: All processing configurations provided
- **Source Code**: Complete implementation available

### System Requirements

- Java 8+ with Apache UIMA framework
- Maven for dependency management
- Minimum 4GB RAM for processing

### Replication Steps

1. Install dependencies using Maven
2. Configure input data sources
3. Run processing pipeline
4. Analyze structured output

## Research Applications

This system can be used for:

- **Retrospective Cohort Studies**: Large-scale analysis of carotid stenosis patterns
- **Quality Assessment**: Evaluation of diagnostic reporting consistency
- **Clinical Decision Support**: Automated screening for severe stenosis cases
- **Population Health Research**: Epidemiological studies of vascular disease

## Methodological Notes

### Known Limitations

- Performance may vary with different reporting styles
- Requires local validation for optimal accuracy
- Limited to English-language text

## Collaboration and Support

### Contributing

- Bug reports and feature requests welcome
- Code contributions should follow established patterns
- Documentation improvements encouraged
- Test cases for new functionality required

## Acknowledgments

This work builds upon:

- Apache UIMA framework development
- Leo NLP library contributions
- Clinical NLP research community
- Medical informatics best practices

## Version History

### Version 1.0 (Initial Public Release)

- Complete dual-pipeline implementation
- Comprehensive documentation
- Synthetic test data
- Configuration examples
- Research publication support
