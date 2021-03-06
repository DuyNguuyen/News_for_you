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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignUpActivity extends AppCompatActivity {

    private EditText edtName, edtEmail, edtPassword, edtRePassword;
    private TextView tvToSignIn;
    private Button btnSignUp;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sign_up);

        initUI();
        initListener();
    }

    private void initUI() {
        edtName = findViewById(R.id.edt_username);
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        edtRePassword = findViewById(R.id.edt_re_password);
        btnSignUp = findViewById(R.id.btn_sign_up);
        tvToSignIn = findViewById(R.id.tv_to_sign_in);
        progressDialog = new ProgressDialog(this);
    }

    private void initListener() {
        tvToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
        btnSignUp.setOnClickListener(view -> onClickSignUp());
    }

    private void onClickSignUp() {
        String name = edtName.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String rePassword = edtRePassword.getText().toString().trim();

        if (!networkChecking()) {
            Toast.makeText(SignUpActivity.this, "Vui l??ng ki???m tra l???i k???t n???i m???ng!", Toast.LENGTH_SHORT).show();

            return;
        }

        if(!CheckValid.isValidName(name)){
            Toast.makeText(SignUpActivity.this, "T??n ng?????i d??ng kh??ng ???????c ????? tr???ng",
                    Toast.LENGTH_SHORT).show();
        } else if(!CheckValid.isValidEmailAddress(email)){
            Toast.makeText(SignUpActivity.this, "?????a ch??? email kh??ng h???p l???.",
                    Toast.LENGTH_SHORT).show();
        } else if(!CheckValid.isValidPassword(password)){
            Toast.makeText(SignUpActivity.this, "M???t kh???u ph???i t???i thi???u 8 k?? t???.",
                    Toast.LENGTH_SHORT).show();
        } else if(!CheckValid.isMatchPassword(password, rePassword)){
            Toast.makeText(SignUpActivity.this, "M???t kh???u x??c nh???n kh??ng kh???p.",
                    Toast.LENGTH_SHORT).show();
        } else{
            FirebaseAuth auth = FirebaseAuth.getInstance();
            progressDialog.show();
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(name).build();
                                user.updateProfile(profileUpdates)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                //????ng k?? th??ng c??ng
                                                if (task.isSuccessful()) {
                                                    //G???i mail verify ?????n email ???? ????ng k??
                                                    user.sendEmailVerification()
                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task task) {
                                                                        Toast.makeText(SignUpActivity.this,
                                                                                "Vui l??ng x??c nh???n email " + user.getEmail() +
                                                                                " ????? b???o v??? t??i kho???n c???a b???n.",
                                                                                Toast.LENGTH_LONG).show();
                                                                }
                                                            });

                                                    Intent intent = new Intent(SignUpActivity.this, DashboardActivity.class);
                                                    startActivity(intent);
                                                    finishAffinity();
                                                } else {
                                                    Toast.makeText(SignUpActivity.this, "???? c?? l???i x???y ra",
                                                            Toast.LENGTH_SHORT).show();
                                                }
                                            }});
                            } else {
                                // ????ng k?? th???t b???i: tr??? k???t qu??? cho ng?????i d??ng
                                Toast.makeText(SignUpActivity.this, "Email ???? ???????c s??? d???ng",
                                        Toast.LENGTH_SHORT).show();
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