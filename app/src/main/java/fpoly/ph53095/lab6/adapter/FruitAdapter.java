package fpoly.ph53095.lab6.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


import java.util.ArrayList;

import fpoly.ph53095.lab6.R;
import fpoly.ph53095.lab6.databinding.ItemFruitBinding;
import fpoly.ph53095.lab6.model.Fruit;

public class FruitAdapter  extends RecyclerView.Adapter<FruitAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Fruit> list;
    private FruitClick fruitClick;

    public FruitAdapter(Context context, ArrayList<Fruit> list, FruitClick fruitClick) {
        this.context = context;
        this.list = list;
        this.fruitClick = fruitClick;
    }

    public interface FruitClick {
        void delete(Fruit fruit);
        void edit(Fruit fruit);

        void showDetail(Fruit fruit);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFruitBinding binding = ItemFruitBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new ViewHolder(binding);
    }

//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Fruit fruit = list.get(position);
//        holder.binding.tvName.setText(fruit.getName());
//        holder.binding.tvPriceQuantity.setText("price :" +fruit.getPrice()+" - quantity:" +fruit.getQuantity());
//        holder.binding.tvDes.setText(fruit.getDescription());
////        String url  = fruit.getImage().get(0);
//        String url = "default_image_url"; // URL mặc định
//        if (fruit != null && fruit.getImage() != null && !fruit.getImage().isEmpty()) {
//            try {
//                url = fruit.getImage().get(0);
//            } catch (IndexOutOfBoundsException e) {
//                Log.e("FruitAdapter", "Lỗi IndexOutOfBoundsException tại vị trí " + position, e);
//            }
//        } else {
//            Log.e("FruitAdapter", "Danh sách ảnh rỗng hoặc null tại vị trí: " + position);
//        }
//
//
//
//
//        String newUrl = url.replace("localhost", "10.0.2.2");
//        Glide.with(context)
//                .load(newUrl)
//                .thumbnail(Glide.with(context).load(R.drawable.baseline_broken_image_24))
//                .into(holder.binding.img);
//
//        holder.binding.btnEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fruitClick.edit(fruit);
//            }
//        });
//        holder.binding.btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fruitClick.delete(fruit);
//            }
//        });
//
//        Log.d("321321", "onBindViewHolder: "+list.get(position).getImage().get(0));
//    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position >= list.size() || list.isEmpty()) {
            Log.e("FruitAdapter", "Danh sách rỗng hoặc vị trí không hợp lệ: " + position);
            return;
        }

        Fruit fruit = list.get(position);
        if (fruit == null) {
            Log.e("FruitAdapter", "Fruit tại vị trí " + position + " là null");
            return;
        }

        holder.binding.tvName.setText(fruit.getName());
        holder.binding.tvPriceQuantity.setText("price :" + fruit.getPrice() + " - quantity:" + fruit.getQuantity());
        holder.binding.tvDes.setText(fruit.getDescription());

        String url = "default_image_url";
        if (fruit.getImage() != null && !fruit.getImage().isEmpty()) {
            url = fruit.getImage().get(0);
        } else {
            Log.w("FruitAdapter", "Không có hình ảnh cho vị trí: " + position);
        }

        String newUrl = url.replace("localhost", "10.0.2.2");
        Glide.with(context)
                .load(newUrl)
                .error(R.drawable.baseline_broken_image_24)
                .thumbnail(Glide.with(context).load(R.drawable.baseline_broken_image_24))
                .into(holder.binding.img);

        holder.binding.btnEdit.setOnClickListener(v -> fruitClick.edit(fruit));
        holder.binding.btnDelete.setOnClickListener(v -> fruitClick.delete(fruit));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemFruitBinding binding;
        public ViewHolder(ItemFruitBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
