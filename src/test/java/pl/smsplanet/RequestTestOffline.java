package pl.smsplanet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RequestTestOffline {

    @Test
    void getURI() {
        SmsplanetRequest request = new SmsplanetRequest("simple");
        Assertions.assertEquals("https://api2.smsplanet.pl/simple", request.getURI().toString());
        request = new SmsplanetRequest("longer/path");
        Assertions.assertEquals("https://api2.smsplanet.pl/longer/path", request.getURI().toString());
        request = new SmsplanetRequest("/longer/path/slash/");
        Assertions.assertEquals("https://api2.smsplanet.pl/longer/path/slash/", request.getURI().toString());
    }

    @Test
    void setDate() {
        SmsplanetRequest request = new SmsplanetRequest("simple");
        Assertions.assertFalse(request.parameters.containsKey("date"));
        request.setDate("any-date");
        Assertions.assertEquals("any-date", request.parameters.get("date"));
    }

    @Test
    void setName() {
        SmsplanetRequest request = new SmsplanetRequest("simple");
        Assertions.assertFalse(request.parameters.containsKey("name"));
        request.setName("any-name");
        Assertions.assertEquals("any-name", request.parameters.get("name"));
    }

    @Test
    void setClearPolish() {
        SmsplanetRequest request = new SmsplanetRequest("simple");
        Assertions.assertFalse(request.parameters.containsKey("clear_polish"));
        request.setClearPolish(false);
        Assertions.assertEquals("0", request.parameters.get("clear_polish"));
        request.setClearPolish(true);
        Assertions.assertEquals("1", request.parameters.get("clear_polish"));
    }

    @Test
    void setTest() {
        SmsplanetRequest request = new SmsplanetRequest("simple");
        Assertions.assertFalse(request.parameters.containsKey("test"));
        request.setTest(false);
        Assertions.assertEquals("0", request.parameters.get("test"));
        request.setTest(true);
        Assertions.assertEquals("1", request.parameters.get("test"));
    }

    @Test
    void setCompanyID() {
        SmsplanetRequest request = new SmsplanetRequest("simple");
        Assertions.assertFalse(request.parameters.containsKey("company_id"));
        request.setCompanyID("any-id");
        Assertions.assertEquals("any-id", request.parameters.get("company_id"));
    }
}