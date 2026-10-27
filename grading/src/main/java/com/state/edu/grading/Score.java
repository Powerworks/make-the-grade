package com.state.edu.grading;

import java.util.Objects;
import java.util.Optional;

/**
 * A score assigned to a submission. Essay/short-answer scores are human
 * assigned; multiple-choice may be provisional until a key review.
 */
public final class Score {

    public enum Status { PROVISIONAL, FINAL }

    private final String submissionId;
    private final int pointsAwarded;
    private final int pointsPossible;
    private final Status status;
    private final String gradedBy;

    public Score(String submissionId, int pointsAwarded, int pointsPossible,
                 Status status, String gradedBy) {
        if (pointsAwarded < 0 || pointsAwarded > pointsPossible) {
            throw new IllegalArgumentException("awarded score out of range");
        }
        this.submissionId = Objects.requireNonNull(submissionId);
        this.pointsAwarded = pointsAwarded;
        this.pointsPossible = pointsPossible;
        this.status = Objects.requireNonNull(status);
        this.gradedBy = Objects.requireNonNull(gradedBy);
    }

    public String submissionId() { return submissionId; }
    public int pointsAwarded() { return pointsAwarded; }
    public int pointsPossible() { return pointsPossible; }
    public Status status() { return status; }
    public String gradedBy() { return gradedBy; }

    public double percentage() {
        return (double) pointsAwarded / pointsPossible * 100.0;
    }
}
