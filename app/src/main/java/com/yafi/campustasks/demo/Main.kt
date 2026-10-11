package com.yafi.campustasks.demo
import com.yafi.campustasks.domain.*

fun main() {
    val results = listOf(
        createTask(1, "  Kotlin  ", "Baca null safety", TaskPriority.HIGH, 45, setOf(" kotlin ", "kotlin")),
        createTask(2, "UI", estimatedMinutes = 30),
        createTask(3, "Testing", "Tulis unit test", TaskPriority.HIGH, 60),
    )
    val tasks = results.mapNotNull { result ->
        when (result) {
            is CreateTaskResult.Success -> result.task
            is CreateTaskResult.Invalid -> {
                println("Invalid: ${result.errors.joinToString()}")
                null
            }
        }
    }
    println("Open: ${openTasks(tasks).map { it.title }}")
    println("Total menit: ${totalOpenMinutes(tasks)}")
    println("HIGH: ${pendingHighTitles(tasks)}")
    val first = findById(tasks, 1)
    first?.let { println("Status awal: ${it.status}; status salinan: ${it.markDone().status}") }
    println("Catatan: ${displayNote(first?.description)}")
}
