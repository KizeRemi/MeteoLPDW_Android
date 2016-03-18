package kize.meteolpdw;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by mavillaz on 15/03/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class Item {
    public String main;
    public String description;
    public Integer temp;
    public Integer pressure;
    public Integer humidity;
    public Integer tempMin;
    public Integer tempMax;
    public Integer speed;
    public Integer deg;
    public Integer all;
    public Integer rain;
    public String name;
    public String pays;

    public Item(){}

    public Item(String name, String pays){
        this.name = name;
        this.pays = pays;
    }
}