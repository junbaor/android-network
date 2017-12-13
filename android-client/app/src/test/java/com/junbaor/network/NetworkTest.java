package com.junbaor.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.junbaor.network.extend.GsonDateTypeAdapter;
import com.junbaor.network.model.RequestData;
import com.junbaor.network.model.ResponseData;
import com.junbaor.network.okhttp3.logging.HttpLoggingInterceptor;
import com.junbaor.network.service.TestService;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class NetworkTest {

    @Test
    public void addition_isCorrect() throws Exception {

        // OkHttp 日志拦截器
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                System.out.println(message);
            }
        });
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // OkHttp 客户端
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(2, TimeUnit.MILLISECONDS)
                .addNetworkInterceptor(logInterceptor)
                .build();

        // 把后台返回的时间戳转成 java.util.Date
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonDateTypeAdapter()).create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://127.0.0.1:8080")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        //请求参数
        RequestData requestData = new RequestData();
        requestData.setId(1);
        requestData.setName("张三");

        TestService testService = retrofit.create(TestService.class);
        Call testServiceReslut = testService.getTestResult(requestData);

        testServiceReslut.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                System.out.println("响应成功, 数据:" + response.body());
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                System.out.println("响应失败, 原因:" + t.getMessage());
            }
        });

        Thread.sleep(60 * 1000);
    }
}