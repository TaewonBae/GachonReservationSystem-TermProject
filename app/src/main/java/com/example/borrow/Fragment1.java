package com.example.borrow;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
//사용자 대여 신청 화면

public class Fragment1 extends Fragment {
    ViewGroup v;
    public TextView t_name;
    public TextView t_number;
    String total;
    String total2;
    String a;
    String b;
    String c;
    String d;
    String e;
    String a2;
    String b2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = (ViewGroup) inflater.inflate(R.layout.fragment1, container, false);

        //텍스트뷰 초기화
        t_number = (TextView) v.findViewById(R.id.number);
        t_name = (TextView) v.findViewById(R.id.name);

        String url = "http://qoxodnjs.cafe24.com/UserInfo.php";
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("userID", ((LoginActivity) LoginActivity.context_main).userID);
        parameters.put("itemName", "우산");

        //휴대폰충전기
        //우산
        //마우스
        //노트북충전기

        final JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(parameters),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {   //밑에서 Request가 보내지고 결과로 온 Reponse가 JsonResponse를 통해 다뤄진다.
                            JSONObject jsonResponse = response;
                            //result = [{"userID":"bae","userStudentID":""}]
                            if (jsonResponse.getString("result").isEmpty() == false)
                            {
                                total = jsonResponse.getString("result");
                                total2 = jsonResponse.getString("result2");
                                String[] array = total.split(",");
                                a = array[0];
                                b = array[1];
                                Log.d("total",a);
                                Log.d("total",b);
                                a2 = a.substring(14,a.length()-1);
                                b2 = b.substring(17,b.length()-3);
                                Log.d("total",a2);
                                Log.d("total",b2);
                                t_number.setText(b2);
                                t_name.setText(a2);

                                String[] array2 = total2.split(",");
                                c = array2[0];
                                d = array2[1];
                                e = array2[2];
                            }
                            else
                            {
                                total = jsonResponse.getString("result2");
                            }


                        } catch (Exception e)//예외처리
                        {
                            e.printStackTrace();//간단하게 예외처리함
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(((LoginActivity) MainActivity.context_main), "DB 연동 에러", Toast.LENGTH_LONG).show();
                    }
                }) {

        };
        //실제로 로그인을 보낼 수 있는 Request : userID, password를 받아 리스너를 보냄
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(jsObjRequest);

        return v;
    }
}