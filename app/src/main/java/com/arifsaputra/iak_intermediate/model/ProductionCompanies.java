package com.arifsaputra.iak_intermediate.model;

/**
 * Created by Chyrus on 8/12/17.
 * Muh Arif Saputra (Android Developer)
 */

public class ProductionCompanies {

    private int id;
    private String name;

    public ProductionCompanies(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
