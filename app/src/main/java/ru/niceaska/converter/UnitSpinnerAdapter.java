package ru.niceaska.converter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class UnitSpinnerAdapter extends ArrayAdapter<String> {

    List<String> units;

    UnitSpinnerAdapter(Context context, List <String> units) {
        super(context, android.R.layout.simple_list_item_1, units);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(android.R.layout.simple_list_item_1, parent, false);
            UnitSpinnerAdapter.ViewHolder viewHolder = new UnitSpinnerAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        UnitSpinnerAdapter.ViewHolder holder = (UnitSpinnerAdapter.ViewHolder) convertView.getTag();
        holder.mSpinner.setText(getItem(position));
        return convertView;
    }

    private class ViewHolder {
        private final TextView mSpinner;
        ViewHolder(View v) {
            mSpinner = v.findViewById(android.R.id.text1);
        }
    }
}
