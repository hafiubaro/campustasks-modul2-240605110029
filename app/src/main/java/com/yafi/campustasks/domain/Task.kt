package com.yafi.campustasks.domain

data class Task(
    override val id: Long,
    val title: String,
    val description: String? = null,
    val priority: TaskPriority = TaskPriority.MEDIUM,
    val status: TaskStatus = TaskStatus.TODO,
    val estimatedMinutes: Int = 30,
    val tags: Set<String> = emptySet(),
) : Identifiable

fun Task.markInProgress(): Task = copy(status = TaskStatus.IN_PROGRESS)
fun Task.markDone(): Task = copy(status = TaskStatus.DONE)
