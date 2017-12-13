package com.junbaor.network;

import com.junbaor.network.model.BaseResult;
import com.junbaor.network.model.RequestData;
import com.junbaor.network.model.ResponseData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@SpringBootApplication
@RestController
public class NetworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetworkApplication.class, args);
    }

    @RequestMapping("android")
    public BaseResult android(@RequestBody RequestData requestData) {
        BaseResult baseResult = new BaseResult();

        try {
            if (requestData == null || requestData.getId() == null || requestData.getName() == null) {
                throw new RuntimeException();
            }

            ResponseData responseData = new ResponseData();
            responseData.setId(requestData.getId());
            responseData.setName(requestData.getName());
            responseData.setBirthday(new Date());

            baseResult.setCode(200);
            baseResult.setMessage("成功");
            baseResult.setData(responseData);
        } catch (Exception e) {
            baseResult.setCode(500);
            baseResult.setMessage("内部异常");
        }

        return baseResult;
    }


}
