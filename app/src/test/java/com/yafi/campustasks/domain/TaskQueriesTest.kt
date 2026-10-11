package com.yafi.campustasks.domain
import org.junit.Assert.*
import org.junit.Test

class TaskQueriesTest {
    private val tasks = listOf(
        Task(1, "Zebra Kotlin", null, TaskPriority.HIGH, TaskStatus.TODO, 45, setOf("mobile")),
        Task(2, "alpha UI", "Belajar layout", TaskPriority.HIGH, TaskStatus.IN_PROGRESS, 30),
        Task(3, "Testing", null, TaskPriority.HIGH, TaskStatus.DONE, 60),
        Task(4, "Database", "Catatan SQL", TaskPriority.LOW, TaskStatus.TODO, 20),
    )
    @Test fun openTasksExcludesDone() { assertEquals(listOf(1L, 2L, 4L), openTasks(tasks).map { it.id }) }
    @Test fun priorityQueryFiltersAndSorts() { assertEquals(listOf(2L, 3L, 1L), tasksByPriority(tasks, TaskPriority.HIGH).map { it.id }) }
    @Test fun searchMatchesTitleIgnoringCase() { assertEquals(listOf(1L), searchByKeyword(tasks, "  KOTLIN  ").map { it.id }) }
    @Test fun searchMatchesNullableDescription() { assertEquals(listOf(4L), searchByKeyword(tasks, "sql").map { it.id }) }
    @Test fun searchMatchesTags() { assertEquals(listOf(1L), searchByKeyword(tasks, "MOBILE").map { it.id }) }
    @Test fun searchWithNoMatchReturnsEmptyList() { assertTrue(searchByKeyword(tasks, "Tidak ditemukan").isEmpty()) }
    @Test fun blankSearchReturnsAllTasks() { assertEquals(tasks, searchByKeyword(tasks, " ")) }
    @Test fun groupByStatusUsesActualStatuses() {
        val result = groupByStatus(tasks)
        assertEquals(listOf(1L, 4L), result[TaskStatus.TODO]?.map { it.id })
        assertEquals(listOf(2L), result[TaskStatus.IN_PROGRESS]?.map { it.id })
        assertEquals(listOf(3L), result[TaskStatus.DONE]?.map { it.id })
    }
    @Test fun pendingHighTitlesExcludesDoneAndSorts() { assertEquals(listOf("alpha UI", "Zebra Kotlin"), pendingHighTitles(tasks)) }
    @Test fun queryFunctionsDoNotMutateInput() {
        val original = tasks.toList()
        openTasks(tasks); tasksByPriority(tasks, TaskPriority.HIGH); searchByKeyword(tasks, "sql"); groupByStatus(tasks)
        assertEquals(original, tasks)
    }
    @Test fun genericFindByIdFindsTaskOrNull() {
        assertEquals(tasks[1], findById(tasks, 2))
        assertNull(findById(tasks, 99))
    }
    @Test fun genericFindByIdWorksForAnotherIdentifiableType() {
        data class Course(override val id: Long, val name: String) : Identifiable
        val course = Course(7, "Mobile")
        assertEquals(course, findById(listOf(course), 7))
    }
    @Test fun emptyInputIsHandled() {
        assertTrue(openTasks(emptyList()).isEmpty())
        assertTrue(tasksByPriority(emptyList(), TaskPriority.HIGH).isEmpty())
        assertTrue(searchByKeyword(emptyList(), "abc").isEmpty())
        assertTrue(groupByStatus(emptyList()).isEmpty())
        assertNull(findById(emptyList<Task>(), 1))
        assertEquals(0, totalOpenMinutes(emptyList()))
    }
    @Test fun totalOpenMinutesExcludesCompletedTasks() { assertEquals(95, totalOpenMinutes(tasks)) }
    @Test fun displayNoteHandlesNullBlankAndText() {
        assertEquals("Tanpa catatan", displayNote(null))
        assertEquals("Tanpa catatan", displayNote(" "))
        assertEquals("Catatan", displayNote(" Catatan "))
    }
}
