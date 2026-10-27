package com.state.edu.testingcenters;

import com.state.edu.students.Student;

import java.time.LocalDate;
import java.util.Objects;

/**
 * A physical testing session at a center. Sessions are place-and-time
 * bound: the kata requires students to test only inside testing centers,
 * most on school premises.
 */
public final class Session {

    public enum Environment { ON_SCHOOL_PREMISES, OFFSITE_CENTER, REMOTE_HOME }

    private final String centerCode;
    private final Environment environment;
    private final LocalDate date;

    public Session(String centerCode, Environment environment, LocalDate date) {
        this.centerCode = Objects.requireNonNull(centerCode);
        this.environment = Objects.requireNonNull(environment);
        this.date = Objects.requireNonNull(date);
    }

    public String centerCode() { return centerCode; }
    public Environment environment() { return environment; }
    public LocalDate date() { return date; }

    /** Remote/home testing is out of scope: state rule. */
    public boolean isPermittedEnvironment() {
        return environment != Environment.REMOTE_HOME;
    }

    @Override public String toString() {
        return "Session{center=%s, env=%s, date=%s}"
                .formatted(centerCode, environment, date);
    }
}
