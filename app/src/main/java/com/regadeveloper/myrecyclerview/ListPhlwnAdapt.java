package com.regadeveloper.myrecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ListPhlwnAdapt extends RecyclerView.Adapter<ListPhlwnAdapt.ListViewHolder> {
    private ArrayList<Phlwn> listPhlwn;

    public ListPhlwnAdapt(ArrayList<Phlwn> list){
        this.listPhlwn = list;
    }
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;

    }
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.subas, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        Phlwn phlwn = listPhlwn.get(position);

        Glide.with(holder.itemView.getContext())
                .load(phlwn.getPhoto())
                .apply(new RequestOptions().override(55,55))
                .into(holder.imgPhoto);

        holder.tvPhlwn.setText(phlwn.getPhlwn());
        holder.tvDesc.setText(phlwn.getDesc());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallback.onItemClicked(listPhlwn.get(holder.getAdapterPosition()));
            }
        });

    }

    
    public int getItemCount() {
        return listPhlwn.size();
    }

    public interface OnItemClickCallback {
        void onItemClicked(Phlwn data);
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvPhlwn, tvDesc;
        
        ListViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item);
            tvPhlwn = itemView.findViewById(R.id.itm_name);
            tvDesc = itemView.findViewById(R.id.itm_des);

        }
    }
}
