package com.dn.module4.mockito;

/**
 * Service under test for the Mockito exercises. It depends on ExternalApi,
 * which is mocked so the tests never make a real network/API call.
 */
public class MyService {

    private final ExternalApi externalApi;

    public MyService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    public String fetchData() {
        return externalApi.getData();
    }

    public String fetchData(String key) {
        return externalApi.getData(key);
    }

    public void notifyUser(String message) {
        externalApi.sendNotification(message);
    }

    public String fetchDataOrDefault() {
        try {
            String data = externalApi.getData();
            return data != null ? data : "DEFAULT";
        } catch (RuntimeException ex) {
            return "DEFAULT";
        }
    }
}
