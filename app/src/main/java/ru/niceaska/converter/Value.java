package ru.niceaska.converter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Value {
    AREA(Collections.<Unit>emptyList()),
    COOKING(Collections.<Unit>emptyList()),
    CURRBCY(Collections.<Unit>emptyList()),
    STORAGE(Collections.<Unit>emptyList()),
    ENERGY(Collections.<Unit>emptyList()),
    FUEL(Collections.<Unit>emptyList()),
    LENGTH(Arrays.asList(Unit.MILE, Unit.KILOMETR, Unit.METRE)),
    MASS(Collections.<Unit>emptyList()),
    POWER(Collections.<Unit>emptyList()),
    PRRESSURE(Collections.<Unit>emptyList()),
    SPEED(Collections.<Unit>emptyList()),
    TEMPERTURE(Collections.<Unit>emptyList()),
    TIME(Collections.<Unit>emptyList()),
    VOLUME(Collections.<Unit>emptyList());

     List<Unit> units;

    private static final Value[] unitArray = Value.values();

    Value(List<Unit> units) {
        this.units = units;
    }

    public static Value getValue(int position) {
        return unitArray[position];
    }


}
