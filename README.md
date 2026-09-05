# Flappy Bird Clone (Android)

A simple offline touch-based Flappy Bird clone built with **Kotlin** and **Jetpack Compose**, using **Canvas** for rendering and animation. This project follows **MVVM + Clean Architecture** to keep game logic, data, and UI cleanly separated and testable.


---


## App Version features

### Version 2 (latest)
- All Screens Design revamping
- Separate Play and Settings button
- Revamped Obstacle design & object design
- Menu option to choose Sky them, Sound Enable, Enable Immersive Mode

### Version 1
- Object jumping animation
- Obstacles
- Present Score & Best Score handling
- Restart & Menu button


---

## App Screenshots

## Version 2

<img width="270" height="585" alt="Screenshot_20260905_180521" src="https://github.com/user-attachments/assets/5c9737eb-ecaa-4400-a57f-e8c420653808" />
<img width="270" height="585" alt="Screenshot_20260905_180627" src="https://github.com/user-attachments/assets/7f3c1044-8aec-4fff-8866-6f08d2ab360a" />
<img width="270" height="585" alt="Screenshot_20260905_180616" src="https://github.com/user-attachments/assets/a078cf7b-efca-401e-9af4-0e962427b4b0" />
<img width="270" height="585" alt="Screenshot_20260905_180637" src="https://github.com/user-attachments/assets/dcaadd69-a7bf-4128-995e-b367e1a255c8" />
<img width="270" height="585" alt="Screenshot_20260905_180738" src="https://github.com/user-attachments/assets/6d2cdabe-6aa2-4045-92a9-acc35fb8477c" />





## Version 1

<img width="270" height="585" alt="Screenshot_20260901_123346" src="https://github.com/user-attachments/assets/a76dad99-2510-4931-8fac-63938f7f4d88" />

<img width="270" height="585" alt="Screenshot_20260901_123334" src="https://github.com/user-attachments/assets/d838ab88-1c4d-4860-a5de-ccabe93e7b07" />





## Table of Contents

