import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
  private static List<Restaurant> restaurants = new ArrayList<>();

  public Restaurant findRestaurantByName(String restaurantName) throws RestaurantNotFoundException {
    return restaurants.stream()
        .parallel()
        .filter(res -> res.getName().equalsIgnoreCase(restaurantName))
        .findFirst()
        .orElseThrow(() -> new RestaurantNotFoundException(restaurantName));
  }

  public Restaurant addRestaurant(
      String name, String location, LocalTime openingTime, LocalTime closingTime) {
    Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
    restaurants.add(newRestaurant);
    return newRestaurant;
  }

  public Restaurant removeRestaurant(String restaurantName) throws RestaurantNotFoundException {
    Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
    restaurants.remove(restaurantToBeRemoved);
    return restaurantToBeRemoved;
  }

  public List<Restaurant> getRestaurants() {
    return restaurants;
  }
}
