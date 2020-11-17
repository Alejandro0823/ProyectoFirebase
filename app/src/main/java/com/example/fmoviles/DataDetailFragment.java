package com.example.fmoviles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.fmoviles.models.MovileModel;

public class DataDetailFragment extends Fragment {

    private static String marca,descripcion;
    private boolean active;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data_detail, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tv_data_detail_marca, tv_data_detail_descripcion;

        tv_data_detail_marca = view.findViewById(R.id.tv_data_detail_marca);
        tv_data_detail_descripcion = view.findViewById(R.id.tv_data_detail_descripcion);

        tv_data_detail_marca.setText(marca);
        tv_data_detail_descripcion.setText(descripcion);

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(DataDetailFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    static void getData(Bundle bundle){
        MovileModel model = (MovileModel) bundle.getSerializable("model");
        if(model != null){
            marca = model.getMarca();
            descripcion = model.getDescripcion();
        }
    }
}