package com.project.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements  IHomeData , ILevelData ,IBackHome ,IResultSending {
    FragmentHome fragmentHome = new FragmentHome();

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
//                fragmentTransaction1.replace(R.id.frameLayout, new FragmentHistory(), null);
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
        setContentView(R.layout.activity_main);
    }


    @Override
    public void send(String monhoc) {

    }

    @Override
    public void sendHome(String s) {

    }

    @Override
    public void sendLevelData(String level, String monhoc) {

    }

    @Override
    public void send(String result, String question, String monhoc, String level) {

    }
}