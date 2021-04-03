package NguoiDung;

import android.content.Intent;
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
import androidx.fragment.app.FragmentTransaction;

import com.example.appbanhang.MainActivity;
import com.example.appbanhang.R;
import com.example.appbanhang.databinding.FrameLoginBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Frame_Login extends Fragment {
    FrameLoginBinding binding;
    UserHelper userHelper;
    static TextView username;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.frame_login,container,false);
        View view=binding.getRoot();
        userHelper=new UserHelper(getActivity());
        BottomNavigationView bottomNavigationView=getActivity().findViewById(R.id.bottomNavigation);
        bottomNavigationView.setVisibility(View.INVISIBLE);
        username=view.findViewById(R.id.username);

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked=false;
                String username=binding.username.getText().toString();
                Cursor cursor=userHelper.getUser("SELECT * FROM user1");
                while (cursor.moveToNext())
                {
                    String check=cursor.getString(cursor.getColumnIndex("taiKhoan"));
                    if(check.equals(username))
                    {
                        Intent intent=new Intent(getActivity(),MainActivity.class);
                        startActivity(intent);
                        checked=true;
                        break;
                    }

                }
                if(!checked) {
                    Toast.makeText(getActivity(),"Tên tài khoản hoặc mật khẩu không chính xác",Toast.LENGTH_LONG).show();
                }
            }
        });
        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainFragment, new Frame_Register());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return view;
    }
}
