package ru.niceaska.converter;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;
import java.util.Locale;

public class ConvertValueFragment extends Fragment {

    private static final String VALUE = "ConvertValue";

    private ConverterHelper converterHelper;
    private EditText editTextFrom;
    private EditText editTextTo;
    boolean focusOnFrom = false;
    boolean focusOnTo = false;

    public ConvertValueFragment() {
        super(R.layout.convertion_fragment);
    }

    public static ConvertValueFragment newInstance(Value value) {
        Bundle args = new Bundle();
        args.putSerializable(VALUE, value);
        ConvertValueFragment fragment = new ConvertValueFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        initSpinners(v);
        return v;
    }
        private void initSpinners(View v) {
            editTextFrom = v.findViewById(R.id.convertFrom);
            editTextTo = v.findViewById(R.id.convertTo);
            Spinner spinnerFrom = v.findViewById(R.id.spinner_from);
            Spinner spinnerTo = v.findViewById(R.id.spinner_to);
            Value val = (Value) getArguments().getSerializable(VALUE);

            if (val == null) return;

            converterHelper = new ConverterHelper(requireContext(), val.units);
            final List<String> spinner_values = converterHelper.generateValuesArray();
            spinnerFrom.setAdapter(
                    new ArrayAdapter<String>(requireContext(),
                            android.R.layout.simple_list_item_1, spinner_values)
            );
            spinnerTo.setAdapter(
                    new ArrayAdapter<String>(requireContext(),
                            android.R.layout.simple_list_item_1, spinner_values)
            );

            spinnerFrom.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    converterHelper.setPositionFrom(position);
                    if (focusOnFrom) {
                        convertionSet(editTextFrom.getText().toString(), editTextTo, false);
                    } else if (focusOnTo) {
                        convertionSet(editTextFrom.getText().toString(), editTextFrom, true);

                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            spinnerTo.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    converterHelper.setPositionTo(position);
                    if (focusOnFrom) {
                        convertionSet(editTextFrom.getText().toString(), editTextTo, false);
                    } else if (focusOnTo) {
                        convertionSet(editTextFrom.getText().toString(), editTextFrom, true);

                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            editTextFrom.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    focusOnFrom = hasFocus;
                }
            });

            editTextTo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    focusOnTo = hasFocus;
                }
            });

            editTextTo.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (focusOnTo) {
                        convertionSet(s.toString(), editTextFrom, true);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            editTextFrom.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {


                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (focusOnFrom) {
                        convertionSet(s.toString(), editTextTo, false);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }

        private void convertionSet(String s, EditText editText, boolean reversePos) {
            try {
                double result = converterHelper.convert(Double.parseDouble(s), reversePos);
                editText.setText(String.format(Locale.US, "%.2f", result));
            } catch (NumberFormatException e) {
                editText.setText("");
            }
        }

}
