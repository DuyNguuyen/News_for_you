package com.example.newsforyou.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.newsforyou.DashboardActivity;
import com.example.newsforyou.R;
import com.example.newsforyou.UserProfileActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment {
    private View mView;
    ImageView ivAvatar, ivSearch;
    TextView tvWelcome;
    ImageButton ibMenu;
    EditText edtSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);

        initView();
        setUserInformation();
        initListener();

        return  mView;
    }

    public void initView() {
        ivAvatar = mView.findViewById(R.id.profile_image);
        tvWelcome = mView.findViewById(R.id.tv_welcome);
        ibMenu = mView.findViewById(R.id.ib_menu_hamburger);
        edtSearch = mView.findViewById(R.id.edt_search);
        ivSearch = mView.findViewById(R.id.iv_search);
    }

    private void initListener() {
        ivAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserProfileActivity.class);
                startActivity(intent);
            }
        });

        tvWelcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setUserInformation() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            return;
        }

        tvWelcome.setText("Xin chào " + user.getDisplayName());
        Glide.with(getActivity()).load(user.getPhotoUrl())
                .error(R.drawable.default_avatar)
                .into(ivAvatar);
    }
}
