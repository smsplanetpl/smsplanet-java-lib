package pl.smsplanet;

import org.junit.jupiter.api.Assertions;

class ResponseTest {

    @org.junit.jupiter.api.Test
    void succeeded() {
        SmsplanetResponse response = new SmsplanetResponse("{\"messageId\":191919}");
        Assertions.assertTrue(response.succeeded);
        response = new SmsplanetResponse("invalid json");
        Assertions.assertFalse(response.succeeded);
    }

    @org.junit.jupiter.api.Test
    void getMessageId() {
        SmsplanetResponse response = new SmsplanetResponse("{\"messageId\":\"191919\"}");
        Assertions.assertEquals(0, response.getErrorCode());
        Assertions.assertEquals("191919", response.getMessageId());
    }

    @org.junit.jupiter.api.Test
    void getErrorCode() {
        SmsplanetResponse response = new SmsplanetResponse("{\"errorMsg\":\"Niepoprawny klucz.\",\"errorCode\":101}");
        Assertions.assertEquals(101, response.getErrorCode());
        Assertions.assertEquals("Niepoprawny klucz.", response.getErrorMessage());
    }

    @org.junit.jupiter.api.Test
    void getErrorCode199() {
        SmsplanetResponse response = new SmsplanetResponse("{\"errorMsg\":\"Niepoprawny klucz.\"}");
        Assertions.assertEquals(199, response.getErrorCode());
        Assertions.assertEquals("Niepoprawny klucz.", response.getErrorMessage());
    }

    @org.junit.jupiter.api.Test
    void invalidSyntax() {
        SmsplanetResponse response = new SmsplanetResponse("invalid json");
        Assertions.assertEquals(-1, response.getErrorCode());
        Assertions.assertNull(response.message);
    }
}