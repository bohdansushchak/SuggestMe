package bohdan.hushcha.sushchak.suggestme.models;

import java.util.ArrayList;


public class Model_country {

    String str_country;
    ArrayList<String> al_state;
    public Model_country() {
    }

    public Model_country(String str_country, ArrayList<String> al_state) {
        this.str_country = str_country;
        this.al_state = al_state;
    }


    public String getStr_country() {
        return str_country;
    }

    public void setStr_country(String str_country) {
        this.str_country = str_country;
    }

    public ArrayList getAl_state() {
        return al_state;
    }

    public void setAl_state(ArrayList al_state) {
        this.al_state = al_state;
    }
}
