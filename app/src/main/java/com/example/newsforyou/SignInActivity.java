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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private TextView tvToSignUp, tvToForgetPass;
    private EditText edtSignInEmail, edtSignInPassword;
    private Button btnSignIn;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sign_in);

        initUI();
        initListener();
    }

    private void initUI() {
        progressDialog = new ProgressDialog(this);
        tvToSignUp= findViewById(R.id.tv_to_sign_up);
        edtSignInEmail = findViewById(R.id.edt_sign_in_email);
        edtSignInPassword = findViewById(R.id.edt_sign_in_password);
        btnSignIn = findViewById(R.id.btn_sign_in);
        tvToForgetPass = findViewById(R.id.tv_to_forgot_password);
    }


    private void initListener() {
        tvToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        tvToForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( SignInActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSignIn();
            }
        });
    }

    private void onClickSignIn() {
        String email = edtSignInEmail.getText().toString().trim();
        String password = edtSignInPassword.getText().toString().trim();
        FirebaseAuth auth = FirebaseAuth.getInstance();

        if (!networkChecking()) {
            Toast.makeText(SignInActivity.this, "Vui lòng kiểm tra lại kết nối mạng!", Toast.LENGTH_SHORT).show();

            return;
        }

        if(!CheckValid.isValidEmailAddress(email)){
            Toast.makeText(SignInActivity.this, "Địa chỉ email không hợp lệ.",
                    Toast.LENGTH_SHORT).show();
        } else if(!CheckValid.isValidPassword(password)){
            Toast.makeText(SignInActivity.this, "Mật khẩu phải tối thiếu 6 ký tự.",
                    Toast.LENGTH_SHORT).show();
        } else {
            progressDialog.show();
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Intent intent = new Intent(SignInActivity.this, DashboardActivity.class);
                                startActivity(intent);
                                finishAffinity();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(SignInActivity.this, "Đăng nhập thất bại, tên đăng nhập hoặc mật khẩu không đúng",
                                        Toast.LENGTH_LONG).show();
                                edtSignInPassword.setText("");
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