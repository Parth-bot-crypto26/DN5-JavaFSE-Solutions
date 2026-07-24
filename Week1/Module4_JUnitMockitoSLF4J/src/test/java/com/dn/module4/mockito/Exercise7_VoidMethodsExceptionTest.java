package com.dn.module4.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

/**
 * Exercise 7: Handling Void Methods with Exceptions
 *
 * Scenario: You need to test a void method that throws an exception.
 */
@ExtendWith(MockitoExtension.class)
public class Exercise7_VoidMethodsExceptionTest {

    @Test
    void testVoidMethodThrowsException() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Stub the void method to throw an exception
        doThrow(new RuntimeException("Notification service unavailable"))
                .when(mockApi).sendNotification("Hello");

        MyService service = new MyService(mockApi);

        assertThrows(RuntimeException.class, () -> service.notifyUser("Hello"));
        verify(mockApi).sendNotification("Hello");
    }
}
