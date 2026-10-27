Feature: Consolidated state-wide reporting

  Background: 40,000+ students, 2,000 graders, 50 administrators;
  results eventually consolidated to a single location representing
  all tests, with reporting by school, teacher, and student.

  Scenario: Scores roll up as submissions are graded
    Given graded submissions exist in the grading context
    When reporting ingests them
    Then the consolidated report reflects them
    And can be queried by school

  Scenario: Reporting never mutates source scores
    Given a graded submission exists
    When a report is generated
    Then the grading context is unchanged
