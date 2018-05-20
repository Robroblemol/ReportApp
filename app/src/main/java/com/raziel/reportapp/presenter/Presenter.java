package com.raziel.reportapp.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.raziel.reportapp.ReportViewModel;
import com.raziel.reportapp.models.IMainActivity;
import com.raziel.reportapp.models.IPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Presenter implements IPresenter {
    private IMainActivity v;
    private FirebaseFirestore db;
    public List<ReportViewModel> lst;

    public Presenter(IMainActivity v){
        this.v=v;
        this.db = FirebaseFirestore.getInstance();
        this.lst = new ArrayList<>();
    }


    @Override
    public void addReport(String device, String description, Boolean state) {
        String d = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String s = null;
        if(!state) {
            s = "Novedad";
        } else
            s = "OK";

        Map<String,Object> dataTask = new HashMap<>();
        dataTask.put("device",device);
        dataTask.put("description",description);
        dataTask.put("state",s);
        dataTask.put("date",d);
        db.collection("report")
                .add(dataTask)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Presenter","Added Report ID: "+documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Presenter","error adding report",e);
                    }
                });

    }

    @Override
    public void initLstReport() {
        v.lodingData();
        db.collection("report")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (DocumentSnapshot document:task.getResult()){
                                Log.d("Presenter", document.getId() + " => " + document.getData());
                                ReportViewModel t = document.toObject(ReportViewModel.class);
                                lst.add(t);
                                v.addReport(t);
                            }
                            Log.d("Presenter","lst = "+lst.size());
                            v.showReports(lst);
                        }
                        else{
                            Log.w("Presenter", "Error getting documents.", task.getException());
                        }
                    }
                });

    }

    @Override
    public void addLst(ReportViewModel t) {
        this.lst.add(t);
    }

    @Override
    public List<ReportViewModel> getLst() {
        return this.lst;
    }
}
