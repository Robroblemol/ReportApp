package com.raziel.reportapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<ReportViewHolder> {
    private List<ReportViewModel> viewModels;
    RecyclerViewAdapter(){
        viewModels = new ArrayList<ReportViewModel>();
    }
    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_report,parent,false);
        return new ReportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportViewHolder holder, int position) {
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
}
