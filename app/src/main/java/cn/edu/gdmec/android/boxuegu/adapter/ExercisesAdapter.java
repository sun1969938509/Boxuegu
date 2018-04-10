package cn.edu.gdmec.android.boxuegu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.bean.ExercisesBean;

/**
 * Created by apple on 18/4/10.
 */

public class ExercisesAdapter extends BaseAdapter{
    private Context mContext;
    private List<ExercisesBean> eb1;
    public ExercisesAdapter(Context context){
        this.mContext=context;
    }
    public void setData(List<ExercisesBean> eb1){
        this.eb1=eb1;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return eb1==null ? 0 : eb1.size();
    }

    @Override
    public Object getItem(int i) {
        return eb1==null ? null : eb1.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder vh;
        if(view==null){
            vh=new ViewHolder();
            view= LayoutInflater.from(mContext).inflate(R.layout.exercises_list_item,null);
            vh.title=(TextView)view.findViewById(R.id.tv_title);
            vh.content=(TextView)view.findViewById(R.id.tv_content);
            vh.order=(TextView)view.findViewById(R.id.tv_order);
            view.setTag(vh);
        }else{
            vh=(ViewHolder)view.getTag();
        }
         final ExercisesBean bean= (ExercisesBean) getItem(i);
        if(bean!=null){
            vh.order.setText(i+1+"");
            vh.title.setText(bean.title);
            vh.content.setText(bean.content);
            vh.order.setBackgroundResource(bean.background);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bean==null){
                    return;
                    //跳转到习题详情页面
                }
            }
        });
        return view;
    }
    class ViewHolder{
        public TextView title,content;
        public TextView order;
    }
}
