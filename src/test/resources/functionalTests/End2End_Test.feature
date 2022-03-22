Feature: Verify authorized user
  Description: The purpose of these tests are to cover an authorized user is able to add and remove a book

  Background: User generate token
    Given I am an authorized user

    @smoke
    Scenario: Authorized user
      Given A list of books are available
      When I add a book to my reading list
      Then The book is added
      When I remove a book from my reading list
      Then The book is removed