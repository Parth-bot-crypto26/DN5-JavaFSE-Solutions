package com.dn.module4.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Exercise 1: Mocking and Stubbing  ⭐ MANDATORY
 *
 * Scenario: You need to test a service that depends on an external API.
 * Use Mockito to mock the external API and stub its methods.
 */
@ExtendWith(MockitoExtension.class)
public class Exercise1_MockingStubbingTest {

    @Test
    void testExternalApi() {
        // 1. Create a mock object for the external API
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // 2. Stub the method to return a predefined value
        when(mockApi.getData()).thenReturn("Mock Data");

        // 3. Write a test case that uses the mock object
        MyService service = new MyService(mockApi);
        String result = service.fetchData();

        assertEquals("Mock Data", result);
    }
}
