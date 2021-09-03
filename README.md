# SMSPlanet JAVA API

JAR available [here](https://smsplanet.pl/wtyczki/smsplanet-java-lib-1.0.jar) or in 'build' repository folder.

## How to use

Sample code:
```java
import pl.smsplanet.Request;
import pl.smsplanet.Response;

import java.io.IOException;

public class Main {

    public static void main(String[] args) { 
		
	final String key = "YourAPIKey";
        final String password = "YourApiPassword";
        final String from = "TEST";
        final String msg = "Sample text.";
        final String[] to = new String[] {"505111222"};

	SmsplanetRequest request = SmsplanetRequest.sendSMS(key, password, from, msg, to);
        try {
            SmsplanetResponse response = request.execute();
            if (response.getErrorCode() == 0) {
                System.out.println("Wiadomosc zostala wyslana. Nadany identyfikator: " + response.getMessageId());
            } else {
                System.err.println("Blad przetwarzania: " + response.getErrorMessage());
            }
        } catch (IOException e) {
            System.err.println("Nie udalo sie nawiazac polaczenia.");
        }

	}
}

```
