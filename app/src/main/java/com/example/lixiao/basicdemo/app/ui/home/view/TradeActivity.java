package com.example.lixiao.basicdemo.app.ui.home.view;

import android.os.Bundle;
import android.view.View;

import com.example.lixiao.basicdemo.R;
import com.example.lixiao.basicdemo.app.ui.base.activity.BaseActivity;
import com.example.lixiao.basicdemo.app.widget.TradeLayout.TradeLayout;

import androidx.annotation.Nullable;

public class TradeActivity extends BaseActivity {
    private TradeLayout tradeLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade);
        tradeLayout = findViewById(R.id.trade);
        findViewById(R.id.btn)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tradeLayout.start();
                    }
                });
    }

    @Override
    protected void onStop() {
        tradeLayout.stop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
