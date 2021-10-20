import lombok.Getter;

@Getter
public class Item {
  private String name;
  private double price;

  public Item(String name, int price) {
    this.name = name;
    this.price = price;
  }

  @Override
  public String toString() {
    return name + ":" + price + "\n";
  }
}
