package com.raziel.reportapp.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.raziel.reportapp.models.IEmailPasswordActivity;
import com.raziel.reportapp.models.ILoginPresenter;


import java.util.concurrent.Executor;

public class LoginPresenter implements ILoginPresenter {

    private FirebaseAuth mAuth;
    private  String TAG = "LoginPresenter";
    private IEmailPasswordActivity v;

    public LoginPresenter(IEmailPasswordActivity v){
        this.v = v;
        mAuth = FirebaseAuth.getInstance();
    }
    @Override
    public void signIn(String email, String pass) {
        Log.d(TAG,"signin: "+email);

        mAuth.signInWithEmailAndPassword(email,pass).
                addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        Log.d(TAG,"signInWithEmail:success");
                        else{
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                        }

                    }
                });
    }
}
