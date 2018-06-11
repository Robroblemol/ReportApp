package com.raziel.reportapp.presenter;

import android.util.Log;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.raziel.reportapp.models.IRViewDetailPresenter;
import com.raziel.reportapp.models.IReportViewDetail;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RViewDetailPresenter implements IRViewDetailPresenter{
    private FirebaseFirestore db;
    private IReportViewDetail v ;
    public RViewDetailPresenter(IReportViewDetail v){
        v = v;
        this.db = FirebaseFirestore.getInstance();
    }
    @Override
    public void addFix(String f,String id) {

        Log.d("fixText","fix: "+f+" id: "+id);

        String d = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss").format(new Date());
        CollectionReference reports = db.collection("report");

        Map<String, Object> fix = new HashMap<>();
        fix.put("fix",f);
        reports.document(id).set(fix, SetOptions.merge());
        //db.collection("report").document(id).set(fix, SetOptions.merge());
    }
}
