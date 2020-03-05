package com.cjx.x5_webview;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.tencent.smtt.sdk.TbsReaderView;

import java.io.File;
import java.util.List;



public class FileActivity extends Activity {


    private TbsReaderView mTbsReaderView;

    private String filePath = "";
    private String title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayfile);
        initTbsReaderView();
        Intent intent = getIntent();
        filePath = intent.getStringExtra("filepath");
        ActionBar actionBar = getActionBar();
        try {
            title = filePath.substring(filePath.lastIndexOf("/") + 1);
        } catch (Exception e) {
        }
        actionBar.setTitle(title);
        displayFile(filePath);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTbsReaderView.onStop();
    }

    RelativeLayout rootRl;

    private void initTbsReaderView() {
        mTbsReaderView = new TbsReaderView(FileActivity.this, new TbsReaderView.ReaderCallback() {
            @Override
            public void onCallBackAction(Integer integer, Object o, Object o1) {
            }
        });
        rootRl = (RelativeLayout) findViewById(R.id.root_layout);
        rootRl.addView(mTbsReaderView, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    private void displayFile(String filePath) {
        boolean isCan = false;
        isCan = this.mTbsReaderView.preOpen(parseFormat(filePath), false);
        Log.d("FileActivity2222", isCan + "");
        if (isCan) {
            String bsReaderTemp = "/storage/emulated/0/TbsReaderTemp";
            File bsReaderTempFile = new File(bsReaderTemp);

            if (!bsReaderTempFile.exists()) {
                boolean mkdir = bsReaderTempFile.mkdir();
                if (!mkdir) {
                    Log.e("TAG", "创建/storage/emulated/0/TbsReaderTemp失败！！！！！");
                }
            }
            Bundle bundle = new Bundle();
            bundle.putString("filePath", filePath);
            bundle.putString("tempPath", Environment.getExternalStorageDirectory().toString() + "/" + "TbsReaderTemp");
            mTbsReaderView.openFile(bundle);
        }

    }

    private String parseFormat(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

}