package com.example.appbanhang;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.appbanhang.databinding.ActivityMainBinding;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import DonHang.Frame_DonHang;
import NguoiDung.Frame_Toi;
import ThongBao.Frame_ThongBao;
import TrangChu.Frame_TrangChu;
import TrangChu.ListMonAn.MonTayList;
import YeuThich.Frame_YeuThich;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    public static BadgeDrawable badgeDrawable_donhang,getBadgeDrawable_yeuthich;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        getSupportActionBar().hide();
        getSupportFragmentManager().beginTransaction().add(R.id.mainFragment, new Frame_TrangChu()).commit();
        badgeDrawable_donhang=binding.bottomNavigation.getOrCreateBadge(R.id.gioHang);
        badgeDrawable_donhang.setNumber(0);
        badgeDrawable_donhang.setVisible(true);
        binding.bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.trangChu:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainFragment,new Frame_TrangChu()).commit();
                        break;
                    case R.id.gioHang:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainFragment,new Frame_DonHang()).commit();
                        break;
                    case R.id.daLuu:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainFragment,new Frame_YeuThich()).commit();
                        break;
                    case R.id.thongBao:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainFragment,new Frame_ThongBao()).commit();
                        break;
                    case R.id.toi:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainFragment,new Frame_Toi()).commit();
                        break;
                }
                return  true;
            }
        });
    }


}
