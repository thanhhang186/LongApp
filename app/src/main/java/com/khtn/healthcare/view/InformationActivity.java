package com.khtn.healthcare.view;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.khtn.healthcare.R;
import com.khtn.healthcare.view.api.UserApi;
import com.khtn.healthcare.view.api.UserService;
import com.khtn.healthcare.view.caseRecord.MedHisFragment;
import com.khtn.healthcare.view.pojo.UserResponse;
import com.khtn.healthcare.view.utils.PrefUtils;

import org.parceler.Parcels;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformationActivity extends AppCompatActivity {
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.btnEditInfo)
    Button btnEditInfo;
    @BindView(R.id.edtFullname)
    EditText edtFullname;
    @BindView(R.id.edtBirthDay)
    EditText edtBirthDay;
    @BindView(R.id.edtAddress)
    EditText edtAddress;
    @BindView(R.id.btnSaveInfo)
    Button btnSaveInfo;
    @BindView(R.id.tvCaseRecord)
    TextView tvCaseRecord;
    @BindView(R.id.radFemale)
    RadioButton radFemale;
    @BindView(R.id.radMale)
    RadioButton radMale;
    @BindView(R.id.radGroup)
    RadioGroup radGroup;
    private DatePickerDialog datePickerDialog;
    private UserResponse userResponse;
    private UserApi userApi;
    public static final String TAG = "Information";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        ButterKnife.bind(this);
        userApi = UserService.getAPI();
        initToolbar();
        showInfo();
        btnEditInfo.setOnClickListener(v -> {
            editInfo();
        });
        btnSaveInfo.setOnClickListener(v -> {
            Toast.makeText(this, "Update successfully", Toast.LENGTH_SHORT).show();
            btnEditInfo.setVisibility(View.VISIBLE);
            btnSaveInfo.setVisibility(View.GONE);
            showInfo();

        });
        tvCaseRecord.setOnClickListener(v -> {
            Intent intent = new Intent(this, CaseRecordActivity.class);
            intent.putExtra("data", Parcels.wrap(userResponse));
            startActivity(intent);
        });
    }

    private void editInfo() {
        edtFullname.setCursorVisible(true);
        edtAddress.setCursorVisible(true);
        edtBirthDay.setCursorVisible(true);
        edtFullname.setFocusable(true);
        edtFullname.setInputType(InputType.TYPE_CLASS_TEXT);
        edtBirthDay.setInputType(InputType.TYPE_CLASS_TEXT);
        edtAddress.setInputType(InputType.TYPE_CLASS_TEXT);
        radMale.setVisibility(View.VISIBLE);
        radFemale.setVisibility(View.VISIBLE);

        btnEditInfo.setVisibility(View.GONE);
        btnSaveInfo.setVisibility(View.VISIBLE);
        edtBirthDay.setOnClickListener(v->{
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);
            datePickerDialog = new DatePickerDialog(InformationActivity.this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            edtBirthDay.setText(dayOfMonth + "/"
                                    + (monthOfYear + 1) + "/" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        });

        radGroup.setOnClickListener(v->{
            if(radMale.isChecked())
            {
                Toast.makeText(this, "Male", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Female", Toast.LENGTH_SHORT).show();
            }

        });


    }

    public void initToolbar() {
        toolBar.setTitle("Information");
        setSupportActionBar(toolBar);
        toolBar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
        toolBar.setNavigationIcon(R.drawable.ic_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    public void showInfo(){
        edtFullname.setCursorVisible(false);
        edtFullname.setInputType(InputType.TYPE_NULL);
        edtAddress.setCursorVisible(false);
        edtAddress.setInputType(InputType.TYPE_NULL);
        edtBirthDay.setCursorVisible(false);
        edtBirthDay.setInputType(InputType.TYPE_NULL);

        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        String token = "";
        String code_id = "";
            Bundle extras = getIntent().getExtras();
            if(extras != null) {
                token= extras.getString("token");
                code_id = extras.getString("code_id");
            }
        Call<UserResponse> data = userApi.getData("bn_fetch_data",token, code_id);
            data.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if(response.isSuccessful()){
                        userResponse = response.body();
                        Log.i(TAG, "mec his list: " + userResponse.getData().getGender());
                        edtFullname.setText(userResponse.getData().getFullname().toString());
                        edtBirthDay.setText(userResponse.getData().getDob().toString());

                        if(userResponse.getData().getGender().equals("0")){
                            radMale.setChecked(true);
                            radMale.setVisibility(View.VISIBLE);
                            radFemale.setVisibility(View.GONE);
                        } else {
                            radFemale.setChecked(true);
                            radFemale.setVisibility(View.VISIBLE);
                            radMale.setVisibility(View.GONE);
                        }
                        edtAddress.setText(userResponse.getData().getAddress().toString());
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {

                }
            });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_logout){
            Call<UserResponse> call = userApi.logout("bn_logout", userResponse.getToken());
            call.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    Toast.makeText(InformationActivity.this, "Logout Successful", Toast.LENGTH_SHORT).show();
                    PrefUtils.putPassword(InformationActivity.this, "");
                    PrefUtils.putUsername(InformationActivity.this, "");
                    Intent intent = new Intent(InformationActivity.this, MainActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Toast.makeText(InformationActivity.this, "Logout failed", Toast.LENGTH_SHORT).show();
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }
}
