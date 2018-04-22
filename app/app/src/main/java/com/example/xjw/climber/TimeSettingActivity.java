package com.example.xjw.climber;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.ddz.floatingactionbutton.FloatingActionButton;
import com.ddz.floatingactionbutton.FloatingActionMenu;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;

import java.util.ArrayList;

public class TimeSettingActivity extends AppCompatActivity {
    //背景图片
    private ImageView imageView;
    private WheelView wheelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_setting);
        // createScaleImage();
        createImageView(R.mipmap.mountain1); //设置背景图片
        createWheelView(); //设置时间滚轮
        createButton();//设置开始专注按钮
        createFAButton();//设置右上角浮动按钮
        //下面是自定义一个任务栏，取代原先自带的任务栏
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    //任务栏右侧的菜单按钮
    /*public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }
    //为菜单项设置时间响应
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.setting:
                Toast.makeText(this,"setting",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }*/
    private void createFAButton() {
        FloatingActionButton fabSetting = findViewById(R.id.fab_setting);
        FloatingActionButton fabHistory = findViewById(R.id.fab_history);
        FloatingActionButton fabAlbum = findViewById(R.id.fab_album);
        fabSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TimeSettingActivity.this, "Setting", Toast.LENGTH_SHORT).show();
            }
        });
        fabHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TimeSettingActivity.this, "History", Toast.LENGTH_SHORT).show();
            }
        });
        fabAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TimeSettingActivity.this, "Album", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createButton() {
        Button startbutton = findViewById(R.id.button);
        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //data即当前选中的滚轮数据
                String data = (String) wheelView.getSelectionItem();
                //下面冬冬可自行切换至下一activity并传入data数据
                Intent intent = new Intent(TimeSettingActivity.this, ClimbingActivity.class);
                intent.putExtra("time", data);
                startActivity(intent);

            }
        });
    }

    //暂时弃用的图片缩放
    /*private void createScaleImage(){
            scaleImage=(ScaleImage)findViewById(R.id.scaleimage);
            scaleImage.startScale(R.mipmap.mountain1);

     }*/
    private void createImageView(int num) {
        imageView = findViewById(R.id.imageview);
        imageView.setImageResource(num);
    }

    //设置时间滚轮
    private void createWheelView() {
        wheelView = findViewById(R.id.wheelView);
        //设置数据适配器
        wheelView.setWheelAdapter(new ArrayWheelAdapter(this));
        //传入数据
        wheelView.setWheelData(createMinutes());
        wheelView.setWheelClickable(true);
        //设置滚轮数据循环显示
        wheelView.setLoop(true);
        //设置滚轮显示3行
        wheelView.setWheelSize(3);
        wheelView.setSkin(WheelView.Skin.Holo);
        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
        //设置选中的文字颜色与大小
        style.selectedTextColor = Color.parseColor("#0288ce");
        style.selectedTextSize = 25;
        //设置背景颜色为透明
        style.backgroundColor = Color.alpha(0);
        style.textColor = Color.GRAY;

        wheelView.setStyle(style);
        //设置滚轮右侧的指示文字及其格式
        wheelView.setExtraText("分钟", Color.parseColor("#0288ce"), 60, 120);
        //设置滚轮滑动停止时的事件，即更换图片
        wheelView.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectedListener() {
            @Override
            public void onItemSelected(int i, Object o) {
                //必须先清零，以避免内存过度占用问题，艹，老子为了这一行代码浪费了一中午
                imageView.setImageResource(0);
                //切换图片
                imageView.setImageResource(R.mipmap.mountain1 + i);


            }
        });
    }

    //为滚轮设置数据
    private ArrayList<String> createMinutes() {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 5; i <= 25; i += 5) {
            if (i == 5)
                list.add("0" + i);
            else {
                list.add("" + i);
            }
        }
        return list;
    }


}
