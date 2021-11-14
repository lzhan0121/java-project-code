package com.codeant.team.domain;

public class Printer implements Equipment {
    private String model;
    private String type;

    public Printer() {
        super();
    }

    public Printer(String model, String type) {
        super();
        this.model = model;
        this.type = type;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getDescription() {
        return model + "(" + type + ")";
    }
}
