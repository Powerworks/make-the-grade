package com.state.edu.reporting;

import com.state.edu.grading.Score;
import com.state.edu.grading.Submission;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Query model over graded submissions — the consolidation point the kata
 * describes as "eventually consolidated to a single location".
 *
 * <p>Depends on the {@link grading} module only: reporting reads score
 * projections; it must never write back into grading. This one-directional
 * dependency is a compliance boundary.
 */
public final class ConsolidatedReport {

    private final List<Score> scores;

    public ConsolidatedReport(List<Score> scores) {
        this.scores = Collections.unmodifiableList(new ArrayList<>(scores));
    }

    public int totalSubmissions() { return scores.size(); }

    public List<Score> finals() {
        return scores.stream().filter(s -> s.status() == Score.Status.FINAL).toList();
    }

    @Override public String toString() {
        return "ConsolidatedReport{total=%d, finals=%d}".formatted(totalSubmissions(), finals().size());
    }
}
