data class SimpleTask(val title: String, val note: String?, val minutes: Int, val done: Boolean)
fun displayNote(note: String?): String = note?.trim()?.takeIf { it.isNotEmpty() } ?: "Tanpa catatan"
fun main() {
    val tasks = listOf(
        SimpleTask("Kotlin", "Baca null safety", 45, false),
        SimpleTask("UI", null, 30, true),
        SimpleTask("Testing", "Tulis unit test", 60, false),
    )
    val openTitles = tasks.filter { !it.done }.map { it.title }
    val totalMinutes = tasks.filterNot { it.done }.sumOf { it.minutes }
    val firstHigh = tasks.firstOrNull { it.minutes >= 60 }?.title
    println(openTitles)
    println(totalMinutes)
    println(firstHigh ?: "Tidak ada")
    val addedTasks = tasks + listOf(
        SimpleTask("Database", null, 20, false),
        SimpleTask("Git", "Commit tugas", 15, true),
    )
    val shortestOpen = addedTasks.filterNot { it.done }.minByOrNull { it.minutes }
    println("Tugas terbuka tersingkat: ${shortestOpen?.title ?: "Tidak ada"}")
    println(displayNote(null))
    println(displayNote("   "))
    println(displayNote("  Baca modul  "))
}
