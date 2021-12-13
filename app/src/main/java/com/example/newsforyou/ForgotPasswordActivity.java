package com.example.newsforyou;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsforyou.Class.CheckValid;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText edtForgotPassEmail;
    private Button btnSendEmailReset, btnGoSignIn;
    private TextView tv1, tv2;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_forgot_password);

        initUI();
        initListener();
    }

    private  void initUI(){
        progressDialog = new ProgressDialog(this);
        edtForgotPassEmail = findViewById(R.id.edt_forgot_password_email);
        btnSendEmailReset = findViewById(R.id.btn_reset_password);
        btnGoSignIn = findViewById(R.id.btn_go_sign_in_1);
        tv1 = findViewById(R.id.textView);
        tv2 = findViewById(R.id.textView2);
    }

    private void initListener(){
        btnSendEmailReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSendEmailReset();
            }
        });

        btnGoSignIn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPasswordActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        }));
    }

    private void onClickSendEmailReset() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAddress = edtForgotPassEmail.getText().toString().trim();

        if (!networkChecking()) {
            Toast.makeText(ForgotPasswordActivity.this, "Vui lòng kiểm tra lại kết nối mạng!", Toast.LENGTH_SHORT).show();

            return;
        }

        if (!CheckValid.isValidEmailAddress(emailAddress)){
            tv1.setText("Địa chỉ email không hợp lệ!");
            tv1.setVisibility(View.VISIBLE);
        } else {
            progressDialog.show();
            auth.sendPasswordResetEmail(emailAddress)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                tv1.setVisibility(View.GONE);
                                tv2.setVisibility(View.VISIBLE);
                                btnGoSignIn.setVisibility(View.VISIBLE);
                                btnSendEmailReset.setText("Gửi lại Email");
                            } else {
                                tv1.setText("Email chưa được đăng ký!");
                                tv1.setVisibility(View.VISIBLE);
                                tv2.setVisibility(View.GONE);
                                btnGoSignIn.setVisibility(View.GONE);
                            }
                        }
                    });
        }
    }

    private boolean networkChecking() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}