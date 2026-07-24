package com.dn.module4.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.inOrder;

/**
 * Exercise 6: Verifying Interaction Order
 *
 * Scenario: You need to ensure that methods are called in a specific order.
 */
@ExtendWith(MockitoExtension.class)
public class Exercise6_InteractionOrderTest {

    @Test
    void testInteractionOrder() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        // Call the methods in a specific order
        service.fetchData();
        service.notifyUser("data fetched");

        // Verify the interaction order
        InOrder inOrder = inOrder(mockApi);
        inOrder.verify(mockApi).getData();
        inOrder.verify(mockApi).sendNotification("data fetched");
    }
}
