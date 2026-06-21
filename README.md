# FairPath

Your career fair companion — an Android app for attendees to scan badges, manage contacts, export data, and send follow-ups.

## Features

| Screen | Status |
|--------|--------|
| Home | Done |
| Scan Badge | Done — real-time QR scanning (CameraX + ML Kit), parses MECARD / vCard / mailto / plain text and prefills Manual Entry |
| Manual Entry | Done — full form with prefill from scans; reused in edit mode from the contact card |
| Contacts | Done — list with search and sort |
| Contact Card | Done — view, edit, delete; quick-note auto-saves as you type |
| Export | Done — CSV + VCF export to the system share sheet |
| Follow-up | Done — message composer, schedule shortcuts, share / copy / email; email signature persisted in Room |

## Tech Stack

- **Language**: Kotlin 2.2.10
- **UI**: Jetpack Compose (single-Activity, no fragments)
- **Navigation**: Compose Navigation 2.7.7
- **Theming**: Material 3 (light + dark)
- **Persistence**: Room 2.7.0 (via KSP) — contacts + email signature
- **Camera + barcode**: CameraX + ML Kit Barcode Scanning
- **Async**: Kotlinx Coroutines + Flow
- **Min SDK**: 24 (Android 7.0) / **Target SDK**: 36

## Project Structure

```
app/src/main/java/com/example/fairpath/
├── MainActivity.kt              # Single activity, hosts AppNavigation
├── FairPathApplication.kt       # Initializes DatabaseProvider
├── navigation/
│   └── AppNavigation.kt         # NavHost + Screen routes
├── screens/
│   ├── HomeScreen.kt
│   ├── ScanScreen.kt            # CameraX preview + permission flow
│   ├── ManualEntryScreen.kt     # Add/edit form, prefilled from scan or contact
│   ├── ContactsScreen.kt        # List with search + sort
│   ├── ContactCardScreen.kt     # View / quick-note auto-save / open editor / delete
│   ├── ExportScreen.kt          # CSV + VCF export
│   └── FollowUpScreen.kt        # Message + schedule + signature dialog
├── data/
│   ├── Contact.kt               # @Entity
│   ├── ContactRepository.kt
│   ├── Signature.kt             # @Entity (singleton row, id = 0)
│   ├── SignatureRepository.kt
│   └── db/
│       ├── FairPathDatabase.kt  # Room DB (version 2, destructive fallback)
│       ├── ContactDao.kt
│       ├── SignatureDao.kt
│       ├── ContactTypeConverters.kt
│       └── DatabaseProvider.kt  # App-scope DB + repository accessors
├── scan/
│   ├── CameraPreviewView.kt     # CameraX + ML Kit AndroidView composable
│   └── QRParser.kt              # Parses MECARD / vCard / mailto / plain text
├── export/
│   ├── CsvExporter.kt
│   └── EmailSender.kt
└── ui/theme/
    ├── Color.kt                 # All color definitions
    ├── Theme.kt                 # Material 3 color scheme wiring
    └── Type.kt                  # Typography
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
- The Room database currently uses `fallbackToDestructiveMigration` while we're pre-release; see [issue #25](https://github.com/MrSandwick/FairPath/issues/25) for the migration policy
