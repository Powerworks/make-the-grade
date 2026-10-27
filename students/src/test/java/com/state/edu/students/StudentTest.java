package com.state.edu.students;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GradeLevelTest {

    @Test
    void says_grades_3_through_12_are_eligible() {
        for (int grade = 3; grade <= 12; grade++) {
            assertTrue(GradeLevel.eligibleForTesting(grade),
                    "grade " + grade + " should be eligible");
        }
    }

    @Test
    void rejects_grades_outside_the_state_mandate() {
        assertFalse(GradeLevel.eligibleForTesting(2));
        assertFalse(GradeLevel.eligibleForTesting(13));
    }

    @Test
    void student_identity_is_value_type_on_uuid() {
        var s1 = new Student(UUID.randomUUID(), "Ada Lovelace", "SCH-1", 8);
        var s2 = new Student(s1.id(), "Grace Hopper", "SCH-9", 10);
        assertEquals(s1, s2, "same id must mean same student");
    }
}
