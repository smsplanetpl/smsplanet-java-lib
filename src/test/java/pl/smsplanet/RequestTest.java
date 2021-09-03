package pl.smsplanet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

class RequestTest {

    public static final String apiKey = "EnterYourKeyHere";
    public static final String password = "EnterYourPasswordHere";

    @org.junit.jupiter.api.Test
    void sendSMS() throws IOException {
        String sender = "Informacja";
        String message = "Sample text.";
        String[] numbers = new String[] {"505600900"};
        SmsplanetRequest request = SmsplanetRequest.sendSMS(apiKey, password, sender, message, numbers);
        request.setTest(true);
        SmsplanetResponse response = request.execute();
        Assertions.assertEquals(0, response.getErrorCode());
        Assertions.assertNotEquals("0", response.getMessageId());
    }

    @org.junit.jupiter.api.Test
    void sendSMS1000() throws IOException {
        String sender = "Informacja";
        String message = "Sample text.";
        String[] numbers = new String[1000];
        for(int i = 0; i < numbers.length; i++)
            numbers[i] = Integer.toString(505600900 + i);
        SmsplanetRequest request = SmsplanetRequest.sendSMS(apiKey, password, sender, message, numbers);
        request.setTest(true);
        SmsplanetResponse response = request.execute();
        Assertions.assertEquals(0, response.getErrorCode());
    }

    @org.junit.jupiter.api.Test
    void sendSMSDate() throws IOException {
        String sender = "Informacja";
        String message = "Sample text.";
        String[] numbers = new String[] {"505600900"};
        SmsplanetRequest request = SmsplanetRequest.sendSMS(apiKey, password, sender, message, numbers);
        request.setTest(true);
        request.setDate("21-05-2022 10:05:00");
        SmsplanetResponse response = request.execute();
        Assertions.assertEquals(0, response.getErrorCode());
        Assertions.assertNotEquals("0", response.getMessageId());
    }

    @org.junit.jupiter.api.Test
    void sendSMSParam() throws IOException {
        String sender = "Informacja";
        String message = "Sample text [%parametr1%].";
        String[] numbers = new String[] {"505600900"};
        SmsplanetRequest request = SmsplanetRequest.sendSMS(apiKey, password, sender, message, numbers);
        request.setTest(true);
        request.addParam1("Karol");
        SmsplanetResponse response = request.execute();
        Assertions.assertEquals(0, response.getErrorCode());
        Assertions.assertNotEquals("0", response.getMessageId());
    }

    @org.junit.jupiter.api.Test
    void sendSMSParams4() throws IOException {
        String sender = "Informacja";
        String message = "[%parametr1%] and [%parametr2%] and [%parametr3%] and [%parametr4%].";
        String[] numbers = new String[] {"505600900"};
        SmsplanetRequest request = SmsplanetRequest.sendSMS(apiKey, password, sender, message, numbers);
        request.setTest(true);
        request.addParam1("This");
        request.addParam2("Is");
        request.addParam3("Next");
        request.addParam4("Param");
        SmsplanetResponse response = request.execute();
        Assertions.assertEquals(0, response.getErrorCode());
        Assertions.assertNotEquals("0", response.getMessageId());
    }

    @org.junit.jupiter.api.Test
    void sendSMSParams4Set() throws IOException {
        String sender = "Informacja";
        String message = "[%parametr1%] [%parametr2%] [%parametr3%] [%parametr4%].";
        String[] numbers = new String[] {"505600900", "505600901"};
        SmsplanetRequest request = SmsplanetRequest.sendSMS(apiKey, password, sender, message, numbers);
        request.setTest(true);
        request.setParam1(new String[] {"Alice", "Bob"});
        request.setParam2(new String[] {"likes", "hates"});
        request.setParam3(new String[] {"apples", "bananas"});
        request.setParam4(new String[] {":)", ":("});
        SmsplanetResponse response = request.execute();
        System.out.println(response.toString());
        Assertions.assertEquals(0, response.getErrorCode());
        Assertions.assertNotEquals("0", response.getMessageId());
    }

