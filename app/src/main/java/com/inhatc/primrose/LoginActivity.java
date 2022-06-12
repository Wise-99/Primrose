package com.inhatc.primrose;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Button btn_login;
    private EditText login_email;
    private EditText login_passwd;
    private String lemail;
    private String lpasswd;
    private Button btn_join;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        firebaseAuth = firebaseAuth.getInstance();//firebaseAuth의 인스턴스를 가져옴

        btn_login = findViewById(R.id.loginBtn);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login_email = findViewById(R.id.editTextEmail);
                login_passwd = findViewById(R.id.editTextPassword);

                lemail = login_email.getText().toString().trim();
                lpasswd = login_passwd.getText().toString().trim();
                //String형 변수 email.pwd(edittext에서 받오는 값)으로 로그인

                firebaseAuth.signInWithEmailAndPassword(lemail, lpasswd)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {//성공 시
                                    Toast.makeText(LoginActivity.this, "로그인 성공!", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                } else {//실패 시
                                    Toast.makeText(LoginActivity.this, "아이디와 비밀번호를 확인해주세요!", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

        btn_join = findViewById(R.id.joinBtn);
        btn_join.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent joinIntent = new Intent(LoginActivity.this, JoinActivity.class);
                startActivity(joinIntent);
            }
        });
    }
}
