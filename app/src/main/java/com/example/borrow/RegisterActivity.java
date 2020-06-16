package com.example.borrow;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    //res>values 폴더의 arrays.xml spinner 아이템 값들을 받아오기 위해 ArrayAdapter를 선언해준다.
    private ArrayAdapter adapter;
    private Spinner spinner;

    public String userID;
    private String userPassword;
    private String userGender;
    private String userMajor;
    private String userEmail;
    private String userStudentID;
    private String userName;
    private String userPhone;

    private AlertDialog dialog;
    private boolean validate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //스피너 findViewById로 위젯 불러옴
        spinner = (Spinner) findViewById(R.id.majorSpinner);
        //adapter는 arrays.xml에 입력했던 학과들을 Resource에서 받아온다.
        adapter = ArrayAdapter.createFromResource(this, R.array.major, android.R.layout.simple_spinner_dropdown_item);
        //스피너에 어뎁터 추가.
        spinner.setAdapter(adapter);

        //초기화
        final EditText idText = (EditText) findViewById(R.id.register_idText);
        final EditText passwordText = (EditText) findViewById(R.id.register_passwordText);
        final EditText emailText = (EditText) findViewById(R.id.emailText);
        final EditText student_idText = (EditText) findViewById(R.id.student_id_Text);
        final EditText nameText = (EditText) findViewById(R.id.nameText);
        final EditText phoneText = (EditText) findViewById(R.id.phoneText);

        //라디오 버튼 초기화 및 클릭 이벤트
        RadioGroup genderGroup = (RadioGroup) findViewById(R.id.genderGroup);
        int genderGroupID = genderGroup.getCheckedRadioButtonId();//남자인지 여자인지 확인하는 ID값
        userGender = ((RadioButton) findViewById(genderGroupID)).getText().toString();//현재 선택된 genderID를 매칭시키고 성별을 string처리
        genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton genderButton = (RadioButton) findViewById(i);
                userGender = genderButton.getText().toString();
            }
        });

        //중복체크버튼 초기화 및 클릭 이벤트
        final Button validateButton = (Button) findViewById(R.id.validateButton);
        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = idText.getText().toString();
                //validate체크가 이루어진 상태면 함수 바로 종료
                if (validate) {
                    return;
                }
                //ID값에 아무것도 입력하지 않았을 때, 예외발생시켜 메시지처리
                if (userID.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("아이디는 빈 칸일 수 없습니다.")
                            .setPositiveButton("확인", null)
                            .create();
                    dialog.show();
                    return;
                }
                String url = "http://qoxodnjs.cafe24.com/UserValidate.php";
                Map<String,String> params = new HashMap<String, String>();
                params.put("userID", userID);

                JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONObject mResult = response;
                                    boolean success = mResult.getBoolean("success");
                                    if (success) {//True
                                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                        dialog = builder.setMessage("사용할 수 있는 아이디입니다.")
                                                .setPositiveButton("확인", null)
                                                .create();
                                        dialog.show();
                                        idText.setEnabled(false);
                                        validate = true;
                                        idText.setBackgroundColor(getResources().getColor(R.color.colorGray));
                                        validateButton.setBackgroundColor(getResources().getColor(R.color.colorGray));
                                    }
                                    else {//False
                                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                        dialog = builder.setMessage("사용할 수 없는 아이디입니다.")
                                                .setNegativeButton("확인", null)
                                                .create();
                                        dialog.show();
                                    }
                                }
                                catch(Exception e)
                                {
                                    e.printStackTrace();;
                                }
                                //   Log.v("TAG", "변수의 값"+name.toString());
                                //drawList();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(RegisterActivity.this, "DB 연동 에러", Toast.LENGTH_LONG).show();
                            }
                        }) {};

                //실질적으로 접속할 수 있도록 객체 생성
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(jsObjRequest);
            }
        });

        //회원가입 버튼 초기화 및 클릭이벤트
        Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //모든 값들을 가져온다.
                String userID = idText.getText().toString();
                String userPassword = passwordText.getText().toString();
                String userMajor = spinner.getSelectedItem().toString();
                String userEmail = emailText.getText().toString();
                String userStudentID = student_idText.getText().toString();
                String userName = nameText.getText().toString();
                String userPhone = phoneText.getText().toString();

                //중복 체크가 되어있지 않다면
                if(!validate){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("중복 체크를 해주세요.")
                            .setNegativeButton("확인", null)
                            .create();
                    dialog.show();
                    return;
                }
                //빈칸이 있을 때
                if(userID.equals("")||userPassword.equals("")|| userMajor.equals("")||userEmail.equals("")||userGender.equals("")||
                        userStudentID.equals("")||userName.equals("")||userPhone.equals(""))
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("빈 칸 없이 입력해주세요.")
                            .setNegativeButton("확인", null)
                            .create();
                    dialog.show();
                    return;
                }

                String url = "http://qoxodnjs.cafe24.com/UserRegister.php";

                Map<String,String> params = new HashMap<String, String>();
                params.put("userID", userID);
                params.put("userPassword", userPassword);
                params.put("userGender", userGender);
                params.put("userMajor", userMajor);
                params.put("userEmail", userEmail);
                params.put("userStudentID", userStudentID);
                params.put("userName", userName);
                params.put("userPhone", userPhone);

                JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONObject mResult = response;
                                    boolean success = mResult.getBoolean("success");
                                    //회원등록 성공했을 때
                                    if (success) {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                        dialog = builder.setMessage("회원 등록에 성공했습니다.")
                                                .setPositiveButton("확인", null)
                                                .create();
                                        dialog.show();
                                        finish();//회원가입등록 창 닫기
                                    } else {//회원등록 실패했을 때
                                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                        dialog = builder.setMessage("회원 등록에 실패했습니다.")
                                                .setNegativeButton("확인", null)
                                                .create();
                                        dialog.show();
                                    }
                                } catch (Exception e) {//오류발생시
                                    e.printStackTrace();
                                }
                                //   Log.v("TAG", "변수의 값"+name.toString());
                                //drawList();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(RegisterActivity.this, "DB 연동 에러", Toast.LENGTH_LONG).show();
                            }
                        }) {
                };
                //실질적으로 접속할 수 있도록 객체 생성
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(jsObjRequest);
            }
        });
    }
    //회원등록이 이루어진 이후 회원등록창이 꺼지게 되면 실행되는 이벤트

    @Override
    protected void onStop() {
        super.onStop();
        if (dialog!=null){
            dialog.dismiss();
            dialog=null;//널값을 넣어준다.
        }
    }
}
