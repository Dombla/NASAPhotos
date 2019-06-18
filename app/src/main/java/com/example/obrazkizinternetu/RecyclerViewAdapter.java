package com.example.obrazkizinternetu;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> names = new ArrayList<>();
    private Context mContext;


    public RecyclerViewAdapter(Context mContext, ArrayList<String> names) {
        this.names = names;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.name.setText(names.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: "+names.get(position));
//                Toast.makeText(mContext, names.get(position),Toast.LENGTH_SHORT).show();
                if(names.get(position).equals("ISS Station"))
                {
                    Intent act = new Intent(mContext, ISSActivity.class);
                    act.putExtra("address", true);
                    mContext.startActivity(act);
                }
                else if(names.get(position).equals("Moon"))
                {
                    Intent act = new Intent(mContext, MoonActivity.class);
                    act.putExtra("address", true);
                    mContext.startActivity(act);
                }
                else if(names.get(position).equals("Sun"))
                {
                    Intent act = new Intent(mContext, SunActivity.class);
                    act.putExtra("address", true);
                    mContext.startActivity(act);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            parentLayout=itemView.findViewById(R.id.parent_layout);
        }
    }
}
