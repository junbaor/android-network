package com.junbaor.network.extend;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Date;

/**
 * Created by junbaor on 2017/12/13.
 *
 * 将后台返回的时间戳转成 java.util.Date
 * 参见: https://stackoverflow.com/questions/41348055/gson-dateformat-to-parse-output-unix-timestamps
 */
public class GsonDateTypeAdapter extends TypeAdapter<Date> {

    @Override
    public void write(JsonWriter out, Date value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            out.value(value.getTime() / 1000);
        }
    }

    @Override
    public Date read(JsonReader in) throws IOException {
        if (in != null) {
            return new Date(in.nextLong() / 1000);
        } else {
            return null;
        }
    }
}