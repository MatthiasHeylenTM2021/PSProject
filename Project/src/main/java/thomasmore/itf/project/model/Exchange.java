package thomasmore.itf.project.model;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class Exchange {

    private final ArrayList<String> currencyNames = new ArrayList<>();

    public Exchange() {
    }

    public void setCurrencyNames(JSONArray names) {
        for (int i=0;i<names.length();i++){
            currencyNames.add(names.getString(i));
        }
        Collections.sort(currencyNames);
    }

    public ArrayList<String> getCurrencyNames() {
        return currencyNames;
    }

}
