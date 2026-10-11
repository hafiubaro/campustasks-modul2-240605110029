package com.yafi.campustasks.domain

object TaskValidator {
    const val MIN_TITLE = 3
    const val MAX_TITLE = 80
    const val MIN_MINUTES = 5
    const val MAX_MINUTES = 480

    fun validate(title: String, estimatedMinutes: Int, tags: Set<String> = emptySet()): List<String> =
        buildList {
            if (title.trim().length !in MIN_TITLE..MAX_TITLE) {
                add("Judul harus terdiri dari 3-80 karakter setelah trim")
            }
            if (estimatedMinutes !in MIN_MINUTES..MAX_MINUTES) {
                add("Estimasi harus berada pada 5-480 menit")
            }
            if (tags.any { it.isBlank() }) {
                add("Tag tidak boleh blank")
            }
        }
}
