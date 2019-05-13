package com.bw.movie.fmk.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.fmk.bean.RMenBean;
import com.bw.movie.fmk.bean.RYingBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * @Auther: 付明锟
 * @Date: 2019/5/13 9:22
 * @Description:
 */
public class RYingAdapter extends RecyclerView.Adapter<RYingAdapter.Holder> {

    public List<RYingBean.ResultBean> data;
    public Context mContext;

    public RYingAdapter(List<RYingBean.ResultBean> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rmen_item, null);
      //  view.setOnClickListener(this);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        String imageUrl = data.get(i).getImageUrl();
        holder.rmen_item_id.setImageURI(Uri.parse(imageUrl));
    }

//    @Override
//    public void onClick(View view) {
//        if(onItemClick!=null){
//            onItemClick.onItemClick(view,(int)view.getTag());
//        }
//    }
//
//    public interface OnItemClick{
//        void onItemClick(View view,int position);
//    }
//    private OnItemClick onItemClick;
//
//    public void setOnItemClick(OnItemClick onItemClick) {
//        this.onItemClick = onItemClick;
//    }
//
    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private SimpleDraweeView rmen_item_id;

        public Holder(@NonNull View itemView) {
            super(itemView);
            rmen_item_id = itemView.findViewById(R.id.rmen_item_id);
        }
    }
}
