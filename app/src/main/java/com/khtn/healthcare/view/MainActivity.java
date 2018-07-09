package com.khtn.healthcare.view;

import android.content.Intent;
import android.content.PeriodicSync;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.khtn.healthcare.R;
import com.khtn.healthcare.view.api.UserApi;
import com.khtn.healthcare.view.api.UserService;
import com.khtn.healthcare.view.pojo.DataResponse;
import com.khtn.healthcare.view.pojo.UserResponse;
import com.khtn.healthcare.view.utils.PrefUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btnSignIn)
    Button btnSignIn;
    @BindView(R.id.edtUsername)
    EditText username;
    @BindView(R.id.edtPassword)
    EditText password;
    private UserResponse userResponse;
    private UserApi userApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if(PrefUtils.getUsername(MainActivity.this) != null && PrefUtils.getUsername(MainActivity.this) != ""){
            Log.i("Login_Tag", "pass Pref: " + PrefUtils.getPassword(MainActivity.this));
            login(PrefUtils.getUsername(MainActivity.this), PrefUtils.getPassword(MainActivity.this));
        }
        btnSignIn.setOnClickListener(v->{
            login(username.getText().toString(), password.getText().toString());
        });
    }
    private void loadInformationAct(UserResponse userResponse){

    }
    private void login(String username, String password){
        userApi = UserService.getAPI();
        if(username != null && password != null){
            Call<UserResponse> call = userApi.login("bn_login", username, password);
            call.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if(response.isSuccessful()){
                        PrefUtils.putUsername(MainActivity.this, username);
                        PrefUtils.putPassword(MainActivity.this, password);
                        userResponse = response.body();
                        Log.i("Login_Tag", "token" + userResponse.getToken());
                        Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                        userResponse = response.body();
                        Intent intent = new Intent(MainActivity.this, InformationActivity.class);
                        intent.putExtra("token", userResponse.getToken());
                        intent.putExtra("code_id", "2C1CE152");
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Login fail", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
