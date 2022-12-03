package com.project.quizapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.project.quizapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements IHomeData, ILevelData, IBackHome, IResultSending {
    ActivityMainBinding binding;
    FragmentHome fragmentHome = new FragmentHome();
    FragmentLevel fragmentLevel = new FragmentLevel();
    FragmentManager fragmentManager;
    FragmentQuestion fragmentQuestion = new FragmentQuestion();
    FragmentResult fragmentResult = new FragmentResult();

    //Navigation bar
    @SuppressLint("NonConstantResourceId")
    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigationBar_play:
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, fragmentHome, null);
                fragmentTransaction.commit();
                return true;
            case R.id.gameplay_history:
                FragmentManager fragmentManager1 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                fragmentTransaction1.replace(R.id.frameLayout, new FragmentHistoryPlay(), null);
                fragmentTransaction1.commit();
                return true;
        }
        return false;
    };

    //Toolbar - dau 3 cham
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_bar, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.toolbar_gop_y:
                Uri uriEmail = Uri.parse("mailto:20021536@vnu.edu.vn" +
                        "?subject=" + Uri.encode("UET Number one") +
                        "&body=" + Uri.encode("DUONG QUOC HUY + NGUYEN NHU TINH \n\n"));
                Intent intentEmail = new Intent(Intent.ACTION_SENDTO);
                intentEmail.setData(uriEmail);
                startActivity(intentEmail);
                return true;
            case R.id.toolbar_game_info:
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, new FragmentInformation(), null);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                return true;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fragmentManager = getSupportFragmentManager();
        // Xác định xem màn nào sẽ hiển thị trước ở trong app
        // Khi mở app , frameLayout của MainActivity sẽ đc mở lên trước, , thông qua tool context thì MainActivity sẽ được liên kết với Fragment Home layout
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frameLayout, fragmentHome, null);
        fragmentTransaction.commit();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    // Pass data mon hoc , chuyen sang fragment Level
    @Override
    public void send(String monhoc) {
        Bundle bundle = new Bundle();
        bundle.putString("monhoc", monhoc);
        fragmentLevel.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragmentLevel, null);
        fragmentTransaction.commit();
    }

    // Back ve man Home
    @Override
    public void sendHome(String s) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragmentHome, null);
        fragmentTransaction.commit();
    }

    // Pass data level va monhoc , chuyen sang fragmentQuestion
    @Override
    public void sendLevelData(String level, String monhoc) {
        Bundle bundle = new Bundle();
        bundle.putString("mess", level);
        bundle.putString("monhoc", monhoc);

        fragmentQuestion.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragmentQuestion, null);
        fragmentTransaction.commit();

    }

    // Pass data so cau dung, mon hoc , level , cau hoi , chuyen sang fragmentResult
    @Override
    public void send(String result, String question, String monhoc, String level) {
        Bundle bundle = new Bundle();
        bundle.putString("result", result);
        bundle.putString("question", question);
        bundle.putString("monhoc", monhoc);
        bundle.putString("level", level);

        fragmentResult.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragmentResult, null);
        fragmentTransaction.commit();
    }
}