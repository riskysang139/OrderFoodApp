package YeuThich;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.appbanhang.MainActivity;
import com.example.appbanhang.R;

import java.util.List;

import DonHang.Frame_DonHang;
import Json.ChiTietMonAn;
import TrangChu.ListMonAn.SupCoZiDo;
import TrangChu.TrangChu_AdapterMon.ItemClickListener;

public class YeuThichAdapter extends RecyclerView.Adapter<YeuThichAdapter.ViewHoder> {
    List<ChiTietMonAn1> contactList;
    Context context;
    YeuThichSQL yeuThichSQL;
    ViewBinderHelper viewBinderHelper=new ViewBinderHelper();
    public YeuThichAdapter(List<ChiTietMonAn1> contactList1, Context context) {
        this.contactList = contactList1;
        this.context = context;
    }

    @NonNull
    @Override
    public YeuThichAdapter.ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_yeu_thich_itemcontact,parent,false);
        return new YeuThichAdapter.ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull YeuThichAdapter.ViewHoder holder, int position) {
        ChiTietMonAn1 chiTietMonAn=contactList.get(position);
        yeuThichSQL=new YeuThichSQL(context);
        holder.textTitle.setText(chiTietMonAn.getTenMonAn());
        byte[] image=chiTietMonAn.getImage();
        Bitmap bitmap= BitmapFactory.decodeByteArray(image,0,image.length);
        holder.imageButton.setImageBitmap(bitmap);
        viewBinderHelper.bind(holder.swipeRevealLayout,chiTietMonAn.getTenMonAn());
        holder.deleteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactList.remove(holder.getAdapterPosition());
                yeuThichSQL.deleteProduct(chiTietMonAn.getTenMonAn());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });

    }
    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        ImageButton imageButton;
        TextView textTitle;
        ItemClickListener itemClickListener;
        RelativeLayout deleteLayout;
        SwipeRevealLayout swipeRevealLayout;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            imageButton=itemView.findViewById(R.id.imageButton);
            textTitle=itemView.findViewById(R.id.textTitle);
            itemView.setOnClickListener(this::onClick);
            swipeRevealLayout=itemView.findViewById(R.id.swipeLayout);
            deleteLayout=itemView.findViewById(R.id.deleteLayout);
        }


        @Override
        public void onClick(View v) {
            itemClickListener.OnClick(v,getAdapterPosition());
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
    }
}
