package com.raziel.reportapp.models;

import com.raziel.reportapp.ReportViewModel;

import java.util.List;

public interface IPresenter {
    void addReport(String device, String description);
    void initLstReport();
    void addLst(ReportViewModel t);
    List<ReportViewModel> getLst();
}
