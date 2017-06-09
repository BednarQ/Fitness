package com.example.mateuszbednarek.fitness;

/**
 * Created by Mateusz Bednarek on 03.06.2017.
 */

public class Config {
    public static final String URL_ADD="http://vps330737.ovh.net/addUser.php";
    public static final String URL_GET_ALL = "http://vps330737.ovh.net/getAllClients.php";
    public static final String URL_GET_SEL = "http://vps330737.ovh.net/getSelected.php";
    public static final String URL_UPDATE_SEL = "http://vps330737.ovh.net/updateSelected.php";
    public static final String URL_GET_CLASSES = "http://vps330737.ovh.net/getClasses.php";
    public static final String URL_DELETE_SEL = "http://10.0.2.2/webapp/deleteSelected.php";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_EMP_ID = "ID";
    public static final String KEY_EMP_NAME = "imie";
    public static final String KEY_EMP_SURNAME = "nazwisko";
    public static final String KEY_EMP_AGE = "wiek";
    public static final String KEY_EMP_GENDER = "plec";
    public static final String KEY_EMP_TYPE = "typ_karnetu";
    public static final String KEY_EMP_EXPIRE = "data_wygasniecia_karnetu";
    public static final String KEY_EMP_EMAIL = "email";
    public static final String KEY_EMP_PASS = "haslo";

    //JSON Tags Users
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "ID";
    public static final String TAG_NAME = "imie";
    public static final String TAG_SURNAME = "nazwisko";
    public static final String TAG_AGE = "wiek";
    public static final String TAG_GENDER="plec";
    public static final String TAG_TYPE = "typ_karnetu";
    public static final String TAG_EXPIRE= "data_wygasniecia_karnetu";
    public static final String TAG_EMAIL = "email";
    public static final String TAG_PASS = "haslo";
    //JSON Tags Classes
    public static final String TAG_TNAME = "imie";
    public static final String TAG_TSURNAME = "nazwisko";
    public static final String TAG_CNAME = "nazwa";
    public static final String TAG_FOR="dla_kogo";
    public static final String TAG_ROOM = "ID_sali";
    public static final String TAG_START= "godzina_rozpoczecia";
    public static final String TAG_END = "godzina_zakonczenia";


    //employee id to pass with intent
    public static final String CLIENT_ID = "client_id";
}

