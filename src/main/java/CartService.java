import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartService {
  private Restaurant restaurant;
  private Map<String, CartItem> cartItems;

  public CartService(Restaurant restaurant) {
    cartItems = new HashMap<>();
    this.restaurant = restaurant;
  }

  public void addItemToCart(Item foodItem, int quantity) {
    String itemName = foodItem.getName();

    if (cartItems.containsKey(itemName)) {
      CartItem currentCartItem = cartItems.get(itemName);
      currentCartItem.setQuantity(currentCartItem.getQuantity() + quantity);
      currentCartItem.setPrice(foodItem.getPrice() * currentCartItem.getQuantity());
      cartItems.put(itemName, currentCartItem);
    } else {
      cartItems.putIfAbsent(
          itemName, new CartItem(itemName, quantity, quantity * foodItem.getPrice()));
    }
  }

  public void removeItemFromCartByQuantity(Item foodItem, int quantity)
      throws InvalidQuantityException {
    String itemName = foodItem.getName();
    if (cartItems.containsKey(itemName) && cartItems.get(itemName).getQuantity() >= quantity) {
      if (cartItems.get(itemName).getQuantity() == quantity) {
        cartItems.remove(itemName);
      } else {
        CartItem cartItem = cartItems.get(itemName);
        cartItem.setQuantity(cartItem.getQuantity() - quantity);
        cartItem.setPrice(foodItem.getPrice() * cartItem.getQuantity());
        cartItems.put(itemName, cartItem);
      }
    } else {
      throw new InvalidQuantityException("Invalid quantity" + quantity + " for item");
    }
  }

  public void removeItemFromCart(Item foodItem) {
    cartItems.remove(foodItem.getName());
  }

  public void discardCart() {
    cartItems.clear();
  }

  public List<CartItem> getCartItems() {
    return cartItems.values().stream().toList();
  }

  public Double totalPrice() {
    return cartItems.values().stream().map(CartItem::getPrice).reduce(Double::sum).orElse(0.0);
  }
}
