package com.khtn.healthcare.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.khtn.healthcare.R;
import com.khtn.healthcare.view.caseRecord.AllergensFragment;
import com.khtn.healthcare.view.caseRecord.MedHisFragment;
import com.khtn.healthcare.view.caseRecord.UsedDrugsFragment;

public class CaseRecordActivity extends AppCompatActivity implements OptionFragment.Callback {
    public static String OPTION_FRAGMENT = "OptionFragment";
    public static String MEDHIS_FRAGMENT = "MedHisFragment";
    public static String USEDDRUGS_FRAGMENT = "UsedDrugsFragment";
    public static String ALLERGEN_FRAGMENT = "AllergenFragment";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_record);
        loadOptionFragment();
    }

    private void loadOptionFragment() {
        OptionFragment optionFragment = new OptionFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, optionFragment, OPTION_FRAGMENT)
                .commit();
    }
    private void loadMedHisFragment() {
        MedHisFragment medHisFragment = new MedHisFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, medHisFragment, MEDHIS_FRAGMENT)
                .commit();
    }
    private void loadUsedDrugsFragment() {
        UsedDrugsFragment usedDrugsFragment = new UsedDrugsFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, usedDrugsFragment, USEDDRUGS_FRAGMENT)
                .commit();
    }
    private void loadAllergensFragment() {
        AllergensFragment allergensFragment = new AllergensFragment();
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
            case "Allergens":
                loadAllergensFragment();
                    break;
        }
    }
}
