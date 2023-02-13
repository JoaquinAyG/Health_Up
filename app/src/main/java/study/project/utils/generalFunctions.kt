package study.project.utils
import androidx.appcompat.app.AppCompatDelegate

fun forceDarkMode() {
    try {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}