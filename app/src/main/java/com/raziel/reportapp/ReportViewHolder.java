package com.raziel.reportapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReportViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.lblDevice)
    TextView lblDevice;
    @BindView(R.id.lblDate)
    TextView lblDate;
    @BindView(R.id.lblState)
    TextView lblState;

    public ReportViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
    public void render(ReportViewModel reportViewModel){
        lblDevice.setText(reportViewModel.getDevice());
        lblDate.setText(reportViewModel.getDate());
        lblState.setText(reportViewModel.getDescription());
    }
}
