package com.example.findmylocation.DB;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    final static private String URL = "http://eessxuv.dothome.co.kr/signup.php";
    private Map<String, String> map;

    public RegisterRequest(String userID, String userPW, Response.Listener<String>listener) {
        super(Method.POST, URL, listener, null);
        map = new HashMap<>();
        map.put("userID", userID);
        map.put("userPW", userPW);
    }
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
