package com.khtn.healthcare.view;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.khtn.healthcare.R;
import com.khtn.healthcare.view.api.UserApi;
import com.khtn.healthcare.view.api.UserService;
import com.khtn.healthcare.view.caseRecord.AllergensFragment;
import com.khtn.healthcare.view.caseRecord.MedHisFragment;
import com.khtn.healthcare.view.caseRecord.UsedDrugsFragment;
import com.khtn.healthcare.view.pojo.DataResponse;
import com.khtn.healthcare.view.pojo.MedHisResponse;
import com.khtn.healthcare.view.pojo.UserResponse;
import com.khtn.healthcare.view.utils.PrefUtils;

import org.parceler.Parcels;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CaseRecordActivity extends AppCompatActivity implements OptionFragment.Callback, AllergensFragment.Callback, UsedDrugsFragment.Callback, MedHisFragment.Callback{
    public static String OPTION_FRAGMENT = "OptionFragment";
    public static String MEDHIS_FRAGMENT = "MedHisFragment";
    public static String USEDDRUGS_FRAGMENT = "UsedDrugsFragment";
    public static String ALLERGEN_FRAGMENT = "AllergenFragment";

    private DataResponse dataResponse;
    private UserResponse userResponse;
    private UserApi userApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_record);
        Intent intent = getIntent();
        if(intent != null) {
            userResponse = Parcels.unwrap(intent.getParcelableExtra("data"));
            dataResponse = userResponse.getData();
            Log.i("case_record", "alle: " + dataResponse.getAlle());
        }
        loadOptionFragment();
    }

    private void loadOptionFragment() {
        OptionFragment optionFragment = new OptionFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, optionFragment, OPTION_FRAGMENT)
                .commit();
    }
    private void loadMedHisFragment() {
        MedHisFragment medHisFragment = MedHisFragment.getInstance(dataResponse.getMecHis());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, medHisFragment, MEDHIS_FRAGMENT)
                .commit();
    }
    private void loadUsedDrugsFragment() {
        UsedDrugsFragment usedDrugsFragment = UsedDrugsFragment.getInstance(parse(dataResponse.getAlle()));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, usedDrugsFragment, USEDDRUGS_FRAGMENT)
                .commit();
    }
    private void loadAllergensFragment() {
        AllergensFragment allergensFragment = AllergensFragment.getInstance(parse(dataResponse.getAlle()));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, allergensFragment, ALLERGEN_FRAGMENT)
                .commit();
    }

    @Override
    public void onOptionClicked(String option) {
        switch (option){
            case "Medical History":
                loadMedHisFragment();
                break;
            case "Used Drugs":
                loadUsedDrugsFragment();
                break;
        }
    }

    @Override
    public void logoutOption() {
        logoutAccount();
    }

    public ArrayList<String> parse(String jsonLine){
        ArrayList<String> alles = new ArrayList<>();
        String[] split = jsonLine.split("[|]");
        for (String s : split) {
            if (!s.isEmpty()) {
                alles.add(s);
            }
        }
        return alles;
    }

    @Override
    public void logoutAlle() {
        logoutAccount();
    }

    public void logoutAccount(){
        userApi = UserService.getAPI();
        Call<UserResponse> call = userApi.logout("bn_logout", userResponse.getToken());
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                Toast.makeText(CaseRecordActivity.this, "Logout Successful", Toast.LENGTH_SHORT).show();
                PrefUtils.putPassword(CaseRecordActivity.this, "");
                PrefUtils.putUsername(CaseRecordActivity.this, "");
                Intent intent = new Intent(CaseRecordActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(CaseRecordActivity.this, "Logout failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void logoutMedHis() {
        logoutAccount();
    }

    @Override
    public void logoutUsedDrug() {
        logoutAccount();
    }
}
