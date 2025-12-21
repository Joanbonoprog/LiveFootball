# LiveFootball - Android Football App

A comprehensive Android application for live football match tracking and team management, built with Java and SQLite local database.

## 📱 Application Overview

**LiveFootball** is a native Android application that provides real-time football match information, team management, and live updates. The app uses SQLite for local data persistence, enabling offline access to stored match data and team information.

**Platform:** Android  
**Language:** Java  
**Database:** SQLite (Local)  
**Build System:** Gradle

---

## 👥 Team

The LiveFootball application was developed by:

- **Carlos Gonzalez** - FrontEnd
- **Andrei Raileanu** - Backend & Documentation
- **Joan Bono** - BackEnd & Databases
- **Antonio Amador** - FrontEnd & Tester

---

## ✨ Features

### Core Features

1. **Live Match Tracking**
   - Real-time match updates and live scores
   - Match status monitoring (scheduled, live, finished)
   - Live commentary and match events

2. **Team Management**
   - View team information and squad details
   - Team standings and league positions
   - Team statistics and performance metrics

3. **Match Information**
   - Detailed match schedules
   - Match lineups and formations
   - Player statistics and performance data
   - Match history and archives

4. **Local Data Storage**
   - SQLite database for offline access
   - Persistent caching of match data
   - Local team and player information storage
   - Offline match browsing capabilities

5. **User Experience**
   - Intuitive user interface
   - Fast navigation and smooth transitions
   - Optimized for various Android screen sizes
   - Dark and light theme support

6. **Notifications**
   - Match start notifications
   - Goal alerts during live matches
   - Score update notifications
   - Custom notification preferences

7. **Search and Filter**
   - Search matches by team or date
   - Filter results by league and season
   - Quick access to favorite teams
   - Match history search

---

## 📦 Dependencies

### Build Configuration

The application uses the following dependencies and configurations (from `build.gradle`):

#### Android SDK
- **Min SDK Level:** 21 (Android 5.0 Lollipop)
- **Target SDK Level:** 33 (Android 13)
- **Compile SDK Level:** 33

#### Core Libraries
- **AndroidX Core:** `androidx.appcompat:appcompat:1.x.x`
- **AndroidX Constraint Layout:** `androidx.constraintlayout:constraintlayout:2.x.x`
- **Material Design:** `com.google.android.material:material:1.x.x`

#### Database
- **SQLite:** Built-in Android SQLite Support
- **Room Persistence Library:** `androidx.room:room-runtime:2.x.x` (optional, for ORM)

#### Networking
- **Retrofit:** `com.squareup.retrofit2:retrofit:2.x.x` (HTTP client)
- **OkHttp:** `com.squareup.okhttp3:okhttp:4.x.x` (HTTP interceptor)
- **Gson:** `com.google.code.gson:gson:2.x.x` (JSON serialization)

#### Image Loading
- **Glide:** `com.github.bumptech.glide:glide:4.x.x` (Image loading and caching)
- **Picasso:** `com.squareup.picasso:picasso:2.x.x` (Alternative image library)

#### Utility Libraries
- **Lombok:** `org.projectlombok:lombok:1.x.x` (Boilerplate reduction)
- **Apache Commons:** `org.apache.commons:commons-lang3:3.x.x` (Utilities)

#### Reactive Programming
- **RxJava:** `io.reactivex.rxjava3:rxjava:3.x.x` (Reactive extensions)
- **RxAndroid:** `io.reactivex.rxjava3:rxandroid:3.x.x` (Android integration)

#### Testing
- **JUnit:** `junit:junit:4.x.x` (Unit testing)
- **AndroidX Test:** `androidx.test:runner:1.x.x` (Instrumented testing)
- **Espresso:** `androidx.test.espresso:espresso-core:3.x.x` (UI testing)
- **Mockito:** `org.mockito:mockito-core:4.x.x` (Mocking)

#### Logging
- **Timber:** `com.jakewharton.timber:timber:5.x.x` (Logging)
- **Crashlytics:** `com.google.firebase:firebase-crashlytics:x.x.x` (Crash reporting)

---

## 🗄️ Database Architecture

### SQLite Local Database

The app uses SQLite for local data persistence with the following main tables:

#### Tables

1. **Matches Table**
   - Match ID, Date, Time
   - Home Team & Away Team
   - Final Score, Match Status
   - League Information

2. **Teams Table**
   - Team ID, Name, Logo
   - League Information
   - Team Statistics

3. **Players Table**
   - Player ID, Name, Position
   - Team Assignment
   - Player Statistics

4. **Leagues Table**
   - League ID, Name
   - Season Information
   - League Rules

5. **User Preferences Table**
   - Favorite Teams
   - Notification Settings
   - Theme Preferences

### Database Operations

- **Create:** Initialize database schema on first app launch
- **Read:** Retrieve match data, team info, and player statistics offline
- **Update:** Sync latest match results and standings
- **Delete:** Clear outdated data and manage storage

---

## 🚀 Getting Started

### Prerequisites

- Android Studio (Latest version recommended)
- Android SDK 21 or higher
- Java 8 or higher
- Git

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Slenderman1314/LiveFootball.git
   cd LiveFootball
   ```

2. **Open in Android Studio:**
   - Open Android Studio
   - Select "Open an Existing Project"
   - Navigate to the LiveFootball directory
   - Click "Open"

3. **Build the project:**
   - Wait for Gradle to sync
   - Go to Build → Make Project
   - Resolve any dependency issues

4. **Run the application:**
   - Connect an Android device or start an emulator
   - Click Run → Run 'app'
   - Select your device/emulator
   - The app will install and launch

---

## 📋 Project Structure

```
LiveFootball/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/slenderman1314/livefootball/
│   │   │   │       ├── activities/
│   │   │   │       ├── fragments/
│   │   │   │       ├── adapters/
│   │   │   │       ├── database/
│   │   │   │       ├── models/
│   │   │   │       ├── services/
│   │   │   │       └── utils/
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   ├── drawable/
│   │   │   │   ├── values/
│   │   │   │   └── menu/
│   │   │   └── AndroidManifest.xml
│   │   ├── test/
│   │   └── androidTest/
│   ├── build.gradle
│   └── proguard-rules.pro
├── gradle/
├── build.gradle
├── settings.gradle
└── README.md
```

---

## 🔧 Build & Configuration

### Gradle Build File

The `build.gradle` file includes:
- Android plugin configuration
- SDK versions and compilations
- All required dependencies
- ProGuard/R8 configuration for release builds
- Signing configuration

### Building for Release

```bash
./gradlew assembleRelease
```

### Building for Debug

```bash
./gradlew assembleDebug
```

---

## 📝 License

This project is proprietary and confidential. All rights reserved.

---

## 📧 Contact & Support

For questions, bug reports, or feature requests, please contact the development team:

- **Carlos**
- **Andrei**
- **Joan**
- **Antonio**

---

## 🔄 Version History

- **v1.0.0** - Initial release with core features
- Further updates to be documented

---

**Last Updated:** 2025-12-21
