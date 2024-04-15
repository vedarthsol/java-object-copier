package org.ordermanagement.copy.demo;

import org.ordermanagement.copy.demo.models.Order;

/**
 * A Client implementation to invoke Order copy functionality.
 */
public class OrderCopyClient {

    /**
     * A convenience method to perform a shallow copy of {Order}.
     *
     * @param order the order object to perform a copy of.
     * @return order the shallow copied order object.
     * @throws CloneNotSupportedException if clone is not supported.
     */
    public Order performShallowCopy(Order order) throws CloneNotSupportedException {
        return order.shallowCopy();
    }

    /**
     * A convenience method to perform a deep copy of {Order}.
     *
     * @param order the order object to perform a copy of.
     * @return order the deep copied order object.
     */
    public Order performDeepCopy(Order order) {
        return new Order(order);
    }


}