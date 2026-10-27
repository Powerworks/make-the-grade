package com.state.edu.students;

import java.util.Objects;
import java.util.UUID;

/**
 * Student identity as the state registrar knows it.
 *
 * <p>Immutable — students never "become" different people; grade level is an
 * attribute of a school year, not of the identity.
 */
public final class Student {

    private final UUID id;
    private final String name;
    private final String schoolId;
    private final int gradeLevelThisYear;

    public Student(UUID id, String name, String schoolId, int gradeLevelThisYear) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.schoolId = Objects.requireNonNull(schoolId);
        this.gradeLevelThisYear = gradeLevelThisYear;
    }

    public UUID id()              { return id; }
    public String name()          { return name; }
    public String schoolId()      { return schoolId; }
    public int gradeLevelThisYear() { return gradeLevelThisYear; }

    public boolean eligibleForTesting() {
        return GradeLevel.eligibleForTesting(gradeLevelThisYear);
    }

    @Override public boolean equals(Object o) {
        return (o instanceof Student s) && id.equals(s.id);
    }
    @Override public int hashCode() { return id.hashCode(); }
    @Override public String toString() {
        return "Student{id=%s, name=%s, school=%s, grade=%d}"
                .formatted(id, name, schoolId, gradeLevelThisYear);
    }
}
