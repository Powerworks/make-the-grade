Feature: Standardized testing registration (grades 3-12)

  Background: The state operates testing at centers, mostly on school
  premises but not all. Students may only test at a center.

  Scenario: A state-eligible student registers at a testing center
    Given a student in grade 8
    And the student is enrolled at an in-person center
    When they submit registration
    Then registration is accepted
    And a session is scheduled for their school year

  Scenario: An out-of-mandate student cannot test
    Given a student in grade 2
    When they attempt registration
    Then registration is refused

  Scenario: Students test at centers, not at home
    Given a student in grade 10
    When a remote-home test session is offered
    Then the session is not created
