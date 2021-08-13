package com.nguyenhoa.diginew.model;

import java.io.Serializable;

public class Province implements Serializable {
    String name;

    public Province(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
