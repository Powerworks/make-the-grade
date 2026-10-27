package com.state.edu.reporting;

import com.state.edu.grading.Score;
import com.state.edu.grading.Submission;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static com.state.edu.grading.Score.Status.*;
import static org.junit.jupiter.api.Assertions.*;

class ConsolidatedReportTest {

    @Test
    void totals_only_over_ingested_scores() {
        var scores = List.of(
                new Score(UUID.randomUUID().toString(), 3, 5, FINAL, "g-1"),
                new Score(UUID.randomUUID().toString(), 5, 5, PROVISIONAL, "machine"));
        var report = new ConsolidatedReport(scores);
        assertEquals(2, report.totalSubmissions());
        assertEquals(1, report.finals().size());
    }
}
