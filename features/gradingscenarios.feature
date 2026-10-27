Feature: Grading behavior

  Background: Tests are multiple choice, short answer, and essay.
  Graders are 2,000 humans (assisted at the margin).

  Scenario: Essay answers require a human-assigned score
    Given an essay submission pending review
    When a grader assigns a score
    Then the submission is graded
    And the score is visible to reporting

  Scenario: Multiple-choice may be pre-scored automatically
    Given a multiple-choice submission
    When scoring keys are applied
    Then a provisional score is produced
    And it is flagged as provisional
