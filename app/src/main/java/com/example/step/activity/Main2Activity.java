package com.example.step.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.step.R;
import com.example.step.service.StepService;

public class Main2Activity extends AppCompatActivity {
    //Service端的Messenger对象
    private Messenger mServiceMessenger;

    //Activity端的Messenger对象
    private Messenger mActivityMessenger;
    TextView tv_step;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

                if (msg.what == 0x12) {
                   int steps = msg.getData().getInt("steps");
                    tv_step.setText("今日步数为："+steps);
                }

            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv_step = findViewById(R.id.tv_step);
    }
    public void onClick(View view) {



            Intent intent = new Intent(this,StepService.class);
        bindService(intent,connection,BIND_AUTO_CREATE);
    }
    ServiceConnection connection = new ServiceConnection(){

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Toast.makeText(Main2Activity.this, "连接成功", Toast.LENGTH_SHORT).show();


            mServiceMessenger = new Messenger(iBinder);






        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Toast.makeText(Main2Activity.this, "连接失败", Toast.LENGTH_SHORT).show();
        }
    };

    public void show(View view) {


        //Activity端的Messenger
        if(mActivityMessenger == null) {
            mActivityMessenger = new Messenger(handler);
        }


        //创建消息
        Message message = Message.obtain();
        message.what = 11;
        message.arg1 = 2016;
        message.arg2 = 1;

        //设定消息要回应的Messenger
        message.replyTo = mActivityMessenger;

        try {
            //通过ServiceMessenger将消息发送到Service中的Handler
            mServiceMessenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
