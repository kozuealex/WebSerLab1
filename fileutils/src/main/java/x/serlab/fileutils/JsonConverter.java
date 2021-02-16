package x.serlab.fileutils;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class JsonConverter {

    private Gson gson;

    public JsonConverter() {
        gson = new Gson();
    }

    public String convertToJson(Object object) {
        return gson.toJson(object);
    }

    public List convertFromJson(String string) {
        return gson.fromJson(string, ArrayList.class);
    }


}
