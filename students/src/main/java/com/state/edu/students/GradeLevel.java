package com.state.edu.students;

/** The state's eligible grades: 3 through 12 inclusive. */
public enum GradeLevel {

    GRADE_3, GRADE_4, GRADE_5, GRADE_6, GRADE_7,
    GRADE_8, GRADE_9, GRADE_10, GRADE_11, GRADE_12;

    /** Lowest grade the state tests. */
    public static final int LOWEST = 3;

    public static boolean eligibleForTesting(int grade) {
        return grade >= LOWEST && grade <= 12;
    }
}
