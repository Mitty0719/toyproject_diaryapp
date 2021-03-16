package com.example.diaryapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.diaryapplication.DTO.UserDTO;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IntroActivity extends AppCompatActivity {

    private LinearLayout mainLayout, joinLayout, loginLayout;
    private EditText join_nameEdit, join_emailEdit, join_passwordEdit, join_passwordConfirm, login_email, login_password;
    private FirebaseDatabase database;
    private DatabaseReference userReference;
    private DatabaseReference checkReference;
    private MySharedPreference mySharedPreference = new MySharedPreference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        String isLoginValue = mySharedPreference.getPreference(this, "email");
        if(isLoginValue!=null){
            Intent intent = new Intent(IntroActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // activity 종료
        }

        mainLayout = findViewById(R.id.intro_mainLayout);
        joinLayout = findViewById(R.id.intro_joinLayout);
        loginLayout = findViewById(R.id.intro_loginLayout);

        join_nameEdit = findViewById(R.id.join_nameEdit);
        join_emailEdit = findViewById(R.id.join_emailEdit);
        join_passwordEdit = findViewById(R.id.join_passwordEdit);
        join_passwordConfirm = findViewById(R.id.join_passwordConfirm);

        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);

        database = FirebaseDatabase.getInstance();
        userReference = database.getReference("User");
    }

    // 버튼 클릭 동작
    public void introClickedBtn(View view){
        switch (view.getId()){
            case R.id.intro_emailloginBtn:
                mainLayout.setVisibility(View.GONE);
                loginLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.intro_joinBtn:
                mainLayout.setVisibility(View.GONE);
                joinLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.intro_notLoginBtn: //로그인 없이 접속
                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // activity 종료
                break;
            case R.id.join_submitBtn: //회원가입
                addUser();
                break;
            case R.id.join_toMainBtn:
                joinLayout.setVisibility(View.GONE);
                mainLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.login_toMainBtn:
                loginLayout.setVisibility(View.GONE);
                mainLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.login_loginBtn:
                String email = login_email.getText().toString();
                String password = login_password.getText().toString();
                loginUserCheck(email, password);
                break;
        }
    }

    //유저 추가(회원가입)
    public void addUser(){
        String name = join_nameEdit.getText().toString();
        final String email = join_emailEdit.getText().toString();
        String password = join_passwordEdit.getText().toString();
        String passwordConfirm = join_passwordConfirm.getText().toString();

        checkReference = database.getReference("User");
        checkReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Boolean isUser = false;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd, hh:mm:ss");

                //비밀번호 확인
                if(!password.equals(passwordConfirm)){
                    Log.i("비밀번호 확인 ", "다름");
                    Toast.makeText(IntroActivity.this, "비밀번호를 확인하세요", Toast.LENGTH_LONG);
                    return;
                }

                //이메일 중복 확인
                for(DataSnapshot snap : snapshot.getChildren()){
                    UserDTO user = snap.getValue(UserDTO.class);
                    System.out.println(email + " : " + user.getEmail());
                    //유효성 검사
                    if(user.getEmail().equals(email)){
                        isUser = true;
                        break;
                    }
                }

                //아이디 입력
                if(isUser == true) {
                    Log.i("이메일", "이미 있음");
                    Toast.makeText(getApplicationContext(), "이미 등록된 이메일 입니다", Toast.LENGTH_LONG);
                }else {
                    userReference.child(email).setValue(new UserDTO(name, email, password, dateFormat.format(new Date())));
                    Toast.makeText(IntroActivity.this, "회원가입 완료!", Toast.LENGTH_LONG);
                    joinLayout.setVisibility(View.GONE);
                    loginLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(IntroActivity.this, "Error : "+error, Toast.LENGTH_LONG).show();
            }
        });

    } // addUser

    //로그인 회원정보 확인
    public void loginUserCheck(String email, String password){
        Log.i("email", email);
        Log.i("password", password);
        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren()){
                    UserDTO user = snap.getValue(UserDTO.class);
                    if(user.getEmail().equals(email) & user.getPassword().equals(password)){

                        mySharedPreference.putPreference(IntroActivity.this, "email", email);

                        Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish(); // activity 종료
                        return;
                    }
                }
                Toast.makeText(IntroActivity.this, "Email 혹은 비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                Log.i("Login : ", "입력정보 불일치");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("error : ", error+"");
            }
        });
    }//loginUserCheck
}