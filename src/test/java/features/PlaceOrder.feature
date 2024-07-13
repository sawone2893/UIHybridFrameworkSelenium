Feature: User Place Order
	
	@sanity
  Scenario Outline: Verify user is able to place order
    Given I "WaitForPageToBeLoad" "10" seconds
    When I "Click" on "TagWithText" with values "a~Login or register"
    And I "EnterValue" "<loginname>" for "TagWithAttribute" with values "input~name~loginname"
    And I "EnterValue" "<password>" for "TagWithAttribute" with values "input~name~password"
    And I "Click" on "ButtonWithText" with values "Login"
    Then I "VerifyVisibility" is "true" for "TagWithText" with values "div~Welcome back Shabbir"
    When I "Click" on "CategoryMenu" with values "Apparel & accessories"
    And I "Click" on "TagWithExactText" with values "a~Shoes"
    And I "Click" on "TagWithExactText" with values "a~New Ladies High Wedge Heel Toe Thong Diamante Flip Flop Sandals"
    And I "Click" on "RadioOrCheckboxWithLabel" with values "6 UK"
    And I "Click" on "DropDownSelect" with values "Colour"
    And I "Click" on "DropDownOption" with values "red"
    And I "EnterValue" "<quantity>" for "TagWithAttribute" with values "input~name~quantity"
    And I "Click" on "TagWithText" with values "a~Add to Cart"
    And I "Click" on "TagWithText" with values "a~Checkout"
    And I "Click" on "ButtonWithText" with values "Confirm Order"
    Then I "VerifyVisibility" is "true" for "TagWithText" with values "span~<OrderConfirmationText>"
    
    Examples:
            | loginname | password | quantity | OrderConfirmationText          |
            | Shab2893  | 123456   | 2        | Your Order Has Been Processed! |