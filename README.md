# 🌲 Canopy Sentinel - Satellite-Based Deforestation Monitoring

**Canopy Sentinel** is a real-time deforestation monitoring system that leverages satellite imagery, computer vision, and geospatial analysis to detect and visualize changes in forest cover. Designed for scalability and precision, it empowers conservation efforts by automating illegal logging detection and providing actionable insights.

---

## 🚀 Features

- 🌍 Multi-temporal satellite image analysis (Landsat/Sentinel-2)
- 🌱 NDVI-based vegetation health assessment
- 🤖 Machine learning classification of deforestation patterns
- 🔍 Pixel-level change detection (Image Differencing)
- 🛰 Integration with Google Earth Engine & NASA Earthdata
- 📊 GIS-compatible outputs with quantitative forest loss metrics
- 🔔 Real-time alerts and visualization dashboards

---

## 💡 Why Canopy Sentinel?

Deforestation is a leading cause of climate change and biodiversity loss. Manual monitoring methods are inefficient and slow. Canopy Sentinel addresses this with automation, speed, and accuracy—enabling:
- Early detection of illegal activities  
- Monitoring of conservation zones  
- Fast, data-driven decision-making  

---

## 🛠️ Technology Stack

**Core Processing:**
- JavaCV (OpenCV), GeoTools

**Desktop Application:**
- JavaFX

**Web Application:**
- Spring Boot (Backend)
- React (Frontend)
- PostGIS (Geospatial DB)

**Databases:**
- PostgreSQL with PostGIS extension
- SQLite (for lightweight use)

**Satellite Data Sources:**
- NASA Earthdata
- Google Earth Engine

---

## 📸 How It Works

1. **Image Differencing:**  
   Detects forest cover change by comparing pixel-level differences in multi-temporal satellite images.

2. **NDVI Analysis:**  
   Evaluates vegetation health using Normalized Difference Vegetation Index.

3. **Machine Learning:**  
   Trains models to classify deforestation types and patterns.

---

## 📈 Impact

Canopy Sentinel provides:
- Scalable deployment (desktop to cloud)
- Automated alerts and reports
- GIS-ready outputs for policy-making
- Support for NGOs, researchers, and governments

---

## 👨‍💻 Development Team

| Name                  | Role                            | GitHub                                       |
|-----------------------|---------------------------------|----------------------------------------------|
| Vayu Nandan Tripathi  | Team Lead & Architect           | [VayuTripathi09](https://github.com/VayuTripathi09) |
| Alok Mishra           | Backend Developer / Database    | [Alokmisra53](https://github.com/Alokmisra53) |
| Apoorva Dwivedi       | Frontend Developer              | [Apoorvaad25](https://github.com/Apoorvaad25) |
| Ananya Mittal         | Frontend Developer              | [AnanyaMittal1403](https://github.com/AnanyaMittal1403) |

---

## 🧪 Installation & Setup

### Prerequisites
- Java 17+
- Node.js & npm
- PostgreSQL with PostGIS
- Maven

### Backend Setup
```bash
cd backend
mvn clean install
java -jar target/canopy-sentinel.jar

