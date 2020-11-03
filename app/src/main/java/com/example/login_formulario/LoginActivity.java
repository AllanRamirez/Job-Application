package com.example.login_formulario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();
    }

    private void initialize(){
        this.password = findViewById(R.id.password);
        this.username = findViewById(R.id.userName);
        this.usersManagement = Database.getInstance();

        ImageView btnLogin = findViewById(R.id.loginBtn);
        TextView tvRegisterHere = findViewById(R.id.txtRegisterHere);
        TextView tvForgotYourPassword = findViewById(R.id.txtForgotYourPassword);

        btnLogin.setOnClickListener(this);
        tvRegisterHere.setOnClickListener(this);
        tvForgotYourPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginBtn:
                User logged = verifyUser(this.username.getText().toString(), this.password.getText().toString());
                if(logged != null){
                    if(logged.getRol().equals(getString(R.string.administratorRole))){
                        Intent intent = new Intent(this, MenuAdminActivity.class);
                        this.startActivity(intent);
                    }else{ //normal user
                        Intent intent = new Intent(this, FormularioActivity.class);
                        this.startActivity(intent);
                    }
                    finish();
                }else{
                    Toast.makeText(this, "ERROR: Invalid Credentials.", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.txtRegisterHere:
                this.startActivity(new Intent(this, SignUpActivity.class));
                break;

            case R.id.txtForgotYourPassword:
                this.startActivity(new Intent(this, ReestablishPasswordActivity.class));
                break;
        }
    }

    private User verifyUser(String username, String password){
        for(User u: usersManagement.getUsers()){
            if(username.equals(u.getUsername()) && password.equals(u.getPassword())){
                return u;
            }
        }
        return null;
    }

    private Database usersManagement;
    private EditText username, password;
}
