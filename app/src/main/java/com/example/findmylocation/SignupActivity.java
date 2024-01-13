package com.example.findmylocation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.findmylocation.DB.RegisterRequest;
import com.example.findmylocation.DB.ValidateRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class SignupActivity extends AppCompatActivity {
    EditText Et_Joinemail, Et_Joinpw, Et_Repw;
    Button Btn_echeck, Btn_pcheck, Btn_joinSuceess;
    private String Jemail;
    private boolean validate = false;
    private AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Et_Joinemail = (EditText) findViewById(R.id.Et_Joinemail);
        Et_Joinpw = (EditText) findViewById(R.id.Et_Joinpw);
        Et_Repw = (EditText) findViewById(R.id.Et_Repw);
        Btn_echeck = (Button) findViewById(R.id.Btn_echeck);
        Btn_pcheck = (Button) findViewById(R.id.Btn_pcheck);
        Btn_joinSuceess = (Button) findViewById(R.id.Btn_JoinSucceess);

        Btn_echeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ckemail = Et_Joinemail.getText().toString();
                if(validate) {
                    return;
                }
                if(ckemail.equals("")) {
                    Toast.makeText(getApplicationContext(),"아이디는 빈 칸일 수 없습니다.", Toast.LENGTH_LONG).show();
                }
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Toast.makeText(getApplicationContext(), "사용 가능한 이메일 입니다.", Toast.LENGTH_LONG).show();
                                validate = true;
                            } else {
                                Toast.makeText(getApplicationContext(), "아이디가 이미 존재합니다.", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                ValidateRequest validateRequest = new ValidateRequest(ckemail, responseListener);
                RequestQueue queue = Volley.newRequestQueue(SignupActivity.this);
                queue.add(validateRequest);
            }
        });


        Btn_pcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Et_Joinpw.getText().toString().equals(Et_Repw.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "비밀번호가 일치합니다.", Toast.LENGTH_LONG).show();
                } else if (Et_Joinpw.getText().toString() != Et_Repw.getText().toString()) {
                    Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                } else if (Et_Repw.getText().toString() == null) {
                    Toast.makeText(getApplicationContext(), "동일한 비밀번호를 입력하세요.", Toast.LENGTH_LONG).show();
                }
            }
        });
        Btn_joinSuceess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = Et_Joinemail.getText().toString();
                final String userPW = Et_Joinpw.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject josnObject = new JSONObject(response);
                            boolean success = josnObject.getBoolean("success");
                            if(success) {
                                Toast.makeText(getApplicationContext(),"회원가입이 되었습니다.", Toast.LENGTH_LONG).show();
                                Intent intent1 = new Intent(SignupActivity.this, LoginActivity.class);
                                startActivity(intent1);
                            } else {
                                Toast.makeText(getApplicationContext(),"회원가입이 실패하였습니다.", Toast.LENGTH_LONG).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(userID, userPW, responseListener);
                RequestQueue queue = Volley.newRequestQueue(SignupActivity.this);
                queue.add(registerRequest);
            }
        });
    }

}
