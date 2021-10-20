import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItem {
    private String foodItemName;
    private int quantity;
    private double price;

    public CartItem(String foodItemName, int quantity, double price) {
        this.foodItemName = foodItemName;
        this.quantity = quantity;
        this.price = price;
    }
}
