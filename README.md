# LiveFootball - Android Football App
 
Native Android application for **managing a football club/team and following its matches live**, developed in Java with local SQLite persistence.
 
##  Overview
 
**LiveFootball** is a management tool designed for an amateur/local club. A **Coach** creates teams, players, and matches, while an **Assistant** manages the score, timer, and cards live during the match. All data is stored in a local SQLite database.
 
**Platform:** Native Android
**Language:** Java
**Database:** Local SQLite (`SQLiteOpenHelper`)
**Build system:** Gradle (Android Gradle Plugin 8.4.1, Gradle 8.6)
**Package / namespace:** `ifp.project.livefootball`
 
---
 
##  Team
 
  - **Carlos Gonzalez** - FrontEnd
  - **Andrei Raileanu** - Backend & Documentation
  - **Joan Bono** - BackEnd & Databases
  - **Antonio Amador** - FrontEnd & Tester
---
 
##  Features
 
### Access and roles
 
  - **Welcome screen** (`MainActivity`): displays a 3-second splash screen and redirects to the login.
  - **Login / Registration** (`LogInActivity`, `RegisterActivity`): users and passwords are stored in the SQLite `users` table. During registration, users choose a role: **Coach** or **Assistant**. The logged-in user is stored in `SharedPreferences`.
  - **Role-based main menu** (`MainMenuActivity`): enables different buttons depending on the user's role:
  - **Coach**: can *create* matches, teams, and players.
  - **Assistant**: can *edit* matches, teams, and players, *list* teams and players, and access the match's *live tracking*.

### Team management (`Team`)
 
  - Create team (`CreateTeamActivity`) — name only.
  - List teams (`ListTeamActivity`) — long press to delete; a team **cannot be deleted if it has assigned players**.
  - Edit team (`EditTeamActivity`).

### Player management (`Player`)
 
  - Create player (`CreatePlayerActivity`) — associated with an existing team.
  - Edit player (`EditPlayerActivity`) — allows reassignment to another team.
  - List players (`ListPlayersActivity`) — filterable by team using a spinner.

### Match management (`Match`)
 
  - Create match (`CreateMatchActivity`) — the home and away teams are selected from the registered teams.
  - Edit match (`EditMatchActivity`).
  - **Live tracking** (`MatchOnLineActivity`):
    - Selection of the active match from a spinner.
    - Timer (`Chronometer`) with start/pause and elapsed-time persistence across screen rotations.
    - Buttons to increment in real time: home/away goals, home/away yellow cards, and home/away red cards. Each press immediately updates the match row in SQLite.

### Other
 
  - Logout (returns to the login screen).
  - `Theme.MaterialComponents.DayNight` theme (supports the system light/dark mode via `values-night`).
  - Alternative layouts for landscape orientation (`values-land`) and large screens/tablets (`values-w600dp`, `values-w1240dp`).
  - Partial localization: `values-ca-rES` resource (Catalan), which only translates the app name; the rest of the text is written directly in Spanish in the layouts/code.
  - Integration of **Firebase Analytics** and **Firebase Crashlytics** (via `google-services.json` and the corresponding Gradle plugins), used automatically by the libraries without additional manual calls in the code.
---
 
##  Dependencies (`app/build.gradle`)
 
  - `androidx.appcompat:appcompat:1.7.0`
  - `com.google.android.material:material:1.12.0`
  - `androidx.constraintlayout:constraintlayout:2.1.4`
  - `androidx.navigation:navigation-fragment:2.7.7` / `navigation-ui:2.7.7` *(included by the plugin, but not actually used: app navigation is handled with explicit `Intent`s between `Activity`s, not with the Navigation component — see the "Notes" section below)*
  - `org.testng:testng:7.10.2`
  - `com.google.firebase:firebase-bom:33.1.0` → `firebase-analytics`, `firebase-crashlytics`
  - `androidx.test.ext:junit:1.1.5`
  - `com.google.guava:listenablefuture:1.0`
  - `androidx.activity:activity:1.9.0`

**Testing:**
  - `junit:junit:4.13.2`
  - `androidx.test:runner:1.5.2`
  - `androidx.test.espresso:espresso-core:3.5.1`
 
### Technical requirements (Android SDK)
 
  - **Min SDK:** 21 (Android 5.0 Lollipop)
  - **Target SDK:** 33 (Android 13)
  - **Compile SDK:** 34
  - **Java:** source/target compatibility 1.8
  - `viewBinding` enabled in `buildFeatures` (although most `Activity`s still use `findViewById`)
  - Gradle plugins: `com.android.application`, `com.google.gms.google-services`, `com.google.firebase.crashlytics`

---
 
##  SQLite database (`Database`, version 1)
 
The `Database` class (`ifp.project.livefootball.Database.Database`) creates and manages the following tables:
 
| Table | Main columns |
|---|---|
| `teams` | `idTeams` (PK), `name` |
| `players` | `idPlayer` (PK), `playerName`, `idTeam` (FK → `teams`), `team` |
| `footballMatch` | `idMatch` (PK), `idLocalTeam` (FK), `idGuestTeam` (FK), `localScore`, `guestScore`, `localTeamName`, `guestTeamName`, `localYellowCards`, `guestYellowCards`, `localRedCards`, `guestRedCards` |
| `users` | `idUser` (PK), `userName`, `password`, `userType` |
 
