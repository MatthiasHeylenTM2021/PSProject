package thomasmore.itf.project.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import thomasmore.itf.project.model.Exchange;
import thomasmore.itf.project.service.ExchangeClient;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;

@Controller
public class MainController {

    private final String apiKey = "Z5U4dVI11vYc7OsMmIcGepcSxhmcLc4v";
    private ArrayList<String> currencyNameArrayList;
    private Exchange exchange;

    @PostConstruct
    private void initializeData() throws IOException {
        ExchangeClient exchangeClient = new ExchangeClient();
        JSONObject jsonObject = exchangeClient.readJsonFromUrl("https://api.apilayer.com/exchangerates_data/latest?apikey=" + apiKey);
        exchange = new Exchange();
        JSONArray jsonArray = jsonObject.getJSONObject("rates").names();
        exchange.setCurrencyNames(jsonArray);
        currencyNameArrayList = exchange.getCurrencyNames();
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("currencyNameArrayList", currencyNameArrayList);

        return "index";
    }

    @RequestMapping("/result")
    public String result(HttpServletRequest request, Model model) throws IOException {
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String amount = request.getParameter("amount");
        ExchangeClient exchangeClient = new ExchangeClient();

        JSONObject jsonObject = exchangeClient.readJsonFromUrl("https://api.apilayer.com/exchangerates_data/convert?apikey=" + apiKey + "&from=" + from + "&to=" + to + "&amount=" + amount);

        double result = Double.parseDouble(jsonObject.get("result").toString());

        model.addAttribute("currencyNameArrayList", currencyNameArrayList);
        model.addAttribute("from", from);
        model.addAttribute("to", to);
        model.addAttribute("result", result);

        return "result";
    }

}