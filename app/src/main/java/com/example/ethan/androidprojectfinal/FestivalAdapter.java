package com.example.ethan.androidprojectfinal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class FestivalAdapter extends RecyclerView.Adapter<FestivalAdapter.FestivalViewHolder> {

    class FestivalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView festivalItemView;
        private final FestivalClickListener festivalClickListener;

        private FestivalViewHolder(View itemView, FestivalClickListener festivalClickListener) {
            super(itemView);
            this.festivalClickListener = festivalClickListener;
            itemView.setOnClickListener(this);
            festivalItemView = itemView.findViewById(R.id.textView);
        }


        @Override
        public void onClick(View v) {
            festivalClickListener.festivalOnClick(getAdapterPosition());
        }
    }

    private final LayoutInflater mInflater;
    private List<Festival> mFestivals; // Cached copy of words
    private final FestivalClickListener festivalClickListener;

    public Festival getFestival(int position) {
       return mFestivals.get(position);
    }

    FestivalAdapter(Context context, FestivalClickListener festivalClickListener) {
        mInflater = LayoutInflater.from(context);
        this.festivalClickListener = festivalClickListener;
    }


    interface FestivalClickListener {
        void festivalOnClick(int i);
    }

    @Override
    public FestivalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new FestivalViewHolder(itemView, festivalClickListener);
    }

    @Override
    public void onBindViewHolder(FestivalViewHolder holder, int position) {
        if (mFestivals != null) {
            Festival current = mFestivals.get(position);
            holder.festivalItemView.setText(current.getFestivalName());
        } else {
            // Covers the case of data not being ready yet.
            holder.festivalItemView.setText("No Festival");
        }
    }

    void setmFestivals(List<Festival> festivals){
        mFestivals = festivals;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mFestivals has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mFestivals != null)
            return mFestivals.size();
        else return 0;
    }
}
