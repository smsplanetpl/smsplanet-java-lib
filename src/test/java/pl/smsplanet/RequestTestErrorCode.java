package pl.smsplanet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;

import static pl.smsplanet.RequestTest.apiKey;
import static pl.smsplanet.RequestTest.password;

class RequestTestErrorCode {

    @Test
    void sendSMSErrorCode101() throws IOException {
        String sender = "Informacja";
        String message = "Sample text.";
        String[] numbers = new String[] {"505600900"};
        SmsplanetRequest request = SmsplanetRequest.sendSMS(apiKey.substring(1), password, sender, message, numbers);
        request.setTest(true);
        SmsplanetResponse response = request.execute();
        Assertions.assertEquals(101, response.getErrorCode());
    }

    @Test
    void sendSMSErrorCode102() throws IOException {
        String sender = "Informacja";
        String message = "Sample text.";
        String[] numbers = new String[] {"505600900"};
        SmsplanetRequest request = SmsplanetRequest.sendSMS(apiKey, password.substring(1), sender, message, numbers);
        request.setTest(true);
        SmsplanetResponse response = request.execute();
        Assertions.assertEquals(102, response.getErrorCode());
    }

    @Test
    void sendSMSErrorCode103() throws IOException {
        String sender = "Invalid";
        String message = "Sample text.";
        String[] numbers = new String[] {"505600900"};
        SmsplanetRequest request = SmsplanetRequest.sendSMS(apiKey, password, sender, message, numbers);
        request.setTest(true);
        SmsplanetResponse response = request.execute();
        Assertions.assertEquals(103, response.getErrorCode());
    }

    @Test
    void sendSMSErrorCode104() throws IOException {
        String sender = "Informacja";
        String message = String.join("", Collections.nCopies(3000, "A"));
        String[] numbers = new String[] {"505600900"};
        SmsplanetRequest request = SmsplanetRequest.sendSMS(apiKey, password, sender, message, numbers);
        request.setTest(true);
        SmsplanetResponse response = request.execute();
        Assertions.assertEquals(104, response.getErrorCode());
    }

    @Test
    void sendSMSErrorCode106() throws IOException {
        String sender = "Informacja";
        String message = "Sample text.";
        String[] numbers = new String[0];
        SmsplanetRequest request = SmsplanetRequest.sendSMS(apiKey, password, sender, message, numbers);
        request.setTest(true);
        SmsplanetResponse response = request.execute();
        Assertions.assertEquals(106, response.getErrorCode());
    }

    @Test
    void sendSMSErrorCode108() throws IOException {
        String sender = "Informacja";
        String message = "Sample text.";
        String[] numbers = new String[] {"505600900"};
        SmsplanetRequest request = SmsplanetRequest.sendSMS(apiKey, password, sender, message, numbers);
        request.setTest(true);
        request.setDate("21-05-2022");
        SmsplanetResponse response = request.execute();
        Assertions.assertEquals(108, response.getErrorCode());
    }

    @Test
    void sendSMSErrorCode109() throws IOException {
        String sender = "Informacja";
        String message = "Sample text.";
        String[] numbers = new String[10000];
        for(int i = 0; i < numbers.length; i++)
            numbers[i] = Integer.toString(505600900 + i);
        SmsplanetRequest request = SmsplanetRequest.sendSMS(apiKey, password, sender, message, numbers);
        request.setTest(true);
        SmsplanetResponse response = request.execute();
        Assertions.assertEquals(109, response.getErrorCode());
    }

    @Test
    void sendSMSErrorCode111() throws IOException {
        String sender = "Informacja";
        String message = "Sample text.";
        String[] numbers = new String[10001];
        for(int i = 0; i < numbers.length; i++)
            numbers[i] = Integer.toString(505600900 + i);
        SmsplanetRequest request = SmsplanetRequest.sendSMS(apiKey, password, sender, message, numbers);
        request.setTest(true);
        SmsplanetResponse response = request.execute();
        Assertions.assertEquals(111, response.getErrorCode());
    }

    @Test
    void sendSMSErrorCode116() throws IOException {
        String sender = "Informacja";
        String message = "Sample text [%parametr1%].";
        String[] numbers = new String[] {"505600900"};
        SmsplanetRequest request = SmsplanetRequest.sendSMS(apiKey, password, sender, message, numbers);
        request.setTest(true);
        request.addParam1("Karol");
        request.addParam1("Extra");
        SmsplanetResponse response = request.execute();
        Assertions.assertEquals(116, response.getErrorCode());
    }
}