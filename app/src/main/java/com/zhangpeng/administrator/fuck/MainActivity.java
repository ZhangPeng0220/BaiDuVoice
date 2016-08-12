package com.zhangpeng.administrator.fuck;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.baidu.voicerecognition.android.ui.BaiduASRDigitalDialog;
import com.baidu.voicerecognition.android.ui.DialogRecognitionListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //开始按钮
    private Button BtnStart,BtnOpen;
    //文本框
    private EditText InputBox;
    //百度语音识别对话框
    private BaiduASRDigitalDialog mDialog=null;
    private DialogRecognitionListener mDialogListener=null;
    //应用授权信息 ，这里使用了官方SDK中的参数，如果需要，请自行申请，并修改为自己的授权信息
    private String API_KEY="8MAxI5o7VjKSZOKeBzS4XtxO";
    private String SECRET_KEY="Ge5GXVdGQpaxOmLzc8fOM8309ATCz9Ha";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        if (mDialog == null) {
            if (mDialog != null) {
                mDialog.dismiss();
            }
            Bundle params = new Bundle();
            //设置API_KEY, SECRET_KEY
            params.putString(BaiduASRDigitalDialog.PARAM_API_KEY, API_KEY);
            params.putString(BaiduASRDigitalDialog.PARAM_SECRET_KEY, SECRET_KEY);
            //设置语音识别对话框为蓝色高亮主题
            params.putInt(BaiduASRDigitalDialog.PARAM_DIALOG_THEME, BaiduASRDigitalDialog.THEME_BLUE_LIGHTBG);
            //实例化百度语音识别对话框
            mDialog = new BaiduASRDigitalDialog(this, params);
            //设置百度语音识别回调接口
            mDialogListener=new DialogRecognitionListener()
            {

                @Override
                public void onResults(Bundle mResults)
                {
                    ArrayList<String> rs = mResults != null ? mResults.getStringArrayList(RESULTS_RECOGNITION) : null;
                    if (rs != null && rs.size() > 0) {
                        InputBox.setText(rs.get(0));
                    }

                }

            };
            mDialog.setDialogRecognitionListener(mDialogListener);
        }
        //设置语音识别模式为输入模式
        mDialog.setSpeechMode(BaiduASRDigitalDialog.SPEECH_MODE_INPUT);
        //禁用语义识别
        mDialog.getParams().putBoolean(BaiduASRDigitalDialog.PARAM_NLU_ENABLE, false);

        //界面元素
        BtnStart=(Button)findViewById(R.id.BtnStart);
        InputBox=(EditText)findViewById(R.id.InputBox);
        BtnStart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mDialog.show();
            }
        });


    }


}
