package com.yafi.campustasks.domain
sealed interface CreateTaskResult {
    data class Success(val task: Task) : CreateTaskResult
    data class Invalid(val errors: List<String>) : CreateTaskResult
}
