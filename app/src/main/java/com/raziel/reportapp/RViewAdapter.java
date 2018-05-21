package com.raziel.reportapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RViewAdapter extends RecyclerView.Adapter<RViewAdapter.ViewHolder>{

    private static  ClickListener clickListener;
    private List<ReportViewModel> viewModels;

    public RViewAdapter(){
        viewModels = new ArrayList<ReportViewModel>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_report,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ReportViewModel reportViewModel = viewModels.get(position);
        holder.render(reportViewModel);
    }

    @Override
    public int getItemCount() {
        return viewModels.size();
    }

    public void addReport(ReportViewModel reportViewModel){
        viewModels.add(reportViewModel);
    }
    public void addLstReport(List<ReportViewModel> viewModels){
        viewModels.clear();
        viewModels.addAll(viewModels);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.lblDevice)
        TextView lblDevice;
        @BindView(R.id.lblDate)
        TextView lblDate;
        @BindView(R.id.lblState)
        TextView lblState;

        public ViewHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(),v);
        }
        public void render(ReportViewModel reportViewModel){
            lblDevice.setText(reportViewModel.getDevice());
            lblDate.setText(reportViewModel.getDate());
            lblState.setText(reportViewModel.getState());
        }
    }
    public void setOnItemClickListener(ClickListener clickListener){
        RViewAdapter.clickListener = clickListener;
    }
    public interface ClickListener{
        void onItemClick(int position, View v);
    }

}
