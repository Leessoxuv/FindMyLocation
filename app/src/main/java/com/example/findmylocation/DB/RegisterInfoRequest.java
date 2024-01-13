package com.example.findmylocation.DB;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class RegisterInfoRequest extends StringRequest {
    final static private String URL = "http://eessxuv.dothome.co.kr/userinfo.php";
    private Map<String, String> map;
    private Map<String, Date> map2;

    public RegisterInfoRequest(String userName, String userGender, Date userBirth, String userNumber, Response.Listener<String>listener) {
        super(Method.POST, URL, listener, null);
        map = new HashMap<>();
        map2 = new HashMap<>();
        map.put("userName", userName);
        map.put("userGender", userGender);
        map2.put("userBirth", userBirth);
        map.put("userNumber", userNumber);

    }
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
