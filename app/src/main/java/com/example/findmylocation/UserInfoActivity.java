package com.example.findmylocation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.findmylocation.DB.RegisterInfoRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;

public class UserInfoActivity extends AppCompatActivity {
    private static final int REQUEST_ACCESS_FINE_LOCATION = 1000;
    TextView Viewid;
    EditText Et_Gender, Et_Name, Et_Birth, Et_Number;
    Button Btn_Save, Btn_Back;
    int textlength;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        Viewid = (TextView) findViewById(R.id.Viewid);
        Et_Gender = (EditText) findViewById(R.id.Et_Gender);
        Et_Name = (EditText) findViewById(R.id.Et_Name);
        Et_Birth = (EditText) findViewById(R.id.Et_Birth);
        Et_Number = (EditText) findViewById(R.id.Et_Number);
        Btn_Save = (Button) findViewById(R.id.Btn_Save);
        Btn_Back = (Button) findViewById(R.id.Btn_Back);

        showResult();

        Et_Birth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (Et_Birth.isFocusable() && !s.toString().equals("")) {
                    try {
                        textlength = Et_Birth.getText().toString().length();
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        return;
                    }

                    if (textlength == 4 && before != 1) {

                        Et_Birth.setText(Et_Birth.getText().toString() + "-");
                        Et_Birth.setSelection(Et_Birth.getText().length());

                    } else if (textlength == 7 && before != 1) {

                        Et_Birth.setText(Et_Birth.getText().toString() + "-");
                        Et_Birth.setSelection(Et_Birth.getText().length());

                    } else if (textlength == 5 && !Et_Birth.getText().toString().contains("-")) {

                        Et_Birth.setText(Et_Birth.getText().toString().substring(0, 4) + "-" + Et_Birth.getText().toString().substring(4));
                        Et_Birth.setSelection(Et_Birth.getText().length());

                    } else if (textlength == 8 && !Et_Birth.getText().toString().substring(7, 8).equals("-")) {

                        Et_Birth.setText(Et_Birth.getText().toString().substring(0, 7) + "-" + Et_Birth.getText().toString().substring(7));
                        Et_Birth.setSelection(Et_Birth.getText().length());

                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        Btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(UserInfoActivity.this, MainActivity.class);
                startActivity(intent4);
            }
        });
        Btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = Et_Name.getText().toString();
                Date userBirth = Date.valueOf(Et_Birth.getText().toString());
                String userGender = Et_Gender.getText().toString();
                String userNumber = Et_Number.getText().toString();
                String getID = Viewid.getText().toString();

                Response.Listener<String> responseListner = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jasonObject = new JSONObject(response);
                            boolean success = jasonObject.getBoolean("success");
                            String userID = jasonObject.getString("userID");
                            if (getID.equals(userID)) {
                                if (success) {
                                    Toast.makeText(getApplicationContext(), "회원 정보가 저장되었습니다.", Toast.LENGTH_SHORT).show();
                                    Intent intent3 = new Intent(UserInfoActivity.this, MainActivity.class);
                                    startActivity(intent3);
                                } else {
                                    Toast.makeText(getApplicationContext(), "회원 정보가 저장되지않았습니다. ", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                RegisterInfoRequest infoRequest = new RegisterInfoRequest(userName, userGender, userBirth, userNumber, responseListner);
                RequestQueue queue = Volley.newRequestQueue(UserInfoActivity.this);
                queue.add(infoRequest);
            }
        });
    }

    private void showResult( ) {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String userId = bundle.getString("userID");
        Viewid.setText(userId);
    }

}