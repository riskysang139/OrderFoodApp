package DonHang;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.appbanhang.MainActivity;
import com.example.appbanhang.R;

import java.text.DecimalFormat;
import java.util.List;

import TrangChu.ListMonAn.BanhBruschestta;
import TrangChu.ListMonAn.BanhRosti;
import TrangChu.ListMonAn.MiSpagetti;
import TrangChu.ListMonAn.SupCoZiDo;
import TrangChu.TrangChu_AdapterMon.ItemClickListener;


public class AdapterDonHang extends RecyclerView.Adapter<AdapterDonHang.ViewHoder> {
    List<ChiTietMonAn2> contactList;
    Context context;
    SQLHelper sqlHelper;
    ViewBinderHelper viewBinderHelper=new ViewBinderHelper();
    public AdapterDonHang(List<ChiTietMonAn2> contactList, Context context) {
        this.contactList = contactList;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_don_hang_itemcontact,parent,false);
        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        sqlHelper=new SQLHelper(context);
        ChiTietMonAn2 chiTietMonAn=contactList.get(position);
        holder.TextTitle.setText(chiTietMonAn.getTenMonAn());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        holder.textPrice.setText(decimalFormat.format(chiTietMonAn.getPrice()));
        byte[] image=chiTietMonAn.getImage();
        Bitmap bitmap= BitmapFactory.decodeByteArray(image,0,image.length);
        holder.imageButton.setImageBitmap(bitmap);
        holder.values.setText(String.valueOf(chiTietMonAn.getSoLuong()));
        int soluong=Integer.parseInt(holder.values.getText()+"");
        if(soluong>=10)
        {
            holder.btnPlus.setVisibility(View.INVISIBLE);
            holder.btnminius.setVisibility(View.VISIBLE);
        }
        else if(soluong <=1)
        {
            holder.btnminius.setVisibility(View.INVISIBLE);

        }
        else if(soluong >=1)
        {
            holder.btnminius.setVisibility(View.VISIBLE);
            holder.btnPlus.setVisibility(View.VISIBLE);
        }
        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slht=Frame_DonHang.contactlist1.get(position).getSoLuong();
                int slMoiNhat=Integer.parseInt(holder.values.getText()+"")+1;
                if(slMoiNhat>=10)
                {
                    holder.btnPlus.setVisibility(View.INVISIBLE);
                    holder.btnminius.setVisibility(View.VISIBLE);
                }
                else if(slMoiNhat <=1)
                {
                    holder.btnminius.setVisibility(View.INVISIBLE);
                }
                else if(slMoiNhat >1)
                {
                    holder.btnminius.setVisibility(View.VISIBLE);
                    holder.btnPlus.setVisibility(View.VISIBLE);
                }
                Frame_DonHang.contactlist1.get(position).setSoLuong(slMoiNhat);
                holder.values.setText(slMoiNhat+"");
                long giaHT= Frame_DonHang.contactlist1.get(position).getPrice();
                long giaMN=(giaHT*slMoiNhat)/slht;
                DecimalFormat decimalFormat1=new DecimalFormat("###,###,###");
                holder.textPrice.setText(decimalFormat1.format(giaMN));
                 Frame_DonHang.contactlist1.get(position).setPrice(giaMN);
                Frame_DonHang.tongTien();
            }
        });
        holder.btnminius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slht=Frame_DonHang.contactlist1.get(position).getSoLuong();
                int slMoiNhat=Integer.parseInt(holder.values.getText()+"")-1;
                if(slMoiNhat>=10)
                {
                    holder.btnPlus.setVisibility(View.INVISIBLE);
                    holder.btnminius.setVisibility(View.VISIBLE);
                }
                else if(slMoiNhat <=1)
                {
                    holder.btnminius.setVisibility(View.INVISIBLE);
                }
                else if(slMoiNhat >1)
                {
                    holder.btnminius.setVisibility(View.VISIBLE);
                    holder.btnPlus.setVisibility(View.VISIBLE);
                }
                Frame_DonHang.contactlist1.get(position).setSoLuong(slMoiNhat);
                holder.values.setText(slMoiNhat+"");
                long giaHT= Frame_DonHang.contactlist1.get(position).getPrice();
                long giaMN=(giaHT*slMoiNhat)/slht;
                DecimalFormat decimalFormat1=new DecimalFormat("###,###,###");
                holder.textPrice.setText(decimalFormat1.format(giaMN));
                Frame_DonHang.contactlist1.get(position).setPrice(giaMN);
                Frame_DonHang.tongTien();
            }
        });
        viewBinderHelper.bind(holder.swipeRevealLayout,chiTietMonAn.getTenMonAn());
        holder.deleteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactList.remove(holder.getAdapterPosition());
                sqlHelper.deleteProduct(chiTietMonAn.getTenMonAn());
                notifyItemRemoved(holder.getAdapterPosition());
                MainActivity.badgeDrawable_donhang.setNumber(MainActivity.badgeDrawable_donhang.getNumber()-1);
                Frame_DonHang.tongTien();
            }
        });

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageButton imageButton;
        TextView TextTitle;
        TextView textPrice,values;
        Button btnPlus,btnminius;
        ItemClickListener itemClickListener;
        RelativeLayout deleteLayout;
        SwipeRevealLayout swipeRevealLayout;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            imageButton=itemView.findViewById(R.id.imageButton);
            TextTitle=itemView.findViewById(R.id.textTitle);
            textPrice=itemView.findViewById(R.id.price);
            btnPlus=itemView.findViewById(R.id.btncong);
            btnminius=itemView.findViewById(R.id.btntru);
            values=itemView.findViewById(R.id.textSoLuong);
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
