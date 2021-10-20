import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RestaurantServiceTest {

  RestaurantService service;
  Restaurant restaurant;
  String restaurantName = "Amelie's cafe";

  @BeforeEach
  public void setUpBeforeEach() {
    service = new RestaurantService();
    LocalTime openingTime = LocalTime.parse("10:30:00");
    LocalTime closingTime = LocalTime.parse("22:00:00");
    restaurant = service.addRestaurant(restaurantName, "Chennai", openingTime, closingTime);
    restaurant.addToMenu("Sweet corn soup", 119);
    restaurant.addToMenu("Vegetable lasagne", 269);
  }

  // >>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
  @Test
  public void searching_for_existing_restaurant_should_return_expected_restaurant_object()
      throws RestaurantNotFoundException {
    // WRITE UNIT TEST CASE HERE
    assertEquals(restaurantName, service.findRestaurantByName(restaurantName).getName());
  }

  // You may watch the video by Muthukumaran on how to write exceptions in Course 3: Testing and
  // Version control: Optional content
  @Test
  public void searching_for_non_existing_restaurant_should_throw_exception()
      throws RestaurantNotFoundException {
    String invalidRestaurant = "tsdfsdfs";
    assertThrows(
        RestaurantNotFoundException.class, () -> service.findRestaurantByName(invalidRestaurant));
  }
  // <<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>

  // >>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
  @Test
  public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1()
      throws RestaurantNotFoundException {
    LocalTime openingTime = LocalTime.parse("12:30:00");
    LocalTime closingTime = LocalTime.parse("23:00:00");
    restaurant = service.addRestaurant("Test cafe", "Chennai", openingTime, closingTime);
    restaurant.addToMenu("Sweet corn soup", 119);
    restaurant.addToMenu("Vegetable lasagne", 269);

    int initialNumberOfRestaurants = service.getRestaurants().size();
    service.removeRestaurant("Test cafe");
    assertEquals(initialNumberOfRestaurants - 1, service.getRestaurants().size());
  }

  @Test
  public void removing_restaurant_that_does_not_exist_should_throw_exception()
      throws RestaurantNotFoundException {
    assertThrows(RestaurantNotFoundException.class, () -> service.removeRestaurant("Pantry d'or"));
  }

  @Test
  public void add_restaurant_should_increase_list_of_restaurants_size_by_1() {
    int initialNumberOfRestaurants = service.getRestaurants().size();
    service.addRestaurant(
        "Pumpkin Tales", "Chennai", LocalTime.parse("12:00:00"), LocalTime.parse("23:00:00"));
    assertEquals(initialNumberOfRestaurants + 1, service.getRestaurants().size());
  }
  }
  // <<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
  // <<<<<<<<<<<<<<<<<<<< Order: Adding order and calculate total price >>>>>>>>>>>>>>>>>>>>>>>>>>>
/*
@Test
  public void On_selected_items_should_calcuate_the_total_price_of_selected_item_and_return_the_total_price() throws ItemNotFoundException{

}
*/

