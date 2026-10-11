package com.yafi.campustasks.domain
import org.junit.Assert.*
import org.junit.Test

class TaskValidatorTest {
    @Test fun validInputHasNoErrors() { assertTrue(TaskValidator.validate("Belajar Kotlin", 45).isEmpty()) }
    @Test fun blankTitleFails() { assertTrue(TaskValidator.validate("   ", 30).any { it.contains("Judul") }) }
    @Test fun twoCharacterTitleFails() { assertEquals(1, TaskValidator.validate("ab", 30).size) }
    @Test fun threeCharacterTitlePasses() { assertTrue(TaskValidator.validate("abc", 30).isEmpty()) }
    @Test fun eightyCharacterTitlePasses() { assertTrue(TaskValidator.validate("a".repeat(80), 30).isEmpty()) }
    @Test fun eightyOneCharacterTitleFails() { assertEquals(1, TaskValidator.validate("a".repeat(81), 30).size) }
    @Test fun titleLengthIsMeasuredAfterTrim() { assertTrue(TaskValidator.validate("  abc  ", 30).isEmpty()) }
    @Test fun fourMinutesFails() { assertEquals(1, TaskValidator.validate("Valid", 4).size) }
    @Test fun fiveMinutesPasses() { assertTrue(TaskValidator.validate("Valid", 5).isEmpty()) }
    @Test fun fourHundredEightyMinutesPasses() { assertTrue(TaskValidator.validate("Valid", 480).isEmpty()) }
    @Test fun fourHundredEightyOneMinutesFails() { assertEquals(1, TaskValidator.validate("Valid", 481).size) }
    @Test fun blankTagFails() { assertTrue(TaskValidator.validate("Valid", 30, setOf(" ")).any { it.contains("Tag") }) }
    @Test fun errorsAreAccumulated() { assertEquals(3, TaskValidator.validate("", 0, setOf(" ")).size) }
}
