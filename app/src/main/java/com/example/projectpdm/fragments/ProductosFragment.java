package com.example.calcuquesito.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.calcuquesito.R;
import com.example.calcuquesito.activities.QuesilloActivity;
import com.example.calcuquesito.activities.QuesoBolaActivity;
import com.example.calcuquesito.activities.QuesoChihuahuaActivity;
import com.example.calcuquesito.activities.QuesoFrescoActivity;
import com.example.calcuquesito.activities.QuesoManchegoActivity;
import com.example.calcuquesito.activities.QuesoPanelaActivity;
import com.example.calcuquesito.activities.YoghurtActivity;

public class ProductosFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_productos, container, false);

        Button btnFresco = view.findViewById(R.id.btnQuesoFresco);
        btnFresco.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), QuesoFrescoActivity.class))
        );

        Button btnPanela = view.findViewById(R.id.btnQuesoPanela);
        btnPanela.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), QuesoPanelaActivity.class))
        );

        Button btnQuesillo = view.findViewById(R.id.btnQuesillo);
        btnQuesillo.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), QuesilloActivity.class))
        );

        Button btnManchego = view.findViewById(R.id.btnQuesoManchego);
        btnManchego.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), QuesoManchegoActivity.class))
        );

        Button btnChihuahua = view.findViewById(R.id.btnQuesoChihuahua);
        btnChihuahua.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), QuesoChihuahuaActivity.class))
        );

        Button btnBola = view.findViewById(R.id.btnQuesoBola);
        btnBola.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), QuesoBolaActivity.class))
        );

        Button btnYogurt = view.findViewById(R.id.btnQuesoYoghurt);
        btnYogurt.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), YoghurtActivity.class))
        );

        return view;
    }
}
