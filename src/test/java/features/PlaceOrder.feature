Feature: User Place Order

  @sanity
  Scenario Outline: Verify user is able to place order
    Given I "WaitForPageToBeLoad" "10" seconds
    When I "Click" on "TagWithText" with values "a~Login or register"
    When I perform following actions using dataset
      | action     | value       | locatorIdentifier | params               |
      | EnterValue | <loginname> | TagWithAttribute  | input~name~loginname |
      | EnterValue | <password>  | TagWithAttribute  | input~name~password  |
    And I "Click" on "ButtonWithText" with values "Login"
    Then I "VerifyVisibility" is "true" for "TagWithText" with values "div~Welcome back Shabbir"
    When I perform click on multiple web element using following dataset
      | locatorIdentifier        | params                                                                  |
      | CategoryMenu             | Apparel & accessories                                                   |
      | TagWithExactText         | a~Shoes                                                                 |
      | TagWithAttribute         | a~title~New Ladies High Wedge Heel Toe Thong Diamante Flip Flop Sandals |
      | RadioOrCheckboxWithLabel | 6 UK                                                                    |
      | DropDownSelect           | Colour                                                                  |
      | DropDownOption           | red                                                                     |
    And I "EnterValue" "<quantity>" for "TagWithAttribute" with values "input~name~quantity"
    When I perform click on multiple web element using following dataset
      | locatorIdentifier | params        |
      | TagWithText       | a~Add to Cart |
      | TagWithText       | a~Checkout    |
      | ButtonWithText    | Confirm Order |
    Then I "VerifyVisibility" is "true" for "TagWithText" with values "span~<OrderConfirmationText>"

    Examples: 
      | loginname | password | quantity | OrderConfirmationText          |
      | Shab2893  |   123456 |        2 | Your Order Has Been Processed! |
