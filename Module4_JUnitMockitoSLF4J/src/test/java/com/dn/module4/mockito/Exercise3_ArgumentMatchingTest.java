package com.dn.module4.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Exercise 3: Argument Matching
 *
 * Scenario: You need to verify that a method is called with specific arguments.
 */
@ExtendWith(MockitoExtension.class)
public class Exercise3_ArgumentMatchingTest {

    @Test
    void testArgumentMatchers() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Stub using an argument matcher: any String key returns "Matched Data"
        when(mockApi.getData(anyString())).thenReturn("Matched Data");

        MyService service = new MyService(mockApi);
        String result = service.fetchData("orders");

        assertEquals("Matched Data", result);

        // Verify it was called with that exact key
        verify(mockApi).getData(eq("orders"));
    }
}
