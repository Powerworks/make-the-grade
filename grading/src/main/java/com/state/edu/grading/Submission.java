package com.state.edu.grading;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A submitted assessment answer, in one of the state's three item types:
 * multiple choice, short answer, or essay.
 */
public final class Submission {

    public enum ItemType { MULTIPLE_CHOICE, SHORT_ANSWER, ESSAY }

    private final UUID id;
    private final String studentId;
    private final String testId;
    private final ItemType itemType;
    private final String answerPayload;

    public Submission(UUID id, String studentId, String testId, ItemType itemType, String answerPayload) {
        this.id = Objects.requireNonNull(id);
        this.studentId = Objects.requireNonNull(studentId);
        this.testId = Objects.requireNonNull(testId);
        this.itemType = Objects.requireNonNull(itemType);
        this.answerPayload = Objects.requireNonNull(answerPayload);
    }

    public UUID id() { return id; }
    public String studentId() { return studentId; }
    public String testId() { return testId; }
    public ItemType itemType() { return itemType; }
    public String answerPayload() { return answerPayload; }

    /** Only machine-scorable types may receive provisional scores. */
    public boolean isMachineScorable() {
        return itemType == ItemType.MULTIPLE_CHOICE;
    }

    @Override public String toString() {
        return "Submission{id=%s, student=%s, test=%s, type=%s}"
                .formatted(id, studentId, testId, itemType);
    }
}