The class exposes entity-specific CRUD methods: `insertTeam`, `updateTeamName`, `getTeams`, `deleteTeam`, `insertPlayer`, `updatePlayerTeam`, `getPlayers`, `getPlayersByTeam`, `deletePlayer`, `insertMatch`, `updateMatch`, `getMatches`, `getMatchStatistics`, `deleteMatch`, `insertUser`, `getUser`, `getPass`, `getUserType`, entre otros.
 
> ⚠️ Passwords are stored **in plain text** in the `users` table, without any hashing/encryption. This should be taken into account if the project is deployed to production.
 
---
 
##  Getting started
 
### Prerequisites
 
  - Android Studio (recent version, compatible with AGP 8.4.1 / Gradle 8.6)
  - Android SDK 21 or higher installed
  - JDK 8 or higher
  - Git
  - A valid Firebase `google-services.json` file for your own project (the one in the repository belongs to the authors' original Firebase project)

### Installation
 
  1. **Clone the repository:**
  ```bash
     git clone https://github.com/Joanbonoprog/LiveFootball.git
     cd LiveFootball
  ```
 
  2. **Open in Android Studio:**
     - Open Android Studio
     - Select "Open an Existing Project"
     - Select the `LiveFootball` folder

  3. **Sync and build:**
     - Let Gradle sync the dependencies
     - Build → Make Project

  4. **Run:**
     - Connect a device or start an emulator (API 21+)
     - Run → Run 'app'

---
 
##  Actual project structure
 
```
LiveFootball/
├── app/
│   ├── google-services.json
│   ├── proguard-rules.pro
│   ├── build.gradle
│   └── src/
│       ├── main/
│       │   ├── java/ifp/project/livefootball/
│       │   │   ├── Account/       # LogInActivity, RegisterActivity, User
│       │   │   ├── Database/      # Database.java (SQLiteOpenHelper)
│       │   │   ├── MainMenu/      # MainActivity (splash), MainMenuActivity (dashboard por rol)
│       │   │   ├── Match/         # CreateMatchActivity, EditMatchActivity,
│       │   │   │                  # MatchOnLineActivity, MatchStatistics
│       │   │   ├── Player/        # CreatePlayerActivity, EditPlayerActivity, ListPlayersActivity
│       │   │   └── Team/          # CreateTeamActivity, EditTeamActivity, ListTeamActivity, Teams
│       │   ├── res/
│       │   │   ├── layout/        # One layout per Activity
│       │   │   ├── values/        # strings, colors, dimensions, themes, arrays
│       │   │   ├── values-ca-rES/ # Localización parcial (catalán, app_name only)
│       │   │   ├── values-land/   # Dimensions for landscape orientation
│       │   │   ├── values-night/  # Tema oscuro
│       │   │   ├── values-w600dp/ # Dimensions for large screens
│       │   │   ├── values-w1240dp/# Dimensions for very large screens
│       │   │   ├── navigation/    # nav_graph.xml (boilerplate not connected to the actual Activities)
│       │   │   ├── drawable*/     # Logo, background, app icon
│       │   │   └── mipmap*/       # Launcher icons
│       │   └── AndroidManifest.xml
│       ├── test/          # JUnit unit tests (User, CreateTeamActivity, CreatePlayerActivity)
│       └── androidTest/   # ExampleInstrumentedTest (default Android Studio template)
├── gradle/
├── build.gradle
├── settings.gradle
└── README.md
```
 
---
 
##  Tests
 
The project includes basic unit tests in `app/src/test`:
 
  - `Account/UserTest.java`: validates the getters of the `User` class (name, password, role).
  - `Team/CreateTeamActivityTest.java` and `Player/CreatePlayerActivityTest.java`: directly instantiate `Activity` classes to check resources (`R.id...`). Since they do not use Robolectric or an instrumented environment, these specific tests are more illustrative than reliable in a real `ActivityUnitTestCase`.
  In `app/src/androidTest`, there is only `ExampleInstrumentedTest.java`, the default template generated by Android Studio (it does not contain project-specific instrumented tests).
 
---
 
##  Build
 
```bash
# Debug
./gradlew assembleDebug
 
# Release
./gradlew assembleRelease
```
 
The `build.gradle` file of the `app` module includes ProGuard/R8 configuration for release (`proguardFiles`), although `minifyEnabled` is set to `false` by default.
 
---
 
##  Notes and known limitations
 
  - **"Live" is manual and does not come from a real results API**: the score, cards, and time are entered manually from the assistant's device during the match; the app does not connect to any external football data provider.
  - **No password encryption**: passwords are stored in plain text in SQLite.
  - **`nav_graph.xml` and the `First/Second` fragments** are remnants of Android Studio's default template (Navigation Drawer/Bottom Nav) and are not part of the app's actual flow, which navigates using explicit `Intent`s between `Activity`s.
  - **Language**: the interface is mostly written directly in Spanish in the layouts and code (Toasts, button titles, etc.), with partial Catalan localization limited to the app name.
  - There is no `LICENSE` file in the repository.

---
