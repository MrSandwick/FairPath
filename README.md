# FairPath

Your career fair companion — an Android app for attendees to scan badges, manage contacts, export data, and send follow-ups.

## Features

| Screen | Status |
|--------|--------|
| Home | Done |
| Scan Badge | Placeholder |
| Contacts | Placeholder |
| Export | Placeholder |
| Follow-up | Placeholder |

## Tech Stack

- **Language**: Kotlin 2.2.10
- **UI**: Jetpack Compose (single-Activity, no fragments)
- **Navigation**: Compose Navigation 2.7.7
- **Theming**: Material 3 (light + dark)
- **Min SDK**: 24 (Android 7.0) / **Target SDK**: 36

## Project Structure

```
app/src/main/java/com/example/fairpath/
├── MainActivity.kt          # Entry point
├── navigation/
│   └── AppNavigation.kt     # NavHost + Screen routes
├── screens/
│   ├── HomeScreen.kt
│   ├── ScanScreen.kt
│   ├── ContactsScreen.kt
│   ├── ExportScreen.kt
│   └── FollowUpScreen.kt
└── ui/theme/
    ├── Color.kt             # All color definitions
    ├── Theme.kt             # Material 3 color scheme wiring
    └── Type.kt              # Typography
```

All user-facing strings live in `app/src/main/res/values/strings.xml`.

## Build & Run

```powershell
# Debug APK
./gradlew assembleDebug

# Install on connected device/emulator
./gradlew installDebug

# Unit tests
./gradlew test

# Instrumented (on-device) tests
./gradlew connectedAndroidTest

# Lint
./gradlew lint

# Clean
./gradlew clean
```

## Development Notes

- Dependencies are managed via `gradle/libs.versions.toml` (version catalog)
- Never hardcode strings or colors in `.kt` files — add them to `strings.xml` / `Color.kt` first
- See `CLAUDE.md` for full contributor guidelines