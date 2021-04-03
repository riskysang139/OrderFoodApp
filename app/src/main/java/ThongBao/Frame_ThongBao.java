package ThongBao;

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

import com.example.appbanhang.MainActivity;
import com.example.appbanhang.R;
import com.example.appbanhang.databinding.FrameThongbaoBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import Json.PhoToArrayJson;
import Json.ThongBaoArray1;
import Json.ThongBaoArray2;
import Json.ThongBaoArray3;
import TrangChu.Frame_TrangChu;
import TrangChu.Photo;
import TrangChu.PhotoAdapter2;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Frame_ThongBao extends Fragment {
    FrameThongbaoBinding binding;
    List<Photo> mlistphoto2,mlistphoto1,mlistphoto3;
    Timer mTimer1,mTimer2,mTimer3;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.frame_thongbao,container,false);
        View view=binding.getRoot();
        thongBao1();

        thongBao2();

        thongBao3();

        return  view;
    }
    public void thongBao2  ()
    {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://demo3861295.mockable.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PhoToArrayJson phoToArrayJson2=retrofit.create(PhoToArrayJson.class);
        Call<ThongBaoArray2> call2=phoToArrayJson2.thongBao2();

        call2.enqueue(new Callback<ThongBaoArray2>() {
            @Override
            public void onResponse(Call<ThongBaoArray2> call2, Response<ThongBaoArray2> response) {
                try {
                    ThongBaoArray2 phoToArray=response.body();
                    mlistphoto2=new ArrayList<>(Arrays.asList(phoToArray.getImage5()));
                    ThongBaoAdapter2 adapter=new ThongBaoAdapter2(getActivity(), mlistphoto2);
                    binding.viewPager2.setAdapter(adapter);
                    autoSlideIMage2();
                }
                catch (Exception e)
                {
                    getFragmentManager().beginTransaction().replace(R.id.mainFragment,new Frame_ThongBao()).commit();
                }

            }


            @Override
            public void onFailure(Call<ThongBaoArray2> call2, Throwable t) {

            }
        });
    }
    public void thongBao3 ()
    {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://demo3861295.mockable.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PhoToArrayJson phoToArrayJson2=retrofit.create(PhoToArrayJson.class);
        Call<ThongBaoArray3> call2=phoToArrayJson2.thongBao3();

        call2.enqueue(new Callback<ThongBaoArray3>() {
            @Override
            public void onResponse(Call<ThongBaoArray3> call2, Response<ThongBaoArray3> response) {
                try {
                    ThongBaoArray3 phoToArray=response.body();
                    mlistphoto3=new ArrayList<>(Arrays.asList(phoToArray.getImage6()));
                    ThongBaoAdapter3 adapter=new ThongBaoAdapter3(getActivity(), mlistphoto3);
                    binding.viewPager3.setAdapter(adapter);
                    autoSlideIMage3();
                }
              catch (Exception e)
              { getFragmentManager().beginTransaction().replace(R.id.mainFragment,new Frame_ThongBao()).commit();
              }
            }


            @Override
            public void onFailure(Call<ThongBaoArray3> call2, Throwable t) {

            }
        });
    }
    public void thongBao1 ()
    {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://demo3861295.mockable.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PhoToArrayJson phoToArrayJson2=retrofit.create(PhoToArrayJson.class);
        Call<ThongBaoArray1> call2=phoToArrayJson2.thongBao1();

        call2.enqueue(new Callback<ThongBaoArray1>() {
            @Override
            public void onResponse(Call<ThongBaoArray1> call2, Response<ThongBaoArray1> response) {
                try {
                    ThongBaoArray1 phoToArray=response.body();
                    mlistphoto1=new ArrayList<>(Arrays.asList(phoToArray.getImage4()));
                    ThongBaoAdapter1 adapter=new ThongBaoAdapter1(getActivity(), mlistphoto1);
                    binding.viewPager1.setAdapter(adapter);
                    autoSlideIMage1();
                }
                catch (Exception e)
                {
                    getFragmentManager().beginTransaction().replace(R.id.mainFragment,new Frame_ThongBao()).commit();
                }

            }


            @Override
            public void onFailure(Call<ThongBaoArray1> call2, Throwable t) {

            }
        });
    }
    private void autoSlideIMage1() {
        if(mlistphoto1==null || mlistphoto1.isEmpty() || binding.viewPager1==null) {
            return;
        }
        if (mTimer1 == null) {
            mTimer1 = new Timer();
        }
        mTimer1.schedule(new TimerTask() {
            @Override
            public void run() {
                {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            int currentItem = binding.viewPager1.getCurrentItem();
                            int totalItem = mlistphoto1.size() - 1;
                            if (currentItem < totalItem) {
                                currentItem++;
                                binding.viewPager1.setCurrentItem(currentItem);
                            } else
                                binding.viewPager1.setCurrentItem(0);
                        }
                    });
                }
            }
        }, 2000, 5000);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mTimer2 != null || mTimer1 != null || mTimer3 != null  ) {
            try {
                mTimer2.cancel();
                mTimer1.cancel();
                mTimer3.cancel();
            }
            catch (Exception e)
            {
                Intent intent=new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }

        }
    }
    private void autoSlideIMage2() {
        if(mlistphoto2==null || mlistphoto2.isEmpty() || binding.viewPager2==null) {
            return;
        }
        if (mTimer2 == null) {
            mTimer2 = new Timer();
        }
        mTimer2.schedule(new TimerTask() {
            @Override
            public void run() {
                {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            int currentItem = binding.viewPager2.getCurrentItem();
                            int totalItem = mlistphoto2.size() - 1;
                            if (currentItem < totalItem) {
                                currentItem++;
                                binding.viewPager2.setCurrentItem(currentItem);
                            } else
                                binding.viewPager2.setCurrentItem(0);
                        }
                    });
                }
            }
        }, 3000, 6000);
    }
    private void autoSlideIMage3() {
        if(mlistphoto3==null || mlistphoto3.isEmpty() || binding.viewPager3==null) {
            return;
        }
        if (mTimer3 == null) {
            mTimer3 = new Timer();
        }
        mTimer3.schedule(new TimerTask() {
            @Override
            public void run() {
                {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            int currentItem = binding.viewPager3.getCurrentItem();
                            int totalItem = mlistphoto3.size() - 1;
                            if (currentItem < totalItem) {
                                currentItem++;
                                binding.viewPager3.setCurrentItem(currentItem);
                            } else
                                binding.viewPager3.setCurrentItem(0);
                        }
                    });
                }
            }
        }, 4000, 7000);
    }
}
