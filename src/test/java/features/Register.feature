Feature: User Registration

  @sanity
  Scenario Outline: User Register on the website
    Given I "VerifyPageTitle" "A place to practice your automation skills!"
    When I perform click on multiple web element using following dataset
      | locatorIdentifier        | locatorType | params              |
      | TagWithText              | XPATH       | a~Login or register |
      | RadioOrCheckboxWithLabel | XPATH       | Register Account    |
    When I "ScrollToElement" on "TagWithAttribute" with "XPATH" values "button~title~Continue"
    And I "Click" on "TagWithAttribute" with "XPATH" values "button~title~Continue"
    When I perform following actions using dataset
      | action     | value       | locatorIdentifier | locatorType | params               |
      | EnterValue | <firstname> | TagWithAttribute  | XPATH       | input~name~firstname |
      | EnterValue | <lastname>  | TagWithAttribute  | XPATH       | input~name~lastname  |
      | EnterValue | <email>     | TagWithAttribute  | XPATH       | input~name~email     |
      | EnterValue | <address_1> | TagWithAttribute  | XPATH       | input~name~address_1 |
      | EnterValue | <lastname>  | TagWithAttribute  | XPATH       | input~name~lastname  |
      | EnterValue | <lastname>  | TagWithAttribute  | XPATH       | input~name~lastname  |
    When I perform click on multiple web element using following dataset
      | locatorIdentifier | locatorType | params                 |
      | TagWithAttribute  | XPATH       | select~name~country_id |
      | DropDownOption    | XPATH       | <country>              |
      | TagWithAttribute  | XPATH       | select~name~zone_id    |
      | DropDownOption    | XPATH       | <state>                |
      | DropDownOption    | XPATH       | <country>              |
      | DropDownOption    | XPATH       | <country>              |
    When I perform following actions using dataset
      | action     | value       | locatorIdentifier | locatorType | params               |
      | EnterValue | <city>      | TagWithAttribute  | XPATH       | input~name~city      |
      | EnterValue | <postcode>  | TagWithAttribute  | XPATH       | input~name~postcode  |
      | EnterValue | <loginname> | TagWithAttribute  | XPATH       | input~name~loginname |
      | EnterValue | <password>  | TagWithAttribute  | XPATH       | input~name~password  |
      | EnterValue | <password>  | TagWithAttribute  | XPATH       | input~name~confirm   |
    When I perform click on multiple web element using following dataset
      | locatorIdentifier        | locatorType | params                |
      | RadioOrCheckboxWithLabel | XPATH       | No                    |
      | RadioOrCheckboxWithLabel | XPATH       | I have read and agree |
      | ButtonWithText           | XPATH       | Continue              |
    Then I "VerifyVisibility" is "true" for "TagWithText" with "XPATH" values "span~<accountConfirmationText>"

    Examples: 
      | firstname | lastname | email               | address_1 | country | state           | city  | postcode | loginname  | password | accountConfirmationText       |
      | Shabbir   | Rayeen   | xyz132653@gmail.com | ABC Nagar | India   | Salomon Islands | Konch |   285205 | Shab289329 |   123456 | Your Account Has Been Created |
