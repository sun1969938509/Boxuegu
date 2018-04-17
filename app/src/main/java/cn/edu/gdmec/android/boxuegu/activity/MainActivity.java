package cn.edu.gdmec.android.boxuegu.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.gdmec.android.boxuegu.CourseFragment;
import cn.edu.gdmec.android.boxuegu.ExercisesFragment;
import cn.edu.gdmec.android.boxuegu.FragmentMyinfoFragment;
import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.utils.AnalysisUtils;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private RelativeLayout main_body;
    private TextView bottom_bar_text_course;
    private ImageView bottom_bar_image_course;
    private RelativeLayout bottom_bar_course_btn;
    private TextView bottom_bar_text_exercises;
    private ImageView bottom_bar_image_exercises;
    private RelativeLayout bottom_bar_exercises_btn;
    private TextView bottom_bar_text_myinfo;
    private ImageView bottom_bar_image_myinfo;
    private RelativeLayout bottom_bar_myinfo_btn;
    private LinearLayout main_bottom_bar;


//变回原来的样子
 /*private FrameLayout mBodyLayout;
  private LinearLayout mBottomLayout;
  private View mCourseBtn;
  private View mExercisesBtn;
  private View mMyInfoBtn;
  private TextView tv_course;
  private TextView tv_exercise;
  private TextView tv_myInfo;
  private ImageView iv_course;
  private ImageView iv_exercises;
  private ImageView iv_myInfo;
  private TextView tv_back;
  private TextView tv_main_title;
  private RelativeLayout rl_title_bar;
  private MyInfoView mMyInfoView;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initView();
       setMain();
       /* init();
        initBottomBar();
        setListener();
        setInitStatus();*/

    }
    //重新注释

 private void initView() {

        main_body = (RelativeLayout) findViewById(R.id.main_body);
        bottom_bar_text_course = (TextView) findViewById(R.id.bottom_bar_text_course);
        bottom_bar_image_course = (ImageView) findViewById(R.id.bottom_bar_image_course);
        bottom_bar_course_btn = (RelativeLayout) findViewById(R.id.bottom_bar_course_btn);
        bottom_bar_text_exercises = (TextView) findViewById(R.id.bottom_bar_text_exercises);
        bottom_bar_image_exercises = (ImageView) findViewById(R.id.bottom_bar_image_exercises);
        bottom_bar_exercises_btn = (RelativeLayout) findViewById(R.id.bottom_bar_exercises_btn);
        bottom_bar_text_myinfo = (TextView) findViewById(R.id.bottom_bar_text_myinfo);
        bottom_bar_image_myinfo = (ImageView) findViewById(R.id.bottom_bar_image_myinfo);
        bottom_bar_myinfo_btn = (RelativeLayout) findViewById(R.id.bottom_bar_myinfo_btn);
        main_bottom_bar = (LinearLayout) findViewById(R.id.main_bottom_bar);


        bottom_bar_course_btn.setOnClickListener(this);
        bottom_bar_exercises_btn.setOnClickListener(this);
        bottom_bar_myinfo_btn.setOnClickListener(this);
    }
     private void setSelectStatus(int index){
        switch (index){
            case 0:
                bottom_bar_image_course.setImageResource(R.drawable.main_course_icon_selected);
                bottom_bar_text_course.setTextColor(Color.parseColor("#0097F7"));

                bottom_bar_text_exercises.setTextColor(Color.parseColor("#666666"));
                bottom_bar_text_myinfo.setTextColor(Color.parseColor("#666666"));

                bottom_bar_image_exercises.setImageResource(R.drawable.main_exercises_icon);
                bottom_bar_image_myinfo.setImageResource(R.drawable.main_my_icon);
                break;

            case 1:
                bottom_bar_image_exercises.setImageResource(R.drawable.main_exercises_icon_selected);
                bottom_bar_text_exercises.setTextColor(Color.parseColor("#0097F7"));

                bottom_bar_text_course.setTextColor(Color.parseColor("#666666"));
                bottom_bar_text_myinfo.setTextColor(Color.parseColor("#666666"));

                bottom_bar_image_course.setImageResource(R.drawable.main_course_icon);
                bottom_bar_image_myinfo.setImageResource(R.drawable.main_my_icon);
                break;

            case 2:
                bottom_bar_image_myinfo.setImageResource(R.drawable.main_my_icon_selected);
                bottom_bar_text_myinfo.setTextColor(Color.parseColor("#0097F7"));

                bottom_bar_text_course.setTextColor(Color.parseColor("#666666"));
                bottom_bar_text_exercises.setTextColor(Color.parseColor("#666666"));

                bottom_bar_image_exercises.setImageResource(R.drawable.main_exercises_icon);
                bottom_bar_image_course.setImageResource(R.drawable.main_course_icon);
                break;
        }
     }
    @Override
    public void onClick(View v) {
     switch (v.getId()){
         case R.id.bottom_bar_course_btn:
             getSupportFragmentManager().beginTransaction().replace(R.id.main_body,new CourseFragment()).commit();
             setSelectStatus(0);
             break;
         case R.id.bottom_bar_exercises_btn:
             getSupportFragmentManager().beginTransaction().replace(R.id.main_body,new ExercisesFragment()).commit();
             setSelectStatus(1);
             break;
         case R.id.bottom_bar_myinfo_btn:
             getSupportFragmentManager().beginTransaction().replace(R.id.main_body,new FragmentMyinfoFragment()).commit();
             setSelectStatus(2);
             break;
     }
    }
    private void setMain(){
         this.getSupportFragmentManager().beginTransaction().add(R.id.main_body,new FragmentMyinfoFragment()).commit();
         setSelectStatus(2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            boolean isLogin=data.getBooleanExtra("isLogin",false);
            if(isLogin){
                setSelectStatus(0);
            }else{
                setSelectStatus(2);
            }
        }
    }
    protected long exitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK&&event.getAction()==KeyEvent.ACTION_DOWN){
            if(System.currentTimeMillis()-exitTime>2000){
                Toast.makeText(MainActivity.this,"再按一次退出登录",Toast.LENGTH_SHORT).show();
                exitTime=System.currentTimeMillis();
            }else {
                this.finish();
                if (AnalysisUtils.readLoginStatus(this)) {
                    AnalysisUtils.cleanLoginStatus(this);
                }
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    if (data!=null){
           String name = data.getStringExtra("userName");
            Toast.makeText(MainActivity.this,name+"登录成功",Toast.LENGTH_SHORT).show();
          boolean isLogin=data.getBooleanExtra("isLogin",false);
          if(isLogin){
              clearBottomImageState();
              selectDisplayView(0);
          }
          if(mMyInfoView!=null){
              mMyInfoView.setLoginParams(isLogin);
          }
        }

    }*/

    //变回原来的样子
    /*private void  init(){
        tv_back=(TextView)findViewById(R.id.tv_back);
        tv_main_title=(TextView)findViewById(R.id.tv_main_title);
        tv_main_title.setText("博学谷课程");
        rl_title_bar=(RelativeLayout)findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        tv_back.setVisibility(View.GONE);
        initBodyLayout();
    }
     private void initBottomBar(){
        mBottomLayout=(LinearLayout)findViewById(R.id.main_bottom_bar);
        mCourseBtn=findViewById(R.id.bottom_bar_course_btn);
        mExercisesBtn=findViewById(R.id.bottom_bar_exercise_btn);
        mMyInfoBtn=findViewById(R.id.bottom_bar_myinfo_btn);
        tv_course=(TextView)findViewById(R.id.bottom_bar_text_course);
        tv_exercise=(TextView)findViewById(R.id.bottom_bar_text_exercises);
        tv_myInfo=(TextView)findViewById(R.id.bottom_bar_text_myinfo);
        iv_course=(ImageView)findViewById(R.id.bottom_bar_image_course);
        iv_exercises=(ImageView)findViewById(R.id.bottom_bar_image_exercises) ;
        iv_myInfo=(ImageView)findViewById(R.id.bottom_bar_image_myinfo);

     }
       private void initBodyLayout(){
         mBodyLayout=(FrameLayout)findViewById(R.id.main_body);
       }

    @Override
    public void onClick(View v) {
           switch (v.getId()){
               //课程点击事件
               case R.id.bottom_bar_course_btn:
                   clearBottomImageState();
                   selectDisplayView(0);
                   break;
                   //习题点击事件
               case  R.id.bottom_bar_exercise_btn:
                   clearBottomImageState();
                   selectDisplayView(1);
                   break;
               case R.id.bottom_bar_myinfo_btn:
                   clearBottomImageState();
                   selectDisplayView(2);
                   break;
                   default:
                       break;

           }

    }
    private void setListener(){
           for(int i=0;i<mBodyLayout.getChildCount();i++){
               mBodyLayout.getChildAt(i).setOnClickListener(this);
           }
    }
    private void clearBottomImageState(){
        tv_course.setTextColor(Color.parseColor("#666666"));
        tv_exercise.setTextColor(Color.parseColor("#666666"));
        tv_myInfo.setTextColor(Color.parseColor("#666666"));
        iv_course.setImageResource(R.drawable.main_course_icon);
        iv_exercises.setImageResource(R.drawable.main_exercises_icon);
        iv_myInfo.setImageResource(R.drawable.main_my_icon);
        for(int i=0;i<mBottomLayout.getChildCount();i++){
            mBottomLayout.getChildAt(i).setSelected(false);
        }
    }
    private void setSelectedStatus(int index){
      switch (index){
          case 0:
              mCourseBtn.setSelected(true);
              iv_course.setImageResource(R.drawable.main_course_icon_selected);
              tv_course.setTextColor(Color.parseColor("#0097F7"));
              rl_title_bar.setVisibility(View.VISIBLE);
              tv_main_title.setText("博学谷课程");
              break;
          case 1:
              mExercisesBtn.setSelected(true);
              iv_course.setImageResource(R.drawable.main_exercises_icon_selected);
              tv_exercise.setTextColor(Color.parseColor("#0097F7"));
              rl_title_bar.setVisibility(View.VISIBLE);
              tv_main_title.setText("博学谷习题");
              break;
          case 2:
              mMyInfoBtn.setSelected(true);
              iv_myInfo.setImageResource(R.drawable.main_my_icon_selected);
              tv_myInfo.setTextColor(Color.parseColor("#0097F7"));
              rl_title_bar.setVisibility(View.GONE);
              break;

      }
    }
    private void removeAllView(){
        for(int i=0;i<mBodyLayout.getChildCount();i++){
            mBodyLayout.getChildAt(i).setVisibility(View.GONE);
        }
    }
    private void setInitStatus(){
        clearBottomImageState();
        setSelectedStatus(0);
        createView(0);
    }
    private void selectDisplayView(int index){
        removeAllView();
        createView(index);
        setSelectedStatus(index);
    }

    private void createView(int viewIndex){
        switch (viewIndex){
            case 0:
                //课程界面
                break;
            case 1:
                //习题界面
                break;
            case 2:
                //我的 界面
                if(mMyInfoView==null){
                    mMyInfoView=new MyInfoView(this);
                    mBodyLayout.addView(mMyInfoView.getView());
                }else{
                    mMyInfoView.getView();
                }
                mMyInfoView.showView();
                break;

        }
    }
    protected long exitTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_BACK&&event.getAction()==KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime)>2000){
            Toast.makeText(MainActivity.this,"再按一次退出博学谷",Toast.LENGTH_SHORT).show();
            exitTime=System.currentTimeMillis();
            }else{
               MainActivity.this.finish();
               if(readLoginStatus()){
                   clearLoginStatus();
               }
               System.exit(0);
        }
        return true;
    }
     return super.onKeyDown(keyCode,event);
}
   private boolean readLoginStatus(){
       SharedPreferences sp=getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
       boolean isLogin=sp.getBoolean("isLogin",false);
       return isLogin;

   }
   private void clearLoginStatus(){
       SharedPreferences sp=getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
       SharedPreferences.Editor editor=sp.edit();
       editor.putBoolean("isLogin",false);
       editor.putString("loginUsername","");
       editor.commit();
   }*/
}

