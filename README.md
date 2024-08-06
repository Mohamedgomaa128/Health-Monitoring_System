# Health-Monitoring System

Welcome to the Health-Monitoring System project. This project leverages big data technologies to provide a comprehensive system for monitoring health data. It is implemented using Java Spring Boot for the backend and Vue.js for the frontend. The project utilizes HDFS, Spark, Hadoop, Parquet files, microservices, and MapReduce for efficient data processing and storage.

## Table of Contents

- [Introduction](#introduction)
- [Project Overview](#project-overview)
- [Technologies Used](#technologies-used)
- [Architecture](#architecture)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)
- [Acknowledgements](#acknowledgements)

## Introduction

This repository contains the code for a Health-Monitoring System, a big data project designed to handle and analyze large volumes of health data. The system is built using Java Spring Boot and Vue.js, and it employs various big data technologies such as HDFS, Spark, Hadoop, Parquet files, and MapReduce to ensure efficient data processing and storage.

## Project Overview

### Objective

The main objective of this project is to create a scalable and efficient system for monitoring health data. The system collects, stores, processes, and visualizes health data to provide actionable insights.

### Key Features

- Real-time data processing and analysis
- Scalable microservices architecture
- Efficient storage using HDFS and Parquet files
- Data processing using Spark and MapReduce
- User-friendly interface with Vue.js

## Technologies Used

- **Java Spring Boot**: Backend framework for building the RESTful API and microservices
- **Vue.js**: Frontend framework for creating the user interface
- **HDFS (Hadoop Distributed File System)**: Distributed file system for storing large volumes of data
- **Spark**: Unified analytics engine for large-scale data processing
- **Hadoop**: Framework for distributed storage and processing of big data
- **Parquet**: Columnar storage file format optimized for use with big data processing frameworks
- **Microservices**: Architectural style for developing the system as a suite of small services

## Architecture

The Health-Monitoring System follows a microservices architecture, with each component designed to handle specific tasks. Data is ingested and stored in HDFS, processed using Spark and MapReduce, and served to the frontend through Spring Boot microservices. The frontend, built with Vue.js, provides a user-friendly interface for interacting with the system.

## Installation

To run the project locally, follow these steps:

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/health-monitoring-system.git
   cd health-monitoring-system
2. Install the necessary dependencies for the backend:
   ```bash
   cd backend
   mvn install
3. Run the frontend Vue.js application:
   ```bash 
   cd ../frontend
   npm run serve
4.Access the application in your web browser at http://localhost:8080.

## Contributing
Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request. Ensure your contributions align with the project's coding standards and objectives

## License
This project is licensed under the MIT License.

## Acknowledgements
<li> The developers and maintainers of the technologies used in this project.</li> 
<li> The open-source community for providing valuable resources and support.</li> 
