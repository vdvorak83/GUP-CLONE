package ua.com.itproekt.gup.server.api.model.profiles;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StorePhones {

    public StorePhones(){
    }

    public StorePhones( List<Long> mainPhones, List<PhoneSynhronize> contactPhones){
        this.mainPhones = mainPhones;
        this.contactPhones = contactPhones;
    }

    public StorePhones(String idUser, List<Long> mainPhones, List<PhoneSynhronize> contactPhones){
        this.idUser = idUser;
        this.mainPhones = mainPhones;
        this.contactPhones = contactPhones;
    }


    private String idUser;
    private List<Long> mainPhones;
    private List<PhoneSynhronize> contactPhones;


    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public List<Long> getMainPhones() {
        return mainPhones;
    }

    public void setMainPhones(List<Long> mainPhones) {
        this.mainPhones = mainPhones;
    }

    public List<PhoneSynhronize> getContactPhones() {
        return contactPhones;
    }

    public void setContactPhones(List<PhoneSynhronize> contactPhones) {
        this.contactPhones = contactPhones;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}

