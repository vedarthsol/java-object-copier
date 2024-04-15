package org.ordermanagement.copy.demo;

import org.junit.jupiter.api.BeforeEach;
import org.ordermanagement.copy.demo.models.Order;
import org.ordermanagement.copy.demo.models.OrderLine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class OrderCopyClientTest {

    public static final String ORDER_ID = UUID.randomUUID().toString();
    public static final String ORDER_DESCRIPTION = "My Original Order";
    public static final String LINE_1_DESCRIPTION = "line 1";

    private OrderCopyClient client;

    @BeforeEach
    public void setup() {
        client = new OrderCopyClient();
    }

    @Test
    @DisplayName("Shall copy overwrites original order line's description")
    public void shouldPerformShallowCopyOverwritesLine1Description() throws CloneNotSupportedException {

        // Create a dummy order
        Order originalOrder = generateDummyOrder();

        // Shallow copy our dummy order
        Order shallowCopiedOrder = client.performShallowCopy(originalOrder);

        // Update line 1 description and quantity
        shallowCopiedOrder.getLines().get(0).setDescription("shallow copied line 1 description");
        shallowCopiedOrder.getLines().get(0).setQuantity(5);

        // Assert that the original order line's description and quantity have now been overwritten
        assertThat(originalOrder.getLines().get(0).getDescription())
                .isEqualTo(shallowCopiedOrder.getLines().get(0).getDescription())
                .isEqualTo("shallow copied line 1 description");
        assertThat(originalOrder.getLines().get(0).getQuantity())
                .isEqualTo(shallowCopiedOrder.getLines().get(0).getQuantity())
                .isEqualTo(5);
    }

    @Test
    @DisplayName("Deep copy retains original order intact")
    public void shouldPerformDeepCopyRetainingOriginalOrderIntact() {

        // Create a dummy order
        Order originalOrder = generateDummyOrder();

        // Deep copy our dummy order
        Order deepCopiedOrder = client.performDeepCopy(originalOrder);

        // Update line 1 description and quantity
        deepCopiedOrder.getLines().get(0).setDescription("deep copied line 1 description");
        deepCopiedOrder.getLines().get(0).setQuantity(5);

        // Assert that the original order line has been retained as it is
        assertThat(originalOrder.getLines().get(0).getDescription())
                .isEqualTo(LINE_1_DESCRIPTION)
                .isNotEqualTo(deepCopiedOrder.getLines().get(0).getDescription());
        assertThat(originalOrder.getLines().get(0).getQuantity())
                .isEqualTo(1)
                .isNotEqualTo(deepCopiedOrder.getLines().get(0).getQuantity());
    }

    /**
     * A method that is used to create a dummy order.
     *
     * @return order The generated order object.
     */
    private Order generateDummyOrder() {
        OrderLine orderLine1 = new OrderLine(LINE_1_DESCRIPTION, 1);
        List<OrderLine> lines = new ArrayList<>();
        lines.add(orderLine1);
        return new Order(ORDER_ID, ORDER_DESCRIPTION, lines);
    }

}