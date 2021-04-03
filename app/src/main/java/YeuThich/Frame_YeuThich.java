package YeuThich;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.appbanhang.R;
import com.example.appbanhang.databinding.FrameYeuthichBinding;

import java.util.ArrayList;
import java.util.List;

import DonHang.SQLHelper;
import Json.ChiTietMonAn;


public class Frame_YeuThich extends Fragment {
    FrameYeuthichBinding binding;
    static List<ChiTietMonAn1> contactlist;
    YeuThichAdapter yeuThichAdapter;
    YeuThichSQL sqlHelper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=DataBindingUtil.inflate(inflater, R.layout.frame_yeuthich,container,false);
        View view=binding.getRoot();
        sqlHelper = new YeuThichSQL(getActivity());
        contactlist=new ArrayList<>();
        if(contactlist!=null)
        {
            yeuThichAdapter=new YeuThichAdapter(contactlist,getContext());
            binding.yeuThichRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.yeuThichRecyclerView.setAdapter(yeuThichAdapter);
            Cursor cursor=sqlHelper.getDataYeuThich("SELECT * FROM Love2");
            while (cursor.moveToNext())
            {
                contactlist.add(new ChiTietMonAn1(cursor.getString(1),cursor.getBlob(2)));
            }
            yeuThichAdapter.notifyDataSetChanged();
            checkData();
        }

        return  view;
    }
    public  void checkData()
    {
        if(contactlist.size()<=0)
        {
            binding.textrong.setVisibility(View.VISIBLE);
        }
        else
            binding.textrong.setVisibility(View.INVISIBLE);
    }
}
