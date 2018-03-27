package cn.edu.gdmec.android.boxuegu;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.gdmec.android.boxuegu.activity.LoginActivity;
import cn.edu.gdmec.android.boxuegu.activity.MainActivity;

public class Main2Activity extends AppCompatActivity {
  private TextView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
     String userName = getIntent().getStringExtra("userName");
        back=findViewById(R.id.tv_back);
        back.setText(userName+"登录成功");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("name","dddd");
                setResult(RESULT_OK,intent);
                Main2Activity.this.finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null) {
                String name = data.getStringExtra("name");
                Toast.makeText(Main2Activity.this,name+"登录成功",Toast.LENGTH_SHORT).show();

            }
        }

    }
}
