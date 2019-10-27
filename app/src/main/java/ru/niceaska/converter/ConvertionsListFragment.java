package ru.niceaska.converter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

public class ConvertionsListFragment extends Fragment {

    public ConvertionsListFragment() {
        super(R.layout.convertion_list);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  super.onCreateView(inflater, container, savedInstanceState);
        initRecycleView(v);
        return v;
    }

    private void initRecycleView(View v) {
        RecyclerView recyclerView = v.findViewById(R.id.value);
        LinearLayoutManager manager = new LinearLayoutManager(requireContext(),
                                    RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        ConverterValuesAdapter converterValuesAdapter = new ConverterValuesAdapter(
                Arrays.asList(getResources().getStringArray(R.array.units)),
                new IMyOnClickMethod() {
            @Override
            public void onItemClick(Value item) {
                if (getActivity() instanceof IConvertValueHolder) {
                    ((IConvertValueHolder) getActivity()).convertValue(item);
                }
            }
        });
        DividerItemDecoration deviderItemDecoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(deviderItemDecoration);
        recyclerView.setAdapter(converterValuesAdapter);

    }
}
