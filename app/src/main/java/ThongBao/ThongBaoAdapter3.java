package ThongBao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.appbanhang.MainActivity;
import com.example.appbanhang.R;

import java.util.List;

import TrangChu.ListMonAn.BanhBruschestta;
import TrangChu.Photo;

public class ThongBaoAdapter3 extends PagerAdapter {
    private Context mContext;
    private List<Photo> mlistPhoto;

    public ThongBaoAdapter3(Context mContext, List<Photo> mlistPhoto) {
        this.mContext = mContext;
        this.mlistPhoto = mlistPhoto;
    }


    @Override
    public int getCount() {
        if(mlistPhoto != null)
        {
            return  mlistPhoto.size();
        }
        return 0;

    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view= LayoutInflater.from(container.getContext()).inflate(R.layout.view_pager_item,container,false);
        ImageView imageView=view.findViewById(R.id.imgPhoto);
        Photo photo=mlistPhoto.get(position);
        if(photo!=null)
        {
            Glide.with(mContext)
                    .load(photo.getResourceID())
                    .into(imageView);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position)
                {

                }
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
