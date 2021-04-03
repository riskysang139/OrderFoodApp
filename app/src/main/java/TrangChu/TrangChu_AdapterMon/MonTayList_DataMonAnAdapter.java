package TrangChu.TrangChu_AdapterMon;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appbanhang.MainActivity;
import com.example.appbanhang.R;

import java.util.ArrayList;
import java.util.List;

import Json.DataMonAn;
import TrangChu.ListMonAn.BanhBruschestta;
import TrangChu.ListMonAn.BanhCrepe;
import TrangChu.ListMonAn.BanhPizza;
import TrangChu.ListMonAn.BanhRosti;
import TrangChu.ListMonAn.MiSpagetti;
import TrangChu.ListMonAn.SupCoZiDo;


public class MonTayList_DataMonAnAdapter extends RecyclerView.Adapter<MonTayList_DataMonAnAdapter.ViewHoder> implements Filterable {
    List<DataMonAn> contactList;
    Context context;
    List<DataMonAn> contactListOld;
    public MonTayList_DataMonAnAdapter(List<DataMonAn> contactList, Context context) {
        this.contactList = contactList;
        this.context = context;
        this.contactListOld=contactList;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_trangchu_listmon_itemscontact, parent, false);
        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        DataMonAn dataMonAn = contactList.get(position);
        holder.textTitle.setText(contactList.get(position).getTenMon());
        holder.textPrice.setText(contactList.get(position).getPrice());
        Glide.with(context)
                .load(dataMonAn.getLinkImage())
                .into(holder.imageButton);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void OnClick(View view, int position) {
                Context context = view.getContext();
                MainActivity activity = (MainActivity) context;
                switch (position) {
                    case 0:
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.mainFragment,new SupCoZiDo()).addToBackStack(null).commit();
                        break;
                    case 1:
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.mainFragment,new BanhBruschestta()).addToBackStack(null).commit();
                        break;
                    case 2:
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.mainFragment,new MiSpagetti()).addToBackStack(null).commit();
                        break;
                    case 3:
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.mainFragment,new BanhRosti()).addToBackStack(null).commit();
                        break;
                    case 4:
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.mainFragment,new BanhPizza()).addToBackStack(null).commit();
                        break;
                    case 5:
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.mainFragment,new BanhCrepe()).addToBackStack(null).commit();
                        break;
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return 6;
    }



    public class ViewHoder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageButton imageButton;
        TextView textTitle;
        TextView textPrice;
        ItemClickListener itemClickListener;

        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            imageButton = itemView.findViewById(R.id.imageButton);
            textTitle = itemView.findViewById(R.id.textTitle);
            textPrice = itemView.findViewById(R.id.price);
            itemView.setOnClickListener(this::onClick);

        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.OnClick(v, getAdapterPosition());

        }
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String str=constraint.toString();
                if(str.isEmpty())
                {
                    contactList=contactListOld;
                }
                else {
                    List<DataMonAn> list=new ArrayList<>();
                    for(DataMonAn dataMonAn :contactListOld)
                    {
                        if(dataMonAn.getTenMon().toLowerCase().contains(str.toLowerCase()))
                        {
                            list.add(dataMonAn);
                        }
                    }
                    contactList=list;
                }
                FilterResults filterResults=new FilterResults();
                filterResults.values=contactList;
                return  filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                contactList= (List<DataMonAn>) results.values;
                notifyDataSetChanged();
            }
        };
    }

}