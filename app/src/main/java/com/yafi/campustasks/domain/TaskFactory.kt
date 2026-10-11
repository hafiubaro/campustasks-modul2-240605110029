package com.yafi.campustasks.domain

fun createTask(
    id: Long,
    title: String,
    description: String? = null,
    priority: TaskPriority = TaskPriority.MEDIUM,
    estimatedMinutes: Int = 30,
    tags: Set<String> = emptySet(),
): CreateTaskResult {
    val errors = TaskValidator.validate(title, estimatedMinutes, tags)
    if (errors.isNotEmpty()) return CreateTaskResult.Invalid(errors)
    return CreateTaskResult.Success(
        Task(
            id = id,
            title = title.trim(),
            description = description?.trim()?.takeIf { it.isNotEmpty() },
            priority = priority,
            estimatedMinutes = estimatedMinutes,
            tags = tags.map { it.trim() }.toSet(),
        ),
    )
}
