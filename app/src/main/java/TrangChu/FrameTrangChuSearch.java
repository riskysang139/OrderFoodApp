package TrangChu;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.appbanhang.MainActivity;
import com.example.appbanhang.R;
import com.example.appbanhang.databinding.FrameTrangchuTimkiemBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;
import Json.DataMonAn;
import Json.DataMonAnList;
import Json.DataMonJson;
import TrangChu.ListMonAn.BanhBruschestta;
import TrangChu.ListMonAn.BanhCrepe;
import TrangChu.ListMonAn.BanhPizza;
import TrangChu.ListMonAn.BanhRosti;
import TrangChu.ListMonAn.MiSpagetti;
import TrangChu.ListMonAn.SupCoZiDo;
import TrangChu.TrangChu_AdapterMon.MonTayList_DataMonAnAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class FrameTrangChuSearch extends Fragment{
    FrameTrangchuTimkiemBinding binding;
    List<DataMonAn> contactlist;
    SearchView searchView;
    MonTayList_DataMonAnAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=DataBindingUtil.inflate(inflater,R.layout.frame_trangchu_timkiem,container,false);
        View view=binding.getRoot();
        BottomNavigationView bottomNavigationView=getActivity().findViewById(R.id.bottomNavigation);
        bottomNavigationView.setVisibility(View.INVISIBLE);

        AutoCompleteTextView autoCompleteTextView=view.findViewById(R.id.textTimKiem);
        String[] listMon=getResources().getStringArray(R.array.listMon);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,listMon);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                        getFragmentManager().beginTransaction().replace(R.id.mainFragment,new BanhBruschestta()).addToBackStack(null).commit();
                        break;
                    case 1:
                        getFragmentManager().beginTransaction().replace(R.id.mainFragment,new BanhPizza()).addToBackStack(null).commit();
                        break;
                    case 2:
                        getFragmentManager().beginTransaction().replace(R.id.mainFragment,new BanhCrepe()).addToBackStack(null).commit();
                        break;
                    case 3:
                        getFragmentManager().beginTransaction().replace(R.id.mainFragment,new BanhRosti()).addToBackStack(null).commit();
                        break;
                }
            }
        });
        contactlist=new ArrayList<>();

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
                binding.recycleviewMonSearch.setLayoutManager(new LinearLayoutManager(getActivity()));
                binding.recycleviewMonSearch.setAdapter(monTayList_dataMonAnAdapter);
            }

            @Override
            public void onFailure(Call<DataMonAnList> call, Throwable t) {

            }
        });
        binding.Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        binding.btnSupCozido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.mainFragment,new SupCoZiDo()).addToBackStack(null)
                        .commit();

            }
        });
        binding.btnbanhRosti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.mainFragment,new BanhRosti()).addToBackStack(null)
                        .commit();
            }
        });
        binding.btnBruschestta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.mainFragment,new BanhBruschestta()).addToBackStack(null)
                        .commit();
            }
        });
        binding.btnMiY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.mainFragment,new MiSpagetti()).addToBackStack(null)
                        .commit();
            }
        });
        return view;
    }
}
