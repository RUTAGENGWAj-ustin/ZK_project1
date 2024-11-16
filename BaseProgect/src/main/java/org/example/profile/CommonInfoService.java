package org.example.profile;

import java.util.ArrayList;
import java.util.List;

public class CommonInfoService {

    // Static method to get a list of countries
    public static List<String> getCountryList() {
        List<String> countries = new ArrayList<>();
        countries.add("United States");
        countries.add("Canada");
        countries.add("United Kingdom");
        countries.add("Germany");
        countries.add("France");
        countries.add("India");
        countries.add("Japan");
        countries.add("Australia");
        countries.add("South Africa");
        countries.add("Brazil");
        return countries;
    }
}
