package cn.edu.gdmec.android.boxuegu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.bean.ExercisesBean;
import cn.edu.gdmec.android.boxuegu.utils.AnalysisUtils;

/**
 * Created by Administrator on 2018/4/17.
 */

public class ExercisesDetailAdapter extends BaseAdapter {
    private Context mContext;
    private List<ExercisesBean> ebl;
    private OnSelectListener onSelectListener;
    public ExercisesDetailAdapter(Context context, OnSelectListener onSelectListener){
        this.mContext=context;
        this.onSelectListener=onSelectListener;
    }
    public void setData(List<ExercisesBean> ebl){
        this.ebl=ebl;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return ebl==null?0:ebl.size();
    }

    @Override
    public ExercisesBean getItem(int position) {
        return ebl==null ? null : ebl.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
   private ArrayList<String> selectedPosition=new ArrayList<String>();
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if(convertView==null){
            vh=new ViewHolder();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.exercises_datail_list_item,null);
            vh.subject=(TextView)convertView.findViewById(R.id.tv_subject);
            vh.tv_a=(TextView)convertView.findViewById(R.id.tv_a);
            vh.tv_b=(TextView)convertView.findViewById(R.id.tv_b);
            vh.tv_c=(TextView)convertView.findViewById(R.id.tv_c);
            vh.tv_d=(TextView)convertView.findViewById(R.id.tv_d);
            vh.iv_a=(ImageView) convertView.findViewById(R.id.iv_a);
            vh.iv_b=(ImageView) convertView.findViewById(R.id.iv_b);
            vh.iv_c=(ImageView) convertView.findViewById(R.id.iv_c);
            vh.iv_d=(ImageView) convertView.findViewById(R.id.iv_d);
            convertView.setTag(vh);
        }else{
            vh=(ViewHolder)convertView.getTag();
        }
        final ExercisesBean bean=getItem(position);
        if(bean!=null){
            vh.subject.setText(bean.subject);
            vh.tv_a.setText(bean.a);
            vh.tv_b.setText(bean.b);
            vh.tv_c.setText(bean.c);
            vh.tv_d.setText(bean.d);
        }
        if (!selectedPosition.contains("" + position)) {
            vh.iv_a.setImageResource(R.drawable.exercises_a);
            vh.iv_b.setImageResource(R.drawable.exercises_b);
            vh.iv_c.setImageResource(R.drawable.exercises_c);
            vh.iv_d.setImageResource(R.drawable.exercises_d);
           // AnalysisUtils.setABCDEnable(true,vh.iv_a,vh.iv_b,vh.iv_c,vh.iv_d);

        }
        return null;
    }
    class ViewHolder{
        public TextView subject,tv_a,tv_b,tv_c,tv_d;
        public ImageView iv_a,iv_b,iv_c,iv_d;
    }
    public interface OnSelectListener{
        void onSelectA(int position,ImageView iv_a,ImageView iv_b,ImageView iv_c,ImageView iv_d);
        void onSelectB(int position,ImageView iv_a,ImageView iv_b,ImageView iv_c,ImageView iv_d);
        void onSelectC(int position,ImageView iv_a,ImageView iv_b,ImageView iv_c,ImageView iv_d);
        void onSelectD(int position,ImageView iv_a,ImageView iv_b,ImageView iv_c,ImageView iv_d);
    }
}
