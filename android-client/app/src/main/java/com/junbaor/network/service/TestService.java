package com.junbaor.network.service;

import com.junbaor.network.model.RequestData;
import com.junbaor.network.model.ResponseData;
import com.junbaor.network.model.BaseResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by junbaor on 2017/12/13.
 */

public interface TestService {

    @POST("android")
    Call<BaseResult<ResponseData>> getTestResult(@Body RequestData requestData);

}
