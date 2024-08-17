Feature: User Place Order

  @sanity
  Scenario Outline: Verify user is able to place order
    Given I "VerifyPageTitle" "A place to practice your automation skills!"
    When I "Click" on "TagWithText" with "XPATH" values "a~Login or register"
    Then I "VerifyPageTitle" "Account Login"
    When I perform following actions using dataset
      | action     | value       | locatorIdentifier | locatorType | params               |
      | EnterValue | <loginname> | TagWithAttribute  | XPATH       | input~name~loginname |
      | EnterValue | <password>  | TagWithAttribute  | XPATH       | input~name~password  |
    And I "Click" on "ButtonWithText" with "XPATH" values "Login"
    Then I "VerifyPageTitle" "My Account"
    Then I "VerifyVisibility" is "true" for "TagWithText" with "XPATH" values "div~Welcome back Shabbir"
    When I perform click on multiple web element using following dataset
      | locatorIdentifier        | locatorType | params                                                                  |
      | CategoryMenu             | XPATH       | Apparel & accessories                                                   |
      | TagWithExactText         | XPATH       | a~Shoes                                                                 |
      | TagWithAttribute         | XPATH       | a~title~New Ladies High Wedge Heel Toe Thong Diamante Flip Flop Sandals |
      | RadioOrCheckboxWithLabel | XPATH       | 6 UK                                                                    |
      | DropDownSelect           | XPATH       | Colour                                                                  |
      | DropDownOption           | XPATH       | red                                                                     |
    And I "EnterValue" "<quantity>" for "TagWithAttribute" with "XPATH" values "input~name~quantity"
    When I perform click on multiple web element using following dataset
      | locatorIdentifier | locatorType | params        |
      | TagWithText       | XPATH       | a~Add to Cart |
      | TagWithText       | XPATH       | a~Checkout    |
      | ButtonWithText    | XPATH       | Confirm Order |
    Then I "VerifyVisibility" is "true" for "TagWithText" with "XPATH" values "span~<OrderConfirmationText>"

    Examples: 
      | loginname | password | quantity | OrderConfirmationText          |
      | Shab2893  |   123456 |        2 | Your Order Has Been Processed! |
