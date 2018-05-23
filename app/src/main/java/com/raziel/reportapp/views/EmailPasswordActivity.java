package com.raziel.reportapp.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.raziel.reportapp.R;
import com.raziel.reportapp.models.IEmailPasswordActivity;
import com.raziel.reportapp.models.ILoginPresenter;
import com.raziel.reportapp.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EmailPasswordActivity extends AppCompatActivity implements IEmailPasswordActivity {
    @BindView(R.id.etEmail)
    TextView etEmail;
    @BindView(R.id.etPassword)
    TextView etPassword;

    private ILoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_pass_activity);
        ButterKnife.bind(this);

        presenter = new LoginPresenter(this);

    }
   /* @Override
    public void onStart(){
        super.onStart();
        //Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        //updateUI(currentUser);
        setTitle(R.string.login);

    }*/
    @OnClick(R.id.btnLogin)
    @Override
    public void signIn() {
        presenter.signIn(etEmail.getText().toString(),etPassword.getText().toString())                          ;

    }

    @Override
    public void logInOK(Boolean flag) {
        if (flag)
            Toast.makeText(this,"usuario registrado",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"usuario no registrado",Toast.LENGTH_SHORT).show();
    }
}
