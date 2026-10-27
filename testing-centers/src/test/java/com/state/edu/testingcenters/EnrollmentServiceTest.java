package com.state.edu.testingcenters;

import com.state.edu.students.Student;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static com.state.edu.testingcenters.Session.Environment.*;

class EnrollmentServiceTest {

    private final Session onSite =
            new Session("CTR-101", ON_SCHOOL_PREMISES, LocalDate.of(2027, 5, 4));
    private final Session remote = new Session("CTR-404", REMOTE_HOME, LocalDate.of(2027, 5, 4));

    private final Student grade8 = new Student(
            java.util.UUID.randomUUID(), "Ada", "SCH-1", 8);
    private final Student grade2 = new Student(
            java.util.UUID.randomUUID(), "Baby", "SCH-2", 2);

    @Test
    void accepts_eligible_student_in_a_permitted_environment() {
        var result = new EnrollmentService().register(grade8, onSite);
        assertTrue(result.accepted());
        assertEquals("registered", result.reason());
    }

    @Test
    void refuses_out_of_mandate_students() {
        var result = new EnrollmentService().register(grade2, onSite);
        assertFalse(result.accepted());
        assertTrue(result.reason().contains("grades 3-12"));
    }

    @Test
    void refuses_remote_home_testing() {
        var result = new EnrollmentService().register(grade8, remote);
        assertFalse(result.accepted());
        assertTrue(result.reason().contains("remote"));
    }
}
