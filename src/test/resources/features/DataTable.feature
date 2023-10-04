Feature: Verify the table data is sorted when table headers are clicked (ascending first, descending on second click)

  Background: User is on the "Data Tables" page
    Given user is on the main page
    When user clicks on "Sortable Data Tables" link
    Then user in on the "Data Tables" page with "/tables" in the path

  Scenario Outline: Table data is sorted by clicked header asc when clicked first, desc - when clicked again
    When user clicks on <column header name> once
    Then data is sorted by <column header name> in ascending order
    When user clicks on <column header name> again
    Then data is sorted by <column header name> in descending order

    Scenarios:
      | column header name |
      | Last Name          |
      | First Name         |
      | Email              |
      | Due                |
      | Web Site           |
      | Action             |