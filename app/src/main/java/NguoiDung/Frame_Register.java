
package NguoiDung;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.appbanhang.R;
import com.example.appbanhang.databinding.FrameRegisterBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Frame_Register extends Fragment {
    FrameRegisterBinding binding;
    UserHelper userHelper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.frame__register,container,false);
        View view=binding.getRoot();
        BottomNavigationView bottomNavigationView=getActivity().findViewById(R.id.bottomNavigation);
        bottomNavigationView.setVisibility(View.INVISIBLE);

        userHelper=new UserHelper(getActivity());

        binding.registerAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=binding.hoTen.getText().toString();
                String sdt=binding.sdt.getText().toString();
                String email=binding.email.getText().toString();
                String taiKhoan=binding.username.getText().toString().trim();
                String matKhau=binding.password.getText().toString();
                String online="on";
                Cursor cursor = userHelper.getUser("SELECT * FROM user1");
                String tenTaiKhoan=binding.username.getText().toString();
                boolean checked=false;
                while (cursor.moveToNext())
                {
                    String check=cursor.getString(cursor.getColumnIndex("name"));
                    if(check.equals(tenTaiKhoan))
                    {
                        Toast.makeText(getActivity(),"Tài khoản đã tồn tại",Toast.LENGTH_LONG).show();
                        checked=true;
                        break;
                    }


                }
                if(!checked)
                {
                    userHelper.insertUser(name, sdt, email, taiKhoan, matKhau, online);
                    Toast.makeText(getActivity(), "Thêm Tài Khoản Thành Công", Toast.LENGTH_LONG).show();
                }

            }

        });
        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.mainFragment,new Frame_Login()).commit();
            }
        });
        return view;
    }
}