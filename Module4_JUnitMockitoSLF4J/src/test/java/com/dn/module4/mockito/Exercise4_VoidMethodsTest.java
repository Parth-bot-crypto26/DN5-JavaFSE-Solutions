package com.dn.module4.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

/**
 * Exercise 4: Handling Void Methods
 *
 * Scenario: You need to test a void method that performs some action.
 */
@ExtendWith(MockitoExtension.class)
public class Exercise4_VoidMethodsTest {

    @Test
    void testVoidMethodStubbing() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // 2. Stub the void method (doNothing is the default, but shown explicitly)
        doNothing().when(mockApi).sendNotification("Hello");

        MyService service = new MyService(mockApi);
        service.notifyUser("Hello");

        // 3. Verify the interaction
        verify(mockApi).sendNotification("Hello");
    }
}
