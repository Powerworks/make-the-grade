package com.state.edu.grading;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.state.edu.grading.Score.Status.*;
import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    private final String sub = UUID.randomUUID().toString();

    @Test
    void provisional_mc_scores_are_flagged() {
        var score = new Score(sub, 4, 5, PROVISIONAL, "machine");
        assertEquals(80.0, score.percentage(), 0.001);
        assertEquals(PROVISIONAL, score.status());
    }

    @Test
    void essay_scores_are_human_and_final() {
        var score = new Score(sub, 12, 15, FINAL, "grader-2000");
        assertEquals(80.0, score.percentage(), 0.001);
    }

    @Test
    void score_cannot_exceed_possible_points() {
        assertThrows(IllegalArgumentException.class,
                () -> new Score(sub, 16, 15, FINAL, "grader-2000"));
    }
}
