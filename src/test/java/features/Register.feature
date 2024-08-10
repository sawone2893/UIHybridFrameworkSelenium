Feature: User Registration

  @sanity
  Scenario Outline: User Register on the website
    Given I "WaitForPageToBeLoad" "10" seconds
    When I perform click on multiple web element using following dataset
      | locatorIdentifier        | params              |
      | TagWithText              | a~Login or register |
      | RadioOrCheckboxWithLabel | Register Account    |
    When I "ScrollToElement" on "TagWithAttribute" with values "button~title~Continue"
    And I "Click" on "TagWithAttribute" with values "button~title~Continue"
    When I perform following actions using dataset
      | action     | value       | locatorIdentifier | params               |
      | EnterValue | <firstname> | TagWithAttribute  | input~name~firstname |
      | EnterValue | <lastname>  | TagWithAttribute  | input~name~lastname  |
      | EnterValue | <email>     | TagWithAttribute  | input~name~email     |
      | EnterValue | <address_1> | TagWithAttribute  | input~name~address_1 |
      | EnterValue | <lastname>  | TagWithAttribute  | input~name~lastname  |
      | EnterValue | <lastname>  | TagWithAttribute  | input~name~lastname  |
    When I perform click on multiple web element using following dataset
      | locatorIdentifier | params                 |
      | TagWithAttribute  | select~name~country_id |
      | DropDownOption    | <country>              |
      | TagWithAttribute  | select~name~zone_id    |
      | DropDownOption    | <state>                |
      | DropDownOption    | <country>              |
      | DropDownOption    | <country>              |
    When I perform following actions using dataset
      | action     | value       | locatorIdentifier | params               |
      | EnterValue | <city>      | TagWithAttribute  | input~name~city      |
      | EnterValue | <postcode>  | TagWithAttribute  | input~name~postcode  |
      | EnterValue | <loginname> | TagWithAttribute  | input~name~loginname |
      | EnterValue | <password>  | TagWithAttribute  | input~name~password  |
      | EnterValue | <password>  | TagWithAttribute  | input~name~confirm   |
    When I perform click on multiple web element using following dataset
      | locatorIdentifier        | params                |
      | RadioOrCheckboxWithLabel | No                    |
      | RadioOrCheckboxWithLabel | I have read and agree |
      | ButtonWithText           | Continue              |
    Then I "VerifyVisibility" is "true" for "TagWithText" with values "span~<accountConfirmationText>"

    Examples: 
      | firstname | lastname | email             | address_1 | country | state           | city  | postcode | loginname | password | accountConfirmationText       |
      | Shabbir   | Rayeen   | xyz1263@gmail.com | ABC Nagar | India   | Salomon Islands | Konch |   285205 | Shab28932 |   123456 | Your Account Has Been Created |
