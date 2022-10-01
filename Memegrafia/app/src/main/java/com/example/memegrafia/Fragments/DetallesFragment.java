package com.example.memegrafia.Fragments;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.example.memegrafia.R;

public class DetallesFragment extends Fragment {
    private int position;

    public DetallesFragment () {
        super ();
    }

    public DetallesFragment(int position){
        super();
        this.position=position;
    }

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate (R.layout.fragment_detalles, container, false);
    }

    @Override
    public void onActivityCreated (@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FragmentActivity activity = getActivity ();
        if (activity == null) return;


        // la información mostrada depende de los arrays almacenados en los recursos
        // y se obtiene mediante el parámetros position recibido en el constructor
        String [] detallesArray = getResources ().getStringArray (R.array.names);
        String [] imagenesArray = getResources ().getStringArray (R.array.names);

        TextView tvDetalles = activity.findViewById (R.id.tvDetalle);
        if (tvDetalles != null) tvDetalles.setText (detallesArray [position]);

        TextView tvTitle = activity.findViewById (R.id.tvTitle);
        if (tvTitle != null) tvTitle.setText (imagenesArray [position]);
    }



}
