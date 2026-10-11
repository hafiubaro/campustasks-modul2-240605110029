package com.yafi.campustasks.domain

fun openTasks(tasks: List<Task>): List<Task> = tasks.filter { it.status != TaskStatus.DONE }

fun tasksByPriority(tasks: List<Task>, priority: TaskPriority): List<Task> =
    tasks.filter { it.priority == priority }.sortedBy { it.title.lowercase() }

// Keyword blank menghasilkan semua task sebagai hasil pencarian tanpa pembatas.
fun searchByKeyword(tasks: List<Task>, keyword: String): List<Task> {
    val query = keyword.trim()
    return tasks.filter { task ->
        task.title.contains(query, ignoreCase = true) ||
            task.description?.contains(query, ignoreCase = true) == true ||
            task.tags.any { it.contains(query, ignoreCase = true) }
    }
}

fun groupByStatus(tasks: List<Task>): Map<TaskStatus, List<Task>> = tasks.groupBy { it.status }
fun totalOpenMinutes(tasks: List<Task>): Int = openTasks(tasks).sumOf { it.estimatedMinutes }

// Jawaban tantangan kode bagian 23.1.
fun pendingHighTitles(tasks: List<Task>): List<String> = tasks
    .filter { it.priority == TaskPriority.HIGH && it.status != TaskStatus.DONE }
    .sortedBy { it.title.lowercase() }
    .map { it.title }

fun displayNote(note: String?): String = note?.trim()?.takeIf { it.isNotEmpty() } ?: "Tanpa catatan"
