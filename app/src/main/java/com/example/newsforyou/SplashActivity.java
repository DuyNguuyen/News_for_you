package com.example.newsforyou;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isConnected()){
                    nextActivity();
                } else {
                    noInternetDialogCall();
                }

            }
        }, 2000);
    }

    //Xác định activity tiếp theo
    private void nextActivity() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //Kiểm tra người dùng đã từng đăng nhập chưa
        if(user == null){
            // Chưa đăng nhập: chuyển sang SignInActivity
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
        }else
        {
            // Ngược lại: chuyển sang DashboardActivity
            Intent intent = new Intent(this, DashboardActivity.class);
            startActivity(intent);
        }
    }

    // Kiểm tra kết nối internet
    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }

    public void noInternetDialogCall(){
        // khởi tạo dialog
        Dialog dialog = new Dialog(SplashActivity.this);
        // gán content view
        dialog.setContentView(R.layout.no_internet_dialog);
        // chặn nhấn bên ngoài dialog
        dialog.setCanceledOnTouchOutside(false);
        // điều chỉnh chiều dài và chiều cao dialog
        dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        // transparent background
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // cài đặt animation
        dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;
        // Khai báo biến trong dialog
        Button btnTryAgain = dialog.findViewById(R.id.btn_try_again);
        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });
    }
}