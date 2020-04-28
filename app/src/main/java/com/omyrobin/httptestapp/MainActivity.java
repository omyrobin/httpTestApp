package com.omyrobin.httptestapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.monkey.network.NetWorkManager;
import com.monkey.network.rxjava.Callback;
import com.monkey.network.rxjava.tranformer.DataTranformer;
import com.omyrobin.httptestapp.api.ApiService;
import com.omyrobin.httptestapp.bean.TestBean;
import com.omyrobin.httptestapp.rxjava.BaseTranformer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getMethod();
    }


    @SuppressLint("CheckResult")
    private void getMethod(){
        ApiService apiService = NetWorkManager.getInstance().createService(ApiService.class);

        apiService.getList()
                .compose(new BaseTranformer<TestBean>())
                .subscribe(new Callback<TestBean>() {
                    @Override
                    public void onSuccess(TestBean testBean) {

                    }

                    @Override
                    public void onFailure(Throwable throwable) {

                    }
                });


        apiService.postList("123")
                .compose(new DataTranformer<String>())
                .subscribe(new Callback<String>() {
                    @Override
                    public void onSuccess(String s) {

                    }

                    @Override
                    public void onFailure(Throwable throwable) {

                    }
                });

    }
}
