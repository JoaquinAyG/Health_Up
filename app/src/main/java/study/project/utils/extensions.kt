package study.project.utils

fun String.isNumber(): Boolean {
    return this.isInt() || this.isDouble()
}

fun String.isInt(): Boolean {
    try {
        this.toInt()
    } catch (e: NumberFormatException) {
        return false
    }
    return true
}

fun String.isDouble(): Boolean {
    try {
        this.toDouble()
    } catch (e: NumberFormatException) {
        return false
    }
    return true
}

fun String.isMail(): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}