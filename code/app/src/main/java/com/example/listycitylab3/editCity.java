package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class editCity extends DialogFragment {
    interface EditCityDialogListener {
        void editCity(City city, City oldcity);
    }


    private editCity.EditCityDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof editCity.EditCityDialogListener){
            listener = (editCity.EditCityDialogListener) context;
        }
        else{
            throw new RuntimeException(context + "must implement AddCityDialogListener");
        }
    }

        //Hint 3
    static editCity newInstance(City city){
        Bundle args = new Bundle();
        args.putSerializable("city", city);
        editCity fragment = new editCity();
        fragment.setArguments(args);
        return fragment;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_city, null);
        Bundle bund = getArguments();
        City city = (City) bund.getSerializable("city");
        City oldcity = city;
        EditText editCityName = view.findViewById(R.id.edit_text_city_text);
        EditText editProvinceName = view.findViewById(R.id.edit_text_province_text);
        editCityName.setText(city.getName());
        editProvinceName.setText(city.getProvince());
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Add/Edit A City")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Add", (dialog, which) -> {
                    // Code that happens when we run the pop up.

                    String cityName = editCityName.getText().toString();
                    String provinceName = editProvinceName.getText().toString();
                    listener.editCity(new City(cityName, provinceName), oldcity);

                })
                .create();
    }
}
