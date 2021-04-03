package TrangChu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.appbanhang.MainActivity;
import com.example.appbanhang.MapsActivity;
import com.example.appbanhang.R;
import com.example.appbanhang.databinding.FrameTrangchuBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import DonHang.Frame_DonHang;
import Json.PhoToArray;
import Json.PhoToArray1;
import Json.PhoToArray2;
import Json.PhoToArray3;
import Json.PhoToArrayJson;
import TrangChu.ListMonAn.MonTayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Frame_TrangChu extends Fragment {
    Timer mTimer;
    List<Photo> mlistphoto;
    FrameTrangchuBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.frame_trangchu, container, false);
        View view = binding.getRoot();
        BottomNavigationView bottomNavigationView=getActivity().findViewById(R.id.bottomNavigation);
        bottomNavigationView.setVisibility(View.VISIBLE);
        viewPagerAdvertisement();
        viewPager1();
        viewPager2();
        viewPager3();
        binding.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainFragment, new FrameTrangChuSearch());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        binding.imagesearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainFragment, new FrameTrangChuSearch());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        binding.monTay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainFragment, new MonTayList());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        binding.monTau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainFragment, new MonTayList());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }

        });
        binding.monAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainFragment, new MonTayList());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
        binding.monViet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainFragment, new MonTayList());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        binding.doAnVat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainFragment, new MonTayList());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        binding.doUong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainFragment, new MonTayList());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        binding.doChienRan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainFragment, new MonTayList());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        binding.doNgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainFragment, new MonTayList());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        binding.located.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void autoSlideIMage() {
        try {
            if (mlistphoto == null || mlistphoto.isEmpty() || binding.viewPager == null) {
                return;
            }
        }
        catch (Exception e)
        {
            Intent intent=new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }
        if (mTimer == null) {
            mTimer = new Timer();
        }
        mTimer = new Timer();
            mTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                int currentItem = binding.viewPager.getCurrentItem();
                                int totalItem = mlistphoto.size() - 1;
                                if (currentItem < totalItem) {
                                    currentItem++;
                                    binding.viewPager.setCurrentItem(currentItem);
                                } else
                                    binding.viewPager.setCurrentItem(0);
                            }
                        });
                    }
                }
            }, 2000, 5000);
        }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
        }
    }
    public void viewPagerAdvertisement()
    {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://demo3861295.mockable.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PhoToArrayJson phoToArrayJson=retrofit.create(PhoToArrayJson.class);
        Call<PhoToArray> call=phoToArrayJson.image();

        call.enqueue(new Callback<PhoToArray>() {
            @Override
            public void onResponse(Call<PhoToArray> call, Response<PhoToArray> response) {
                PhoToArray phoToArray=response.body();
                try {
                    mlistphoto=new ArrayList<>(Arrays.asList(phoToArray.getImage()));
                    PhotoAdapter adapter=new PhotoAdapter(getActivity(), mlistphoto);
                    binding.viewPager.setAdapter(adapter);
                    autoSlideIMage();
                }
              catch (Exception e)
              {

              }
            }

            @Override
            public void onFailure(Call<PhoToArray> call, Throwable t) {

            }
        });

    }
    public void viewPager1()
    {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://demo3861295.mockable.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PhoToArrayJson phoToArrayJson1=retrofit.create(PhoToArrayJson.class);
        Call<PhoToArray1> call1=phoToArrayJson1.image1();

        call1.enqueue(new Callback<PhoToArray1>() {
            @Override
            public void onResponse(Call<PhoToArray1> call1, Response<PhoToArray1> response) {
                    PhoToArray1 phoToArray=response.body();
                    List<Photo> mlistphoto1=new ArrayList<>(Arrays.asList(phoToArray.getImage1()));
                    PhotoAdapter1 adapter=new PhotoAdapter1(getActivity(), mlistphoto1);
                    binding.viewPager1.setAdapter(adapter);

            }


            @Override
            public void onFailure(Call<PhoToArray1> call1, Throwable t) {

            }
        });
    }
    public void viewPager2()
    {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://demo3861295.mockable.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PhoToArrayJson phoToArrayJson2=retrofit.create(PhoToArrayJson.class);
        Call<PhoToArray2> call2=phoToArrayJson2.image2();

        call2.enqueue(new Callback<PhoToArray2>() {
            @Override
            public void onResponse(Call<PhoToArray2> call2, Response<PhoToArray2> response) {
                try {
                    PhoToArray2 phoToArray=response.body();
                    List<Photo> mlistphoto2=new ArrayList<>(Arrays.asList(phoToArray.getImage2()));
                    PhotoAdapter2 adapter=new PhotoAdapter2(getActivity(), mlistphoto2);
                    binding.viewPager2.setAdapter(adapter);
                }
            catch (Exception e)
            {

            }
            }


            @Override
            public void onFailure(Call<PhoToArray2> call2, Throwable t) {

            }
        });
    }
    public void viewPager3()
    {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://demo3861295.mockable.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PhoToArrayJson phoToArrayJson3=retrofit.create(PhoToArrayJson.class);
        Call<PhoToArray3> call3=phoToArrayJson3.image3();

        call3.enqueue(new Callback<PhoToArray3>() {
            @Override
            public void onResponse(Call<PhoToArray3> call3, Response<PhoToArray3> response) {
                PhoToArray3 phoToArray=response.body();
                try {
                    List<Photo> mlistphoto3=new ArrayList<>(Arrays.asList(phoToArray.getImage3()));
                    PhotoAdapter3 adapter=new PhotoAdapter3(getActivity(), mlistphoto3);
                    binding.viewPager3.setAdapter(adapter);
                }
                catch (Exception e)
                {
                    getFragmentManager().beginTransaction().replace(R.id.mainFragment,new Frame_TrangChu()).commit();
                }
            }
            @Override
            public void onFailure(Call<PhoToArray3> call3, Throwable t) {

            }
        });
    }
}
