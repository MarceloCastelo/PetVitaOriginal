package com.example.petvitaoriginal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<DataClass> dataList;

    public MyAdapter(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(dataList.get(position).getDataImage()).into(holder.recImage);
        holder.recName.setText(dataList.get(position).getDataPetName());
        holder.recType.setText(dataList.get(position).getDataPetType());
        holder.recGender.setText(dataList.get(position).getDataPetGender());

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("image_url", dataList.get(holder.getAdapterPosition()).getDataImage());
                intent.putExtra("tipo_do_animal", dataList.get(holder.getAdapterPosition()).getDataPetType());
                intent.putExtra("nome_do_animal", dataList.get(holder.getAdapterPosition()).getDataPetName());
                intent.putExtra("sexo_do_animal", dataList.get(holder.getAdapterPosition()).getDataPetGender());
                intent.putExtra("Key", dataList.get(holder.getAdapterPosition()).getKey()); // Se necessário
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public void searchDataList(ArrayList<DataClass> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{

    ImageView recImage;
    TextView recName, recGender, recType;
    CardView recCard;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        recImage = itemView.findViewById(R.id.recImage);
        recName = itemView.findViewById(R.id.recName);
        recGender = itemView.findViewById(R.id.recGender);
        recType = itemView.findViewById(R.id.recType);
        recCard = itemView.findViewById(R.id.recCard);
    }
}