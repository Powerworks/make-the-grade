package com.state.edu.testingcenters;

import com.state.edu.students.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Registers students into permitted sessions.
 *
 * <p>Depends on {@link Student} from the students context: enrollment time
 * of the enrolled student is unambiguous. This dependency direction
 * (testing-centers -> students) is the documented norm later exercised in
 * the coupling-fitness-functions repo.
 */
public final class EnrollmentService {

    /** Registry of sessions offered by the state. */
    private final List<Session> sessions = new ArrayList<>();

    public void offerSession(Session session) {
        sessions.add(session);
    }

    public List<Session> sessionsOn(LocalDate date) {
        return sessions.stream()
                .filter(s -> s.date().equals(date))
                .toList();
    }

    /**
     * Registers a student for a specific session.
     *
     * @throws IllegalArgumentException if the student is out of mandate or the
     *         session environment is not permitted
     */
    public EnrollmentResult register(Student student, Session session) {
        if (!student.eligibleForTesting()) {
            return EnrollmentResult.refused("student %s not in grades 3-12".formatted(student.id()));
        }
        if (!session.isPermittedEnvironment()) {
            return EnrollmentResult.refused("session %s is a remote/unsanctioned environment".formatted(session));
        }
        return EnrollmentResult.accepted(student, session);
    }

    public record EnrollmentResult(boolean accepted, String reason,
                                   Student student, Session session) {

        static EnrollmentResult refused(String reason) {
            return new EnrollmentResult(false, reason, null, null);
        }

        static EnrollmentResult accepted(Student s, Session se) {
            return new EnrollmentResult(true, "registered", s, se);
        }
    }
}
