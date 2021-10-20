import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CartServiceTest {

    @BeforeEach
    public void setUp(){

    }
    @Test
    public void selecting_item_and_able_to_add_item_and_quantity_increase_size_of_the_cart_to_1(){
        assertTrue(false);
    }
    @Test
    public void selecting_item_and_able_to_add_item_add_quantity_in_the_cart_return_total_price_of_food_item(){
        assertTrue(false);
    }
    @Test
    public void after_selecting_and_adding_multiple_items_to_cart_should_be_able_to_return_total_amount(){
        assertTrue(false);
    }
    @Test
    public void removing_food_item_from_the_cart_should_reduce_the_cart_size_by_1(){
        assertTrue(false);
    }
    @Test
    public void removing_food_item_from_the_cart_which_is_not_exist_should_throw_exception(){
        assertTrue(false);
    }

}
