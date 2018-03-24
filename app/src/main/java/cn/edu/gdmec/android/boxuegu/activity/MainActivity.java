package cn.edu.gdmec.android.boxuegu.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.gdmec.android.boxuegu.R;

public class MainActivity extends AppCompatActivity {

    TextView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String userName = getIntent().getStringExtra("userName");
        back=findViewById(R.id.back);
        back.setText(userName+"登录成功");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("name","dddd");
                setResult(RESULT_OK,intent);
                MainActivity.this.finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    if (data!=null){
            String name = data.getStringExtra("userName");
            Toast.makeText(MainActivity.this,name+"登录成功",Toast.LENGTH_SHORT).show();
        }

    }
}
