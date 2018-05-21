package com.raziel.reportapp.models;

import com.raziel.reportapp.ReportViewModel;

import java.util.List;

public interface IMainActivity {
    void addReport();

    void addReport(ReportViewModel t);

    void showReports(List<ReportViewModel> taskViewModelList);

    void lodingData();
}
