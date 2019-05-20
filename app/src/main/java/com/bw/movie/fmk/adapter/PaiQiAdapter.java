package com.bw.movie.fmk.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.fmk.bean.GouPiaoBean;
import com.bw.movie.fmk.bean.PaiQiBeam;

import java.util.List;

/**
 * @Auther: 付明锟
 * @Date: 2019/5/20 16:54
 * @Description:
 */
public class PaiQiAdapter extends RecyclerView.Adapter<PaiQiAdapter.Holder> {

    private List<PaiQiBeam.ResultBean> data;
    private Context mContext;

    public PaiQiAdapter(List<PaiQiBeam.ResultBean> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.paiqi_item, null);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        String screeningHall = data.get(i).getScreeningHall();
        holder.pai_item_name.setText(screeningHall);

        String beginTime = data.get(i).getBeginTime();
        holder.pai_item_kai.setText(beginTime);

        String endTime = data.get(i).getEndTime();
        holder.pai_item_jie.setText(endTime);

        double price = data.get(i).getPrice();
        holder.pai_item_qian.setText(price+"");
    }

    @Override
    public int getItemCount() {
        Log.e("tab","data"+data);
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private final TextView pai_item_name,pai_item_kai,pai_item_jie,pai_item_qian;

        public Holder(@NonNull View itemView) {
            super(itemView);
            pai_item_name = itemView.findViewById(R.id.pai_item_name);
            pai_item_kai = itemView.findViewById(R.id.pai_item_kai);
            pai_item_jie = itemView.findViewById(R.id.pai_item_jie);
            pai_item_qian = itemView.findViewById(R.id.pai_item_qian);
        }
    }
}
