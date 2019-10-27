package ru.niceaska.converter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class ConverterHelper {

    private List <Unit> units;
    private Context context;
    private int positionFrom;
    private int positionTo;

    public void setPositionFrom(int positionFrom) {
        this.positionFrom = positionFrom;
    }

    public void setPositionTo(int positionTo) {
        this.positionTo = positionTo;
    }

    ConverterHelper(Context context, List<Unit> units) {
        this.context = context;
        this.units = units;
    }

    List<String> generateValuesArray() {
        List<String> vals = new ArrayList<>();
        for (Unit u : units) {
            vals.add(context.getResources()
                    .getString(u.getId()));
        }
        return vals;
    }

    double convert(double valFrom, boolean reversePosition) {
        int posFrom = (reversePosition) ? positionTo : positionFrom;
        int posTo = (reversePosition) ? positionFrom : positionTo;
        double convToBase = valFrom * units.get(posFrom)
                                            .getConvertionFrom();
        return convToBase * units.get(posTo).getConvertT0();
    }

}
