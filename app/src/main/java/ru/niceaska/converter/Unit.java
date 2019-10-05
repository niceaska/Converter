package ru.niceaska.converter;

public enum Unit {

    MILE(0, 1609.34, 0.0006),
    KILOMETR(1, 1000.0, 0.001),
    METRE(2,  1.0, 1.0),
    CENTIMETR(3, 0.01, 100.0),
    MILIIMETR(3, 0.010, 1000.0),
    MICROMETR(4, 00001, 100000.0);

    private int id;
    private double convertionFrom;
    private double convertT0;

    Unit(int id, double convertFrom, double convertTo) {
        this.id = id;
        this.convertionFrom = convertFrom;
        this.convertT0 = convertTo;
    }
}
