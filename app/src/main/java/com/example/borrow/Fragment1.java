package com.example.borrow;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
    //위젯 초기화
    ViewGroup v;

    private ListView listview;
    private ListViewAdapter adapter;

    public TextView student_name;
    public TextView student_id;
    public TextView item_name;
    public TextView item_rental_date;
    public TextView item_return_date;

    int i=3;
    String result;
    String result2;

    String a,b,c,d,e;
    String a2,b2,c2,d2,e2;

    String[] array;
    String[] array2;
    String[] item;
    String[] item2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = (ViewGroup) inflater.inflate(R.layout.fragment1, container, false);

        //Adapter 생성
        adapter = new ListViewAdapter();

        //리스트뷰 참조 및 Adapter
        listview = (ListView) v.findViewById(R.id.item_list_view);
        listview.setAdapter(adapter);

        //텍스트뷰 초기화
        student_id = (TextView) v.findViewById(R.id.student_id);
        student_name = (TextView) v.findViewById(R.id.student_name);
        item_name = (TextView) v.findViewById(R.id.item_name);
        item_rental_date = (TextView) v.findViewById(R.id.item_rental_date);
        item_return_date = (TextView) v.findViewById(R.id.item_return_date);

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
                            if (jsonResponse.getString("result").isEmpty() == false) {
                                result = jsonResponse.getString("result");
                                result2 = jsonResponse.getString("result2");
                                Log.d("result2", result2);
                                //=============사용자 이름, 학번 출력=============//
                                array = result.split(",");
                                a = array[0];
                                b = array[1];
                                a2 = a.substring(14, a.length() - 1);
                                b2 = b.substring(17, b.length() - 3);
                                student_id.setText(b2);
                                student_name.setText(a2);
                                //=============대여물품정보 출력=============//
                                array2 = result2.split(",");
                                c = array2[0];
                                d = array2[1];
                                e = array2[2];
                                c2 = c.substring(14, c.length() - 1);
                                d2 = d.substring(18, d.length() - 1);
                                e2 = e.substring(18, e.length() - 2);
                                adapter.addItem(c2, d2, e2);  //listView에 데이터 추가
                                adapter.notifyDataSetChanged(); //listView 갱신
                                //item, item2 초기화(array2의 length만큼)
                                item = array2;
                                item2 = array2;
                                //=============대여물품이 2개 이상 일 때=======//
                                for(i=3;i<array2.length;i=i+3) {
                                    item[i] = array2[i];
                                    item2[i] = item[i].substring(13, item[i].length() - 1);
                                    item[i + 1] = array2[i + 1];
                                    item2[i + 1] = item[i + 1].substring(18, item[i + 1].length() - 1);
                                    item[i + 2] = array2[i + 2];
                                    item2[i + 2] = item[i + 2].substring(18, item[i + 2].length() - 2);
                                    adapter.addItem(item[i], item[i + 1], item[i + 2]);
                                    adapter.notifyDataSetChanged();
                                }
                            } else {
                                result = jsonResponse.getString("result2");
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