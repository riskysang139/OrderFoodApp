package DonHang;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.appbanhang.MainActivity;
import com.example.appbanhang.R;
import com.example.appbanhang.databinding.FrameDonhangBinding;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import TrangChu.Frame_TrangChu;


public class Frame_DonHang extends Fragment {
    FrameDonhangBinding binding;
    public static List<ChiTietMonAn2> contactlist1;
    public  static AdapterDonHang adapterDonHang;
    public static RecyclerView recyclerView;
    SQLHelper sqlHelper;
    static TextView toTalPrice;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.frame_donhang, container, false);
        View view = binding.getRoot();
        recyclerView=view.findViewById(R.id.gioHangRecyclerView);
        toTalPrice=view.findViewById(R.id.totalPrice);
        sqlHelper = new SQLHelper(getActivity());
        contactlist1 = new ArrayList<>();

        if(contactlist1!=null)
        {
            adapterDonHang = new AdapterDonHang(contactlist1,getActivity());
            binding.gioHangRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            binding.gioHangRecyclerView.setAdapter(adapterDonHang);
            Cursor cursor = sqlHelper.getDataDonHang("SELECT * FROM Food1 ");
            while (cursor.moveToNext()) {
                contactlist1.add(new ChiTietMonAn2(cursor.getString(1), cursor.getLong(2), cursor.getBlob(3), 1));
            }
            adapterDonHang.notifyDataSetChanged();
            MainActivity.badgeDrawable_donhang.setVisible(true);
            checkData();
            tongTien();
        }
        if(recyclerView.getAdapter().getItemCount()==0)
        {
            MainActivity.badgeDrawable_donhang.setNumber(0);
        }
        getItemCount();
        binding.thanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contactlist1 != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Thông Báo").setMessage("Bạn Muốn Thanh Toán Số Món Ăn Này ?")
                            .setPositiveButton("Đồng Ý", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    sqlHelper.delAllProduct();
                                    adapterDonHang.notifyDataSetChanged();
                                    MainActivity.badgeDrawable_donhang.setNumber(0);
                                    getFragmentManager().beginTransaction().replace(R.id.mainFragment, new Frame_DonHang()).commit();
                                }
                            })
                            .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getActivity(), "Thanh Toán Không Thành Công !", Toast.LENGTH_LONG).show();
                                }
                            }).create().show();
                }
                else Toast.makeText(getActivity(),"Giỏ hàng của bạn chưa có sản phẩm nào",Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
    public static int getItemCount() {
        if (contactlist1 != null)
            return recyclerView.getAdapter().getItemCount();
        return 0;
    }
    public void checkData() {
        if (contactlist1.size() <= 0) {
            binding.textrong.setVisibility(View.VISIBLE);
        } else
            binding.textrong.setVisibility(View.INVISIBLE);
    }
    public static void tongTien()
    {
        long tongTien=0;
        for(int i=0;i<contactlist1.size();i++)
        {
            tongTien+=contactlist1.get(i).getPrice();
        }
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        toTalPrice.setText(decimalFormat.format(tongTien)+"  VNĐ");
    }
}