- [Tech Stack](#tech-stack)
- [Architecture Overview](#architecture-overview)
- [Project Structure](#project-structure)
- [How the Layers Connect](#how-the-layers-connect)
- [Game Loop Explained](#game-loop-explained)
- [Data Flow: A Single Frame](#data-flow-a-single-frame)
- [Local Storage](#local-storage)
- [Dependency Injection](#dependency-injection)
- [Roadmap](#roadmap)

---

## Tech Stack

| Category | Technology | Purpose |
|---|---|---|
| Language | **Kotlin** | Primary language for the entire app |
| UI Toolkit | **Jetpack Compose** | Declarative UI for all screens |
| Rendering | **Compose `Canvas`** | Draws bird, pipes, background, ground each frame |
| Animation / Game Loop | `withFrameNanos` (inside `LaunchedEffect`) | Frame-synced physics updates (~60 FPS) |
| Gesture Input | `detectTapGestures` (`onPress` + `awaitRelease`) | Tap-to-flap, with continuous flap while held |
| Navigation | **Navigation Compose** | Moves between Menu → Game → Settings screens |
| State Management | `ViewModel` + `StateFlow` | Holds and exposes UI state reactively |
| Local Persistence | **Jetpack DataStore (Preferences)** | Stores high score and settings (sound, music, skin) |
| Dependency Injection | **Hilt** | Wires use cases, repositories, and managers together |
| Audio | `SoundPool` (SFX) + `MediaPlayer` (looping music) | Flap/score/hit sound effects and background music |
| Haptics | `Vibrator` / `VibratorManager` | Vibration feedback on collision |
| Build System | Gradle (Kotlin DSL) | Project build configuration |

**Offline-first:** No internet permission is used. All data (high score, settings) is stored locally on-device via DataStore — no backend, no network calls.

---

## Architecture Overview

The app follows **MVVM (Model-View-ViewModel)** combined with **Clean Architecture's 3-layer separation**:

```
┌─────────────────────────────────────────┐
│              PRESENTATION                │  ← Compose UI + ViewModels
│   (Screens, Canvas drawing, gestures)     │
└───────────────────┬───────────────────────┘
                    │ calls
┌───────────────────▼───────────────────────┐
│                 DOMAIN                     │  ← Pure Kotlin, no Android imports
│  (Models, Use Cases, Repository interfaces)│
└───────────────────┬───────────────────────┘
                    │ implemented by
┌───────────────────▼───────────────────────┐
│                  DATA                      │  ← DataStore wrappers, Repository impls
│      (Local persistence only — offline)     │
└─────────────────────────────────────────────┘
```

**Why this separation matters here:**
- **Domain layer has zero Android dependencies.** Physics math (`UpdateBirdPhysicsUseCase`), collision detection (`CheckCollisionUseCase`), and scoring logic are pure Kotlin functions — they can be unit tested with plain JUnit, no emulator or Compose runtime needed.
- **Data layer is intentionally thin.** Since the app is offline-only, it's just DataStore wrappers implementing domain-defined repository interfaces — no network layer, no API models, no mappers.
- **Presentation layer has no game logic.** `GameScreen.kt` only draws whatever state `GameViewModel` gives it and forwards taps. All physics/rules live in the domain layer's use cases.

---

## Project Structure

```
app/src/main/java/com/prajwalhs/flappybird/
│
├── FlappyBirdApp.kt              # @HiltAndroidApp entry point
├── MainActivity.kt                # Single Activity, hosts NavGraph
│
├── di/                             # Hilt modules — wiring everything together
│   ├── DataStoreModule.kt         # Provides DataStore<Preferences> instances
│   ├── RepositoryModule.kt        # Binds repository interfaces to implementations
│   └── AudioModule.kt             # Provides SoundManager / MusicManager singletons
│
├── domain/                        # Pure Kotlin — the "rules of the game"
│   ├── model/                     # Bird, Pipe, GameState, GameConfig, Settings
│   ├── usecase/                   # One class per game rule (physics, collision, scoring...)
│   └── repository/                # Interfaces only — no implementation details
│
├── data/                          # Local persistence implementation
│   ├── local/                     # ScoreDataStore, SettingsDataStore
│   └── repository/                # Implements domain's repository interfaces
│
├── presentation/                  # Everything UI-related
│   ├── navigation/                # NavGraph + route definitions
│   ├── game/                      # GameViewModel, GameUiState, GameScreen (Canvas + game loop)
│   ├── menu/                      # MenuViewModel, MenuScreen
│   ├── settings/                  # SettingsViewModel, SettingsScreen
│   ├── components/                # Reusable Canvas draw functions (Bird, Pipe, Ground, etc.)
│   └── theme/                     # Compose colors, typography, theme
│
└── util/                          # Cross-cutting helpers (not domain, not data, not UI)
    ├── SoundManager.kt            # Wraps SoundPool
    ├── MusicManager.kt            # Wraps MediaPlayer
    ├── VibrationHelper.kt         # Wraps Vibrator API
    └── Constants.kt
```

---

## How the Layers Connect

Using **high score saving** as a concrete example, here's how a request travels through every layer:

```
1. GameViewModel (presentation)
        │  calls
        ▼
2. SaveHighScoreUseCase (domain/usecase)
        │  depends on interface
        ▼
3. ScoreRepository (domain/repository) ← interface only, no logic
        │  implemented by
        ▼
4. ScoreRepositoryImpl (data/repository)
        │  delegates to
        ▼
5. ScoreDataStore (data/local)
        │  reads/writes
        ▼
6. DataStore<Preferences> (Android's on-device storage)
```

**The key rule:** the `domain` layer never imports anything from `data`. It only defines *interfaces* (`ScoreRepository`). The `data` layer *implements* those interfaces. This means the domain layer (and all your game rules/physics) would work identically even if you swapped DataStore for Room, or added a network layer later — nothing in `domain/` would need to change.

**Hilt makes the wiring invisible.** `di/RepositoryModule.kt` tells Hilt "whenever something asks for a `ScoreRepository`, hand it a `ScoreRepositoryImpl`." Because of this, `GameViewModel` never has to know `ScoreRepositoryImpl` or `DataStore` exist — it just asks for a `SaveHighScoreUseCase`, and Hilt assembles the entire chain above automatically at runtime.

---

## Game Loop Explained

Unlike a typical CRUD app, this project has a **continuous game loop** driving the bird's motion every frame, independent of user input:

**`GameScreen.kt`**
```kotlin
LaunchedEffect(Unit) {
    var lastFrameTimeNanos = 0L
    while (true) {
        withFrameNanos { frameTimeNanos ->
            val deltaSeconds = (frameTimeNanos - lastFrameTimeNanos) / 1_000_000_000f
            viewModel.onFrame(deltaSeconds)
            lastFrameTimeNanos = frameTimeNanos
        }
    }
}
```

`withFrameNanos` syncs this loop to the device's actual screen refresh rate (usually 60 FPS), and passes the precise time elapsed (`deltaSeconds`) since the last frame into `GameViewModel.onFrame()`. This delta-time approach means physics feels consistent even if a frame is briefly slow — everything moves proportionally to *real elapsed time*, not a fixed per-frame step.

**Tap handling** works alongside this loop, but independently:
- A tap sets the bird's `velocityY` to a strong upward value instantly (`UpdateBirdPhysicsUseCase.applyFlap`).
- Every frame after that, gravity (`GameConfig.gravity`) is continuously added back to `velocityY`, gradually turning the upward motion into a downward fall — until the next tap resets it again.
- Holding a finger down triggers repeated flaps at a fixed interval (see `isPressed` logic in `GameScreen.kt`) rather than one continuous unstoppable rise.

---

## Data Flow: A Single Frame

```
withFrameNanos fires (GameScreen)
        │
        ▼
GameViewModel.onFrame(deltaTime)
        │
        ├─► UpdateBirdPhysicsUseCase   → new bird position/velocity/rotation
        ├─► MovePipesUseCase           → scrolls pipes, spawns new ones
        ├─► CalculateScoreUseCase      → marks passed pipes, returns score delta
        └─► CheckCollisionUseCase      → true/false
                │
                ▼
    if collided → SaveHighScoreUseCase (async) → GameState.GameOver
    if not      → GameUiState updated → GameState.Playing continues
        │
        ▼
GameUiState (StateFlow) emits new value
        │
        ▼
GameScreen recomposes → Canvas redraws bird/pipes/ground with new state
```

Each use case is a small, single-responsibility class — this keeps physics, movement, scoring, and collision detection independently testable and easy to reason about in isolation.

---

## Local Storage

The app uses **Jetpack DataStore (Preferences)**, not Room, because all stored data is simple key-value settings — not relational/queryable data:

| Data | Storage | Why |
|---|---|---|
| High score | DataStore | Single overwritable integer, no querying/sorting needed |
| Sound / music toggle | DataStore | Single boolean flags |
| Selected skin | DataStore | Single string value |

If future versions add features like **score history** or a **local leaderboard with multiple ranked entries**, Room would be introduced *alongside* DataStore for just that feature — DataStore isn't well-suited for structured, multi-row, query-able data.

---

## Dependency Injection

**Hilt** is used throughout so that ViewModels never manually construct their dependencies:

- `di/DataStoreModule.kt` — provides singleton `DataStore<Preferences>` instances (qualified with `@ScorePrefs` / `@SettingsPrefs` to distinguish the two files)
- `di/RepositoryModule.kt` — binds `ScoreRepositoryImpl` → `ScoreRepository`, `SettingsRepositoryImpl` → `SettingsRepository`
- `di/AudioModule.kt` — provides `SoundManager` and `MusicManager` singletons

Every `ViewModel` is annotated `@HiltViewModel` and receives its use cases via constructor injection — no manual wiring anywhere in the UI layer.

---


## Roadmap

This is an early, functional first version. Planned next steps include:

- [X] Difficulty scaling (pipe speed/gap shrinks as score increases)
- [X] Selectable the Sky theme of the app
- [X] Pause/resume support


Contributions, refactor suggestions, and issue reports are welcome as the project matures.
