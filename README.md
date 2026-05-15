# Grama Suvidha Portal 

An Android application designed to improve transparency and citizen awareness 
in rural infrastructure development at the Panchayat level.

---

## 📱 About the App

Grama Suvidha Portal is a village-level project tracking platform where citizens 
can view the progress of local infrastructure works such as roads, water supply, 
and public buildings. The app also allows citizens to submit feedback or report 
issues directly through the app.

---

## ✨ Features

- **Project List** — View all village infrastructure projects in a scrollable list
- **Progress Tracking** — Visual progress bars showing completion percentage
- **Project Status** — Status badges: Pending / Ongoing / Completed
- **Project Details** — Full details including type, location, budget, and description
- **Citizen Feedback** — Submit feedback or report issues for any project
- **Bilingual Support** — Toggle between English and Kannada language
- **Kannada Numerals** — Numbers displayed in Kannada script when Kannada is selected
- **Firebase Integration** — Real-time data storage and retrieval using Firestore

---

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Kotlin | Primary programming language |
| Jetpack Compose | UI framework |
| Firebase Firestore | Cloud database |
| MVVM Architecture | App structure |
| Jetpack Navigation | Screen navigation |
| Coroutines | Background tasks |

---

## 📋 Requirements

- Android Studio (latest version)
- Minimum SDK: API 24 (Android 7.0)
- Target SDK: API 35
- Firebase account
- Internet connection

---

## 🚀 How to Run

1. Clone or download this project
2. Open in **Android Studio**
3. Add your `google-services.json` file to the `app` folder
4. Click **Run** button or press `Shift + F10`
5. App will launch on your device or emulator

---

## 📂 Project Structure
GramaSuvidha/
├── app/
│   ├── src/main/java/com/grama/suvidha/
│   │   ├── MainActivity.kt
│   │   ├── Project.kt
│   │   ├── ProjectViewModel.kt
│   │   ├── TranslationService.kt
│   │   ├── navigation/
│   │   │   └── AppNavigation.kt
│   │   └── ui/screens/
│   │       ├── ProjectListScreen.kt
│   │       └── ProjectDetailScreen.kt
│   └── google-services.json
└── README.md

---

## 🗄️ Firebase Setup

1. Go to [firebase.google.com](https://firebase.google.com)
2. Create a new project
3. Add an Android app with package name `com.grama.suvidha`
4. Download `google-services.json` and place it in the `app` folder
5. Enable **Firestore Database** in test mode

---

## 📊 Sample Projects Included

| Project | Type | Status | Progress |
|---|---|---|---|
| Main Road Repair | Road | Ongoing | 60% |
| Water Supply Pipeline | Water | Completed | 100% |
| Community Hall Construction | Building | Pending | 0% |
| Street Light Installation | Electricity | Ongoing | 40% |
| Drainage System | Sanitation | Ongoing | 75% |

---

## 👨‍💻 Developer

**D Mahesh**
USN: 1KG22CS029
K.S. School of Engineering and Management
Internship at MindMatrix — 2025-26

---

## 📄 License

This project was developed as part of the MindMatrix Internship Program 2025-26.
