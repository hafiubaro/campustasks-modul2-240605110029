package com.yafi.campustasks.domain
import org.junit.Assert.*
import org.junit.Test

class TaskFactoryTest {
    private fun success(result: CreateTaskResult): Task = when (result) {
        is CreateTaskResult.Success -> result.task
        is CreateTaskResult.Invalid -> throw AssertionError(result.errors.joinToString())
    }
    @Test fun validTaskIsCreatedWithTrimmedTitle() {
        val task = success(createTask(1, "  Belajar Kotlin  ", estimatedMinutes = 45))
        assertEquals("Belajar Kotlin", task.title)
        assertEquals(TaskStatus.TODO, task.status)
    }
    @Test fun invalidTitleProducesInvalidResult() {
        val result = createTask(1, " ")
        assertTrue(result is CreateTaskResult.Invalid)
        if (result is CreateTaskResult.Invalid) assertTrue(result.errors.any { it.contains("Judul") })
    }
    @Test fun blankDescriptionBecomesNull() { assertNull(success(createTask(1, "Valid", " \n ")).description) }
    @Test fun nullDescriptionStaysNull() { assertNull(success(createTask(1, "Valid")).description) }
    @Test fun nonblankDescriptionIsTrimmed() { assertEquals("Catatan", success(createTask(1, "Valid", " Catatan ")).description) }
    @Test fun tagsAreTrimmedAndDeduplicatedWithoutMutatingInput() {
        val input = setOf(" Kotlin ", "Kotlin", " Mobile ")
        val task = success(createTask(1, "Valid", tags = input))
        assertEquals(setOf("Kotlin", "Mobile"), task.tags)
        assertEquals(setOf(" Kotlin ", "Kotlin", " Mobile "), input)
    }
    @Test fun blankTagIsRejected() { assertTrue(createTask(1, "Valid", tags = setOf(" ")) is CreateTaskResult.Invalid) }
    @Test fun markDoneCreatesANewInstance() {
        val original = success(createTask(1, "Valid"))
        val done = original.markDone()
        assertNotSame(original, done)
        assertEquals(TaskStatus.TODO, original.status)
        assertEquals(TaskStatus.DONE, done.status)
        assertEquals(original.copy(status = TaskStatus.DONE), done)
    }
    @Test fun markInProgressCreatesANewInstance() {
        val original = success(createTask(1, "Valid"))
        val changed = original.markInProgress()
        assertNotSame(original, changed)
        assertEquals(TaskStatus.TODO, original.status)
        assertEquals(TaskStatus.IN_PROGRESS, changed.status)
    }
}
