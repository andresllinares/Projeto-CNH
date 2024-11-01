Feature: Another User Feature

  Scenario: Validate another user JSON against the schema
    Given a JSON payload that contains user data
    When I validate the JSON payload against the schema
    Then the JSON should be valid according to the schema
