@assignment
Feature: Verify Lexus site functionality

  Background: User navigates to the application
    Given I navigate to "https://www.lexus.com.sg"

  Scenario: Check banner exists on homepage
    Then masthead banner should display "ALL-NEW LEXUS RX HYBRID"

  Scenario: Check video exists and able to play
    And I click on required car model
    Then corresponding YouTube video should be loading in gallery

  Scenario: Check form functionality
    And I click on required car model
    And I book a test drive for "UX 300e"
    And I fill form for booking test drive
    |FirstName| LastName | EmailAddress      | PhoneNumber | PreferredDate | PreferredTime | PreferredConsultant| NumberOfPax | PreferredModels | TestDriveOptions           |
    |CPL      | Test     | qa@convertium.com | +65 91234567| 15 June 2023  | 18:00         |                    | 1           | RX 300          | Lexus Test Drive Concierge |
    Then submit button should be enabled