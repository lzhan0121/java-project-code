package com.codeant.team.domain;

public class PC implements Equipment {
    private String model; //型号
    private String display; //显示器

    public PC() {
        super();
    }

    public PC(String model, String display) {
        super();
        this.model = model;
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }

    public String getModel() {
        return model;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String getDescription() {
        return model + "(" + display + ")";
    }
}
