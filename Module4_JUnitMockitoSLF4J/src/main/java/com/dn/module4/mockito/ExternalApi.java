package com.dn.module4.mockito;

/**
 * Represents a third-party/external dependency that MyService relies on.
 * In the exercises this is mocked with Mockito instead of being called for real.
 */
public interface ExternalApi {

    String getData();

    String getData(String key);

    void sendNotification(String message);
}
