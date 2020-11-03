package com.example.login_formulario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class ReestablishPasswordActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText verifyPassword, username;
    private TextInputLayout password;
    private Database usersManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initialize();
    }

    private void initialize(){
        this.password = findViewById(R.id.signUpPassword);
        this.verifyPassword = findViewById(R.id.signUpVerifyPassword);
        this.username = findViewById(R.id.signUpUsername);
        this.usersManagement = Database.getInstance();
        findViewById(R.id.btnDoRegister).setOnClickListener(this);

        ((TextView)findViewById(R.id.UI_title)).setText("Forgot Your Password?");
        ((TextInputLayout)findViewById(R.id.signUpPassword)).setHint("    New Password");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnDoRegister:
                changePassword();
                break;
        }
    }

    private void changePassword(){
        if(verifyUsername() == true){
            if(verifyPassword() == true){
                this.usersManagement.changePassword(this.username.getText().toString(), this.password.getEditText().getText().toString());
                Intent intent = new Intent(this, LoginActivity.class);
                this.startActivity(intent);
                this.finish();
                Toast.makeText(this, "Password succesfully changed.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Error: Passwords doesn't match.", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Error: Username dosen't exists.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean verifyUsername(){
        return (this.usersManagement.verifyUsername(this.username.getText().toString()));
    }

    private boolean verifyPassword(){
        return (this.password.getEditText().getText().toString().equals(this.verifyPassword.getText().toString()));
    }
}