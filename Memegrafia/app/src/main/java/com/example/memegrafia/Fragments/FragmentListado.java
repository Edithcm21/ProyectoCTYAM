package com.example.memegrafia.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memegrafia.Interfaces.OnImageSelected;
import com.example.memegrafia.R;

import java.util.Arrays;
import java.util.List;

public class FragmentListado extends Fragment {
    private OnImageSelected listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listado,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //metodo get Activity regresa la referencia a la actividad que hospeda al fragmento
        FragmentActivity activity = getActivity();
        if (activity == null) return;


        String [] imagesArray=activity.getResources().getStringArray(R.array.images);
        List<String> images= Arrays.asList(imagesArray);

        RecyclerView recyclerView=activity.findViewById(R.id.myList);
        if(recyclerView==null)return ;

        recyclerView.setLayoutManager (new GridLayoutManager(activity,2));

        // se crea un objeto MyAdapter al que se pasan los parámetros necesarios
        // context: la actividad que hospeda al fragmento
        // List<Images>: la lista con la información al mostrar
        // OnImageSelected: el objeto listener mediante el cuál se invocará al manejador del evento imageSelected para los elementos de la lista
        recyclerView.setAdapter (new MyAdapter (activity,images, this.listener));
    }

    /**
     *  Asigna al manejador del elemento seleccionad en la lista
     * @param listener Objeto que implementa la interfaz OnPlanetSelected
     */
    public void setOnImageSelectedListener(OnImageSelected listener) {
        this.listener = listener;
    }
}



class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private List<String> images;
    private  OnImageSelected listener;

    MyAdapter (Context context, List<String> images, OnImageSelected listener) {
        this.context = context;
        this.images= images;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from (context);
        View view = inflater.inflate (android.R.layout.simple_list_item_1, parent, false);
        return new MyViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        // se asigna la información en cada fila de la lista y se establece el manejador del evento OnClickListener,
        // de modo que al seleccionarse se invoque al método imageSelected en el objeto que manejará dicho evento
        holder.setData (images.get (position));
        holder.itemView.setOnClickListener (view -> listener.imageSelected (position));
    }

    @Override
    public int getItemCount() {
        return images.size ();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView text1;

        MyViewHolder(@NonNull View itemView) {
            super (itemView);
            text1 = itemView.findViewById (android.R.id.text1);
        }

        void setData (String data) {
            text1.setText (data);
        }

    }
}
