package com.omyrobin.httptestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.monkey.network.NetWorkManager;
import com.monkey.network.rxjava.Callback;
import com.omyrobin.httptestapp.api.ApiService;
import com.omyrobin.httptestapp.bean.TestBean;
import com.omyrobin.httptestapp.rxjava.BaseTranformer;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @SuppressLint("CheckResult")
    private void getMethod(){
        final CompositeDisposable compositeDisposable = new CompositeDisposable();
        ApiService apiService = NetWorkManager.getInstance().createService(ApiService.class);
        apiService.getList()
                .compose(new BaseTranformer<TestBean>())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        compositeDisposable.add(disposable);
                    }
                })
                .subscribe(new Callback<TestBean>() {
                    @Override
                    public void onSuccess(TestBean testBean) {

                    }

                    @Override
                    public void onFailure(Throwable throwable) {

                    }
                });

    }
}
