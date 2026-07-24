package com.dn.module4.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Exercise 2: Verifying Interactions  ⭐ MANDATORY
 *
 * Scenario: You need to ensure that a method is called with specific arguments.
 */
@ExtendWith(MockitoExtension.class)
public class Exercise2_VerifyingInteractionsTest {

    @Test
    void testVerifyInteraction() {
        // 1. Create a mock object
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // 2. Call the method with specific arguments
        MyService service = new MyService(mockApi);
        service.fetchData();

        // 3. Verify the interaction happened exactly once
        verify(mockApi).getData();
        verify(mockApi, times(1)).getData();
    }
}
