package TrangChu.ListMonAn;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.appbanhang.R;
import com.example.appbanhang.databinding.FrameTrangchuListmonBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Json.DataMonAn;
import Json.DataMonAnList;
import Json.DataMonJson;
import TrangChu.TrangChu_AdapterMon.MonTayList_DataMonAnAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MonTayList extends Fragment {
    FrameTrangchuListmonBinding binding;
    List<DataMonAn> contactlist;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.frame_trangchu_listmon, container, false);
        View view = binding.getRoot();
        BottomNavigationView bottomNavigationView=getActivity().findViewById(R.id.bottomNavigation);
        bottomNavigationView.setVisibility(View.INVISIBLE);
        binding.listMon.setText(R.string.monTay);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://demo3861295.mockable.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DataMonJson dataMonJson = retrofit.create(DataMonJson.class);
        Call<DataMonAnList> call=dataMonJson.monTay();

        call.enqueue(new Callback<DataMonAnList>() {
            @Override
            public void onResponse(Call<DataMonAnList> call, Response<DataMonAnList> response) {
                DataMonAnList dataMonAnList=response.body();
                contactlist=new ArrayList<>(dataMonAnList.getListMon());
                MonTayList_DataMonAnAdapter monTayList_dataMonAnAdapter=new MonTayList_DataMonAnAdapter(contactlist,getActivity());
                binding.recycleviewMon.setLayoutManager(new LinearLayoutManager(getActivity()));
                binding.recycleviewMon.setAdapter(monTayList_dataMonAnAdapter);
            }

            @Override
            public void onFailure(Call<DataMonAnList> call, Throwable t) {
            }
        });
        binding.exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        return view;
    }
}

