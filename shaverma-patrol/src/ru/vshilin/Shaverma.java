package ru.vshilin;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;

import java.util.HashMap;

/**
 * Created by helpdesk on 01.08.2016.
 */
public class Shaverma {
    HashMap<String, String> attrMap;

    public Shaverma(DataSnapshot snapshot){
        attrMap = new HashMap<String, String>();

        for (DataSnapshot ch : snapshot.getChildren()) {
            attrMap.put(ch.getKey(), ch.getValue(String.class));
        }
    }

    public String getAddress() {
        return attrMap.get("address");
    }

    public Double getGeoPointA() {
        return Double.parseDouble(attrMap.get("geoPointA"));
    }

    public Double getGeoPointB() {
        return Double.parseDouble(attrMap.get("geoPointB"));
    }

    public String getYoutubeLink() {
        return attrMap.get("youtubeLink");
    }

    public Float getTaste() {
        return (Float.parseFloat(attrMap.get("taste_x")) +
                Float.parseFloat(attrMap.get("taste_k")) +
                Float.parseFloat(attrMap.get("taste_m"))) / 3;
    }

    public Float getFill() {
        return (Float.parseFloat(attrMap.get("fill_x")) +
                Float.parseFloat(attrMap.get("fill_k")) +
                Float.parseFloat(attrMap.get("fill_m"))) / 3;
    }

    public Float getStruct() {
        return (Float.parseFloat(attrMap.get("struct_x")) +
                Float.parseFloat(attrMap.get("struct_k")) +
                Float.parseFloat(attrMap.get("struct_m"))) / 3;
    }

    public Float getOrig() {
        return (Float.parseFloat(attrMap.get("orig_x")) +
                Float.parseFloat(attrMap.get("orig_k")) +
                Float.parseFloat(attrMap.get("orig_m"))) / 3;
    }

    public Float getIntPers() {
        return (Float.parseFloat(attrMap.get("int_x")) +
                Float.parseFloat(attrMap.get("int_k")) +
                Float.parseFloat(attrMap.get("int_m"))) / 3;
    }


    public String getScore() {
        return
                String.format("%.2f", (Float.parseFloat(attrMap.get("taste_x")) + Float.parseFloat(attrMap.get("taste_k")) + Float.parseFloat(attrMap.get("taste_m")) +
                        Float.parseFloat(attrMap.get("fill_x")) + Float.parseFloat(attrMap.get("fill_k")) + Float.parseFloat(attrMap.get("fill_m")) +
                        Float.parseFloat(attrMap.get("struct_x")) + Float.parseFloat(attrMap.get("struct_k")) + Float.parseFloat(attrMap.get("struct_m")) +
                        Float.parseFloat(attrMap.get("orig_x")) + Float.parseFloat(attrMap.get("orig_k")) + Float.parseFloat(attrMap.get("orig_m")) +
                        Float.parseFloat(attrMap.get("int_x")) + Float.parseFloat(attrMap.get("int_k")) + Float.parseFloat(attrMap.get("int_m")))/15);
    }
}
