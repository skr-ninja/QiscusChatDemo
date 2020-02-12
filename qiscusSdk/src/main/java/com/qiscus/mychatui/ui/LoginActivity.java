package com.qiscus.mychatui.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.qiscus.mychatui.AppComponent;
import com.qiscus.mychatui.MyApp;
import com.qiscus.mychatui.MyApplication;
import com.qiscus.mychatui.R;
import com.qiscus.mychatui.presenter.LoginPresenter;

/**
 * Created on : January 30, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
public class LoginActivity extends AppCompatActivity implements LoginPresenter.View {
    private EditText displayName;
    private EditText userId;
    private EditText password;
    private LinearLayout loginButton;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userId = findViewById(R.id.et_user_id);
        password = findViewById(R.id.et_password);
        displayName = findViewById(R.id.et_display_name);
        loginButton = findViewById(R.id.login);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait!");

      /*  LoginPresenter loginPresenter = new LoginPresenter(this,
                MyApplication.getInstance().getComponent().getUserRepository());*/



        AppComponent appComponent=new AppComponent(this);
        LoginPresenter loginPresenter = new LoginPresenter(this,
                appComponent.getUserRepository());

        loginPresenter.start();

        loginButton.setOnClickListener(v -> {
            if (TextUtils.isEmpty(userId.getText().toString())) {
                userId.setError("Must not empty!");
            } else if (TextUtils.isEmpty(password.getText().toString())) {
                password.setError("Must not empty!");
            } else if (TextUtils.isEmpty(displayName.getText().toString())) {
                displayName.setError("Must not empty!");

            } else {
                loginPresenter.login(
                        userId.getText().toString(),
                        password.getText().toString(),
                        displayName.getText().toString()
                );
            }
        });
    }

    @Override
    public void showHomePage() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void dismissLoading() {
        progressDialog.dismiss();
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
