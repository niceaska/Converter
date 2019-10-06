package ru.niceaska.converter;

import java.util.List;

public enum Unit {

    MILE(R.string.mile, 1609.34, 0.0006),
    KILOMETR(R.string.kilometr, 1000.0, 0.001),
    METRE(R.string.metr,  1.0, 1.0),
    CENTIMETR(R.string.santimeter, 0.01, 100.0),
    MILIIMETR(R.string.milimetr, 0.010, 1000.0),
    MICROMETR(R.string.mikrometr, 00001, 100000.0);

    private int id;
    private double convertionFrom;
    private double convertT0;

    Unit(int id, double convertFrom, double convertTo) {
        this.id = id;
        this.convertionFrom = convertFrom;
        this.convertT0 = convertTo;
    }

    public int getId() {
        return id;
    }

    public double getConvertionFrom() {
        return convertionFrom;
    }

    public double getConvertT0() {
        return convertT0;
    }
}
