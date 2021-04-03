package NguoiDung;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.appbanhang.R;
import com.example.appbanhang.databinding.FrameToiBinding;

public class Frame_Toi extends Fragment {
    FrameToiBinding binding;
    UserHelper userHelper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.frame_toi,container,false);
        View view=binding.getRoot();
        userHelper=new UserHelper(getActivity());
        Cursor cursor=userHelper.getUser("SELECT * FROM user1 ");
        boolean checked=false;
        String admin="admin";
        while (cursor.moveToNext())
        {
            String check=cursor.getString(cursor.getColumnIndex("taiKhoan"));
            if(check.compareToIgnoreCase(admin)==0)
            {
                binding.txtName.setText(cursor.getString(1));
                binding.sdt.setText(cursor.getString(2));
                binding.email.setText(cursor.getString(3));
                binding.login.setText("Đăng Xuất");
                checked=true;
                break;
            }


        }
        if(!checked)
        {

        }

        binding.phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent=new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+"0971410156" ));
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                    {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE},1);
                    }
                    else
                    {
                        startActivity(callIntent);
                    }
                }
                else
                {
                    startActivity(callIntent);
                }
            }
        });
        binding.trungTamTroGiup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent helpIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://support.google.com/?hl=vi"));
                startActivity(helpIntent);
            }
        });
        binding.veChungToi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent infoIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://quananngon.com.vn/gioi-thieu.html"));
                startActivity(infoIntent);
            }
        });
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.mainFragment,new Frame_Login()).commit();
                binding.login.setText(R.string.dangXuat);
            }
        });
        return view;
    }

}
