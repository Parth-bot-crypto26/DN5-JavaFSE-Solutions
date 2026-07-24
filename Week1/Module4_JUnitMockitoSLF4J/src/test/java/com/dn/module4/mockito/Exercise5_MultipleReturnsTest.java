package com.dn.module4.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Exercise 5: Mocking and Stubbing with Multiple Returns
 *
 * Scenario: You need to test a service that depends on an external API
 * with multiple return values on consecutive calls.
 */
@ExtendWith(MockitoExtension.class)
public class Exercise5_MultipleReturnsTest {

    @Test
    void testConsecutiveCalls() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Stub different values on consecutive calls
        when(mockApi.getData())
                .thenReturn("First Call")
                .thenReturn("Second Call")
                .thenReturn("Third Call");

        MyService service = new MyService(mockApi);

        assertEquals("First Call", service.fetchData());
        assertEquals("Second Call", service.fetchData());
        assertEquals("Third Call", service.fetchData());
        // Mockito keeps returning the last stubbed value after the list is exhausted
        assertEquals("Third Call", service.fetchData());
    }
}
