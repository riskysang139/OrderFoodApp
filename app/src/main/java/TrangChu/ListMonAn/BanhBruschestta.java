package TrangChu.ListMonAn;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.appbanhang.MainActivity;
import com.example.appbanhang.R;
import com.example.appbanhang.databinding.FrameChitietmonBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;

import DonHang.Frame_DonHang;
import DonHang.SQLHelper;
import Json.ApiMonAnChiTiet;
import Json.ChiTietMonAn;
import YeuThich.YeuThichSQL;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class BanhBruschestta extends Fragment{
    FrameChitietmonBinding binding;
    SQLHelper sqlHelper;
    YeuThichSQL yeuThichSQL;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.frame_chitietmon, container, false);
        View view = binding.getRoot();
        sqlHelper = new SQLHelper(getActivity());
        yeuThichSQL = new YeuThichSQL(getActivity());
        BottomNavigationView bottomNavigationView=getActivity().findViewById(R.id.bottomNavigation);
        bottomNavigationView.setVisibility(View.INVISIBLE);
        ApiMonAnChiTiet.apiserver.banhBruschessta().enqueue(new Callback<ChiTietMonAn>() {
            @Override
            public void onResponse(Call<ChiTietMonAn> call, Response<ChiTietMonAn> response) {
                ChiTietMonAn dataMon = response.body();
                Glide.with(getActivity())
                        .load(dataMon.getImage())
                        .into(binding.imageMonAn);
                binding.tenMonAn.setText(dataMon.getTenMonAn());
                binding.mieuTaMonAn.setText(dataMon.getChiTietMon());
                binding.price.setText(dataMon.getPrice());
            }

            @Override
            public void onFailure(Call<ChiTietMonAn> call, Throwable t) {
                Toast.makeText(getActivity(), "L???i", Toast.LENGTH_LONG).show();
            }
        });
        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        binding.themHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) binding.imageMonAn.getDrawable();
                Bitmap bitmap = Bitmap.createBitmap(bitmapDrawable.getBitmap());
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                String tenMonAn = binding.tenMonAn.getText().toString();
                byte[] image = byteArrayOutputStream.toByteArray();
                String price = (String) binding.price.getText();
                boolean checked = false;
                Cursor cursor = sqlHelper.getDataDonHang("SELECT * FROM Food1");
                while (cursor.moveToNext()) {
                    String check = cursor.getString(cursor.getColumnIndex("name"));
                    if (check.equals(tenMonAn)) {
                        Toast.makeText(getActivity(), "B???n ???? Th??m V??o Gi??? H??ng R???i !", Toast.LENGTH_LONG).show();
                        checked = true;
                        break;
                    }

                }
                if (!checked) {
                    AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
                    builder.setTitle("Th??ng b??o");
                    builder.setMessage("B???n c?? mu???n th??m m??n ??n n??y v??o gi??? h??ng kh??ng ?");
                    builder.setPositiveButton("?????ng ??", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            sqlHelper.insertMon(tenMonAn, price, image);
                            MainActivity.badgeDrawable_donhang.setNumber(MainActivity.badgeDrawable_donhang.getNumber()+1);
                            Toast.makeText(getActivity(), "Th??m Th??nh C??ng V??o Gi??? H??ng !", Toast.LENGTH_LONG).show();

                        }
                    });
                    builder.setNegativeButton("Kh??ng", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(), "Th??m Kh??ng Th??nh C??ng !", Toast.LENGTH_LONG).show();
                        }
                    });
                    builder.create().show();


                }

            }
        });
        binding.btnYeuThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) binding.imageMonAn.getDrawable();
                Bitmap bitmap = Bitmap.createBitmap(bitmapDrawable.getBitmap());
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                String tenMonAn = binding.tenMonAn.getText().toString();
                byte[] image = byteArrayOutputStream.toByteArray();
                boolean checked = false;
                Cursor cursor = yeuThichSQL.getDataYeuThich("SELECT * FROM Love2");
                while (cursor.moveToNext()) {
                    String check = cursor.getString(cursor.getColumnIndex("name"));
                    if (check.equals(tenMonAn)) {
                        Toast.makeText(getActivity(), "B???n ???? Th??m V??o M???c Y??u Th??ch R???i !", Toast.LENGTH_LONG).show();
                        checked = true;
                        break;
                    }

                }
                if (!checked) {
                    AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
                    builder.setTitle("Th??ng b??o");
                    builder.setMessage("B???n c?? mu???n th??m m??n ??n n??y v??o m???c y??u th??ch kh??ng ?");
                    builder.setPositiveButton("?????ng ??", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            yeuThichSQL.insertMon(tenMonAn, image);
                            Toast.makeText(getActivity(), "Th??m Th??nh C??ng V??o M???c Y??u Th??ch !", Toast.LENGTH_LONG).show();
                        }
                    });
                    builder.setNegativeButton("Kh??ng", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.create().show();


                }


            }
        });
        return view;
    }
}

