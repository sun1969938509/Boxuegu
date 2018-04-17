package cn.edu.gdmec.android.boxuegu;





import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.boxuegu.adapter.ExercisesAdapter;
import cn.edu.gdmec.android.boxuegu.bean.ExercisesBean;
import cn.edu.gdmec.android.boxuegu.view.ExercisesView;


public class ExercisesFragment extends Fragment {
    private ListView lv_list;
    private ExercisesAdapter adapter;
    private List<ExercisesBean> ebl;
    private Activity mContext;
    private LayoutInflater mInflater;
    private View mCurrentView;
    //private ExercisesView mExercisesView;
    private ExercisesFragment mExercisesFragment;
    private FrameLayout mBodyLayout;
    public ExercisesFragment(){
        mInflater=LayoutInflater.from(mContext);
    }
    private void createView(){
        if(mExercisesFragment==null){
            mExercisesFragment=new ExercisesFragment();
            mBodyLayout.addView(mExercisesFragment.getView());
        }else{
            mExercisesFragment.getView();
        }
        mExercisesFragment.showView();
        initView();
    }
    public void initView(){
        mCurrentView=mInflater.inflate(R.layout.main_view_exercises,null);
        lv_list=(ListView)mCurrentView.findViewById(R.id.lv_list);
        adapter=new ExercisesAdapter(mContext);
        initData();
        adapter.setData(ebl);
        lv_list.setAdapter(adapter);
    }
    private void initData(){
        ebl=new ArrayList<ExercisesBean>();
        for(int i=0;i<10;i++){
            ExercisesBean bean=new ExercisesBean();
            bean.id=(i+1);
            switch (i){
                case 0:
                    bean.title="第1章Android基础入门";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_1);
                    break;
                case 1:
                    bean.title="第2章Android UI开发";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_2);
                    break;
                case 2:
                    bean.title="第3章Activity开发";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_3);
                    break;
                case 3:
                    bean.title="第4章 数据储存";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_4);
                    break;
                case 4:
                    bean.title="第5章 SQLite数据库";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_1);
                    break;
                case 5:
                    bean.title="第6章 广播接受者";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_2);
                    break;
                case 6:
                    bean.title="第6章 服务";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_3);
                    break;
                case 7:
                    bean.title="第8章 内容提供者";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_4);
                    break;
                case 8:
                    bean.title="第9章 网络编程";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_1);
                    break;
                case 9:
                    bean.title="第10章 高级编程";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_2);
                    break;
                default:
                    break;
            }
            ebl.add(bean);
        }
    }
    public View getView(){
        if(mCurrentView==null){
            createView();
        }
        return mCurrentView;
    }
    public void showView(){
        if(mCurrentView==null){
            createView();
        }
        mCurrentView.setVisibility(View.VISIBLE);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       return inflater.inflate(R.layout.fragment_exercises, null);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
       super.onViewCreated(view, savedInstanceState);


    }


}
