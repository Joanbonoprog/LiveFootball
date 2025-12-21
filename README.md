# LiveFootball

A Java Android application for live football updates and information.

## Overview

LiveFootball is a mobile application that provides users with real-time football/soccer match information, scores, and statistics on Android devices.

## Technology Stack

- **Language**: Java
- **Platform**: Android
- **Database**: SQLite
- **Build System**: Gradle
- **Minimum API Level**: Android 5.0 (API level 21)

## Features

- Live football match updates
- Real-time score tracking
- Match statistics and information
- Local data persistence with SQLite
- Offline access to cached data

## Project Structure

```
LiveFootball/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   ├── res/
│   │   │   └── AndroidManifest.xml
│   │   └── test/
│   ├── build.gradle
│   └── proguard-rules.pro
├── build.gradle
├── settings.gradle
└── README.md
```

## Getting Started

### Prerequisites

- Android Studio (latest version recommended)
- Java Development Kit (JDK) 8 or higher
- Android SDK with API level 21 or higher

### Building the Project

1. Clone the repository:
```bash
git clone https://github.com/Slenderman1314/LiveFootball.git
```

2. Open the project in Android Studio

3. Build the project using Gradle:
```bash
./gradlew build
```

4. Run the application on an emulator or connected device:
```bash
./gradlew installDebug
```

## Database

The application uses SQLite for local data storage, allowing users to:
- Cache match information
- Store user preferences
- Access data offline
- Maintain historical match records

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## Support

For support, please open an issue on the GitHub repository.
