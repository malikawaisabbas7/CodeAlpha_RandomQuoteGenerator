# 📱 CodeAlpha Android Internship Tasks

A collection of Android apps built during the **CodeAlpha Android Development Internship**.

---

## ✅ Task 1 — Flashcard Quiz App

A flashcard-based quiz app that helps users study and test their knowledge interactively.

### Features
- Create and review flashcards
- Flip animation to reveal answers
- Score tracking per session
- Clean Material Design UI

### Tech Stack
- Kotlin
- Room Database
- Material Components
- RecyclerView

---

## ✅ Task 2 — Random Quote Generator

An aesthetic quote app that displays inspiring quotes with smooth animations.

### Features
- 🎲 Random quote shown on every app launch
- 🔄 "New Quote" button with guaranteed no-repeat logic
- 📤 Share any quote via Android share sheet
- ✨ Smooth fade + slide animation on quote change
- 🎨 Deep navy + electric blue aesthetic theme
- 30 curated inspirational quotes with author names

### Screenshots
> *(Add screenshots here after running the app)*

### Tech Stack
- Kotlin
- Material Design Components (ExtendedFAB, MaterialButton, CardView)
- ConstraintLayout
- Android Animation (ObjectAnimator / AnimationUtils)
- Poppins font family (Google Fonts)

### Project Structure
```
app/src/main/
├── java/com/codealpha/randomquote/
│   └── MainActivity.kt          # Core logic — quote picker, animation, share
├── res/
│   ├── layout/
│   │   └── activity_main.xml    # UI layout — card, buttons, header
│   ├── values/
│   │   ├── colors.xml           # Navy + blue color palette
│   │   ├── strings.xml          # All string resources
│   │   └── themes.xml           # Material dark theme
│   ├── drawable/
│   │   ├── bg_gradient.xml      # Full-screen navy gradient background
│   │   ├── shape_circle_accent.xml  # Decorative circle accents
│   │   ├── ic_share.xml         # Share icon
│   │   └── ic_refresh.xml       # New quote icon
│   ├── anim/
│   │   ├── fade_in.xml          # Slide-up + fade in animation
│   │   └── fade_out.xml         # Fade out animation
│   └── font/
│       ├── poppins_regular.ttf
│       ├── poppins_medium.ttf
│       └── poppins_bold.ttf
└── AndroidManifest.xml
```

### How to Run
1. Clone the repository
2. Open in **Android Studio Hedgehog** or newer
3. Download Poppins font: `res/font/` → right-click → **New → Font resource file** → search "Poppins"
4. Sync Gradle and run on emulator or device (API 24+)

---

## 🛠 Setup & Requirements
- Android Studio Hedgehog+
- Kotlin 2.0.0
- Min SDK: 24 (Android 7.0)
- Target SDK: 35 (Android 15)
- Gradle 8.5

---

*Built with ❤️ during CodeAlpha Android Internship*