    @Test
    void sendMMS() throws IOException {
        String sender = "Informacja";
        String message = "Sample text.";
        String subject = "Subject";
        String[] numbers = new String[] {"505600900"};
        SmsplanetRequest request = SmsplanetRequest.sendMMS(apiKey, password, sender, subject, message, numbers);
        request.setTest(true);
        SmsplanetResponse response = request.execute();
        Assertions.assertEquals(0, response.getErrorCode());
        Assertions.assertNotEquals("0", response.getMessageId());
    }

    // This test needs to send sms in order to work, this is why it is disabled.
    @Test
    @Disabled
    void cancelMessage() throws IOException {
        String sender = "Informacja";
        String message = "Sample text.";
        String[] numbers = new String[] {"505600900"};
        SmsplanetRequest request = SmsplanetRequest.sendSMS(apiKey, password, sender, message, numbers);
        SmsplanetResponse response = request.execute();
        request = SmsplanetRequest.cancelMessage(apiKey, password, response.getMessageId());
        response = request.execute();
        Assertions.assertEquals(0, response.getErrorCode());
        Assertions.assertTrue(response.result());
    }

    @Test
    void getBalance() throws IOException {
        SmsplanetRequest request = SmsplanetRequest.getBalance(apiKey, password);
        SmsplanetResponse response = request.execute();
        Assertions.assertEquals(0, response.getErrorCode());
        Assertions.assertNotEquals(-1, response.balance());
    }

    @Test
    void senderFields() throws IOException {
        String senderField = "sender1";
        SmsplanetRequest request = SmsplanetRequest.addSenderField(apiKey, password, senderField);
        SmsplanetResponse response = request.execute();
        Assertions.assertEquals(0, response.getErrorCode());
        Assertions.assertTrue(response.result());
        request = SmsplanetRequest.getSenderFields(apiKey, password);
        response = request.execute();
        Assertions.assertEquals(0, response.getErrorCode());
        Assertions.assertNotNull(response.senderFields());
        Assertions.assertTrue(Arrays.asList(response.senderFields()).contains(senderField));
    }

    @Test
    void generateReport() throws IOException {
        String from = "21-05-2022 10:05:00";
        String to = "30-05-2022 10:05:00";
        SmsplanetRequest request = SmsplanetRequest.generateReport(apiKey, password, from, to);
        SmsplanetResponse response = request.execute();
        Assertions.assertEquals(0, response.getErrorCode());
        Assertions.assertTrue(response.result());
        Assertions.assertNotNull(response.getMessage());
    }

    // This test needs to send sms in order to work, this is why it is disabled.
    @Test
    @Disabled
    void getMessageInfo() throws IOException {
        String sender = "Informacja";
        String message = "Sample text.";
        String[] numbers = new String[] {"505600900"};
        SmsplanetRequest request = SmsplanetRequest.sendSMS(apiKey, password, sender, message, numbers);
        SmsplanetResponse response = request.execute();
        request = SmsplanetRequest.getMessageInfo(apiKey, password, response.getMessageId());
        response = request.execute();
        Assertions.assertEquals(0, response.getErrorCode());
        Assertions.assertTrue(response.result());
    }

    @Test
    void blacklist() throws IOException {
        String number = "505600900";
        SmsplanetRequest request = SmsplanetRequest.addBlacklist(apiKey, password, number);
        SmsplanetResponse response = request.execute();
        Assertions.assertEquals(0, response.getErrorCode());
        Assertions.assertTrue(response.result());
        request = SmsplanetRequest.removeBlacklist(apiKey, password, number);
        response = request.execute();
        Assertions.assertEquals(0, response.getErrorCode());
        Assertions.assertTrue(response.result());
    }

    @Test
    void shortUrl() throws IOException {
        String link = "https://smsplanet.pl/wysylka-sms.html";
        SmsplanetRequest request = SmsplanetRequest.shortUrl(apiKey, password, link);
        SmsplanetResponse response = request.execute();
        Assertions.assertEquals(0, response.getErrorCode());
        Assertions.assertNotNull(response.shortUrl());
    }

    @Test
    void partsCount() throws IOException {
        String message = "Sample text.";
        SmsplanetRequest request = SmsplanetRequest.partsCount(apiKey, password, message);
        SmsplanetResponse response = request.execute();
        Assertions.assertEquals(0, response.getErrorCode());
        Assertions.assertEquals(1, response.count());
    }
  
}