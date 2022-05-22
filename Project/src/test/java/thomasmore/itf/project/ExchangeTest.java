package thomasmore.itf.project;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import thomasmore.itf.project.model.Exchange;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ExchangeTest {

    @Test
    public void testGetterAndSetter() {

        Exchange exchange = new Exchange();
        JSONArray jsonArray = new JSONArray();
        jsonArray.put("EUR");
        jsonArray.put("GBP");
        jsonArray.put("USD");

        exchange.setCurrencyNames(jsonArray);
        assertEquals("EUR", exchange.getCurrencyNames().get(0));
        assertEquals("GBP", exchange.getCurrencyNames().get(1));
        assertEquals("USD", exchange.getCurrencyNames().get(2));
    }
}
