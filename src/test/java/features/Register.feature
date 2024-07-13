Feature: User Registration

  @sanity
  Scenario Outline: User Register on the website
    Given I "WaitForPageToBeLoad" "10" seconds
    When I "Click" on "TagWithText" with values "a~Login or register"
    And I "Click" on "RadioOrCheckboxWithLabel" with values "Register Account"
    And I "ScrollToElement" on "TagWithAttribute" with values "button~title~Continue"
    And I "Click" on "TagWithAttribute" with values "button~title~Continue"
    And I "EnterValue" "<firstname>" for "TagWithAttribute" with values "input~name~firstname"
    And I "EnterValue" "<lastname>" for "TagWithAttribute" with values "input~name~lastname"
    And I "EnterValue" "<email>" for "TagWithAttribute" with values "input~name~email"
    And I "EnterValue" "<address_1>" for "TagWithAttribute" with values "input~name~address_1"
    And I "Click" on "TagWithAttribute" with values "select~name~country_id"
    And I "Click" on "DropDownOption" with values "<country>"
    And I "Click" on "TagWithAttribute" with values "select~name~zone_id"
    And I "Click" on "DropDownOption" with values "<state>"
    And I "EnterValue" "<city>" for "TagWithAttribute" with values "input~name~city"
    And I "EnterValue" "<postcode>" for "TagWithAttribute" with values "input~name~postcode"
    And I "EnterValue" "<loginname>" for "TagWithAttribute" with values "input~name~loginname"
    And I "EnterValue" "<password>" for "TagWithAttribute" with values "input~name~password"
    And I "EnterValue" "<password>" for "TagWithAttribute" with values "input~name~confirm"
    And I "Click" on "RadioOrCheckboxWithLabel" with values "No"
    And I "Click" on "RadioOrCheckboxWithLabel" with values "I have read and agree"
    And I "Click" on "ButtonWithText" with values "Continue"
    Then I "VerifyVisibility" is "true" for "TagWithText" with values "span~<accountConfirmationText>"

    Examples: 
      | firstname | lastname | email            | address_1 | country | state         | city  | postcode | loginname | password | accountConfirmationText       |
      | Shabbir   | Rayeen   | xyz123@gmail.com | ABC Nagar | India   | Uttar Pradesh | Konch |   285205 | Shab28932 |   123456 | Your Account Has Been Created |
