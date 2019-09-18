package com.regadeveloper.myrecyclerview;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class CardViewPhlwnAdapt extends RecyclerView.Adapter<CardViewPhlwnAdapt.CardViewHolder> {
    private ArrayList<Phlwn> listPhlwn;

    public CardViewPhlwnAdapt(ArrayList<Phlwn> list) {
        this.listPhlwn = list;
    }
    private String card = "Card View";

    @NonNull
    public CardViewHolder onCreateViewHolder  (@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_hero, parent, false);
        return new CardViewHolder(view);
    }

    public void onBindViewHolder(@NonNull final CardViewHolder holder, int position) {

        Phlwn phlwn = listPhlwn.get(position);

        Glide.with(holder.itemView.getContext())
                .load(phlwn.getPhoto())
                .apply(new RequestOptions().override(350,550))
                .into(holder.imgPhoto);

        holder.tvPhlwn.setText(phlwn.getPhlwn());
        holder.tvDesc.setText(phlwn.getDesc());

        holder.btnFav.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), "Favorite "+
                        listPhlwn.get(holder.getAdapterPosition()).getPhlwn(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Toast.makeText(holder.itemView.getContext(), "Share "+
                        listPhlwn.get(holder.getAdapterPosition()).getPhlwn(), Toast.LENGTH_SHORT).show();
        } {

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(holder.itemView.getContext(), "Kamu mamilih "+ listPhlwn.get(holder.getAdapterPosition()).getPhlwn(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return listPhlwn.size();
    }

    class CardViewHolder extends RecyclerView.ViewHolder  {
        ImageView imgPhoto;
        TextView tvPhlwn, tvDesc;
        Button btnFav, btnShare;

        CardViewHolder(View itemView){
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item);
            tvPhlwn = itemView.findViewById(R.id.itm_name);
            tvDesc = itemView.findViewById(R.id.itm_des);
            btnFav =  itemView.findViewById(R.id.btn_fav);
            btnShare = itemView.findViewById(R.id.btn_share);
        }
    }
}
