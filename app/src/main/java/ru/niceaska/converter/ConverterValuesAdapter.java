package ru.niceaska.converter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ConverterValuesAdapter extends RecyclerView.Adapter<ConverterValuesAdapter.ConvertValuesHolder> {

    private List<String> units;
    private final IMyOnClickMethod listener;

    public ConverterValuesAdapter(List<String> units, IMyOnClickMethod listener) {
        this.units = units;
        this.listener = listener;
    }


    @Override
    public int getItemCount() {
        return units == null ? 0 : units.size();
    }



    @NonNull
    @Override
    public ConverterValuesAdapter.ConvertValuesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.convert_item, parent, false);
        return new ConvertValuesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConvertValuesHolder holder, int position) {
        holder.bindOnClick(units.get(position), Value.getValue(position), listener);
    }

    static class ConvertValuesHolder extends  RecyclerView.ViewHolder {

        private final TextView value;

        ConvertValuesHolder(@NonNull View itemView) {
            super(itemView);
            value = itemView.findViewById(R.id.convert_item);
        }

        void bindOnClick(final String name, final Value item, final IMyOnClickMethod listener) {

            value.setText(name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
