import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CartServiceServiceTest {
  CartService cartService;
  Restaurant restaurant;

  @BeforeEach
  public void setUp() {
    LocalTime openingTime = LocalTime.parse("10:30:00");
    LocalTime closingTime = LocalTime.parse("22:00:00");
    restaurant = Mockito.spy(new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime));
    restaurant.addToMenu("Sweet corn soup", 119);
    restaurant.addToMenu("Vegetable lasagne", 269);
    cartService = Mockito.spy(new CartService(restaurant));
  }

  @Test
  public void selecting_item_and_able_to_add_item_and_quantity_increase_size_of_the_cart_to_1() {
    Item mockedCustomerSelection = restaurant.getMenu().get(0);
    int customerSelectedQuantity = 2;
    cartService.addItemToCart(mockedCustomerSelection, customerSelectedQuantity);
    assertEquals(1, cartService.getCartItems().size());
    assertEquals(customerSelectedQuantity, cartService.getCartItems().get(0).getQuantity());
  }

  @Test
  public void
      selecting_item_and_able_to_add_item_add_quantity_in_the_cart_return_total_price_of_food_item() {
    Item mockedCustomerSelection1 = restaurant.getMenu().get(0);
    int customerSelectedQuantityForItem1 = 2;
    double expectedTotalPrice = 238;
    cartService.addItemToCart(mockedCustomerSelection1, customerSelectedQuantityForItem1);
    assertEquals(expectedTotalPrice, cartService.getCartItems().get(0).getPrice());
  }

  @Test
  public void
      after_selecting_and_adding_multiple_items_to_cart_should_be_able_to_return_total_amount() {
    Item mockedCustomerSelection1 = restaurant.getMenu().get(0);
    int customerSelectedQuantityForItem1 = 2;
    Item mockedCustomerSelection2 = restaurant.getMenu().get(1);
    int customerSelectedQuantityForItem2 = 4;
    double expectedTotalPrice = 1314;
    cartService.addItemToCart(mockedCustomerSelection1, customerSelectedQuantityForItem1);
    cartService.addItemToCart(mockedCustomerSelection2, customerSelectedQuantityForItem2);
    assertEquals(expectedTotalPrice, cartService.totalPrice());
  }

  @Test
  public void removing_food_item_from_the_cart_should_reduce_the_cart_size_by_1() {
    Item mockedCustomerSelection1 = restaurant.getMenu().get(0);
    int customerSelectedQuantityForItem1 = 2;
    Item mockedCustomerSelection2 = restaurant.getMenu().get(1);
    int customerSelectedQuantityForItem2 = 4;
    cartService.addItemToCart(mockedCustomerSelection1, customerSelectedQuantityForItem1);
    cartService.addItemToCart(mockedCustomerSelection2, customerSelectedQuantityForItem2);
    assertEquals(2, cartService.getCartItems().size());
    cartService.removeItemFromCart(mockedCustomerSelection1);
    assertEquals(1, cartService.getCartItems().size());
  }

  @Test
  public void reducing_food_item_quantity_should_update_the_cart_item_quantity()
      throws InvalidQuantityException {
    Item mockedCustomerSelection = restaurant.getMenu().get(0);
    int customerSelectedQuantity = 2;
    cartService.addItemToCart(mockedCustomerSelection, customerSelectedQuantity);
    assertEquals(1, cartService.getCartItems().size());
    assertEquals(customerSelectedQuantity, cartService.getCartItems().get(0).getQuantity());
    cartService.removeItemFromCartByQuantity(mockedCustomerSelection, 1);
    assertEquals(1, cartService.getCartItems().get(0).getQuantity());
  }

  @Test
  public void
      reducing_food_item_quantity_when_removing_quantity_is_greater_than_cart_quantity_then_throw_error() {
    Item mockedCustomerSelection = restaurant.getMenu().get(0);
    int customerSelectedQuantity = 2;
    cartService.addItemToCart(mockedCustomerSelection, customerSelectedQuantity);
    assertEquals(1, cartService.getCartItems().size());
    assertEquals(customerSelectedQuantity, cartService.getCartItems().get(0).getQuantity());
    assertThrows(
        InvalidQuantityException.class,
        () -> cartService.removeItemFromCartByQuantity(mockedCustomerSelection, 4));
  }
}
