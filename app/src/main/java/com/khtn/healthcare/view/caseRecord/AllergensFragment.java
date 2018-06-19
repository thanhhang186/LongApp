package com.khtn.healthcare.view.caseRecord;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.khtn.healthcare.R;
import com.khtn.healthcare.view.CaseRecordActivity;
import com.khtn.healthcare.view.OptionFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllergensFragment extends Fragment {
    @BindView(R.id.lvAllergens)
    ListView lvAllergens;
    @BindView(R.id.imv_allergens_back)
    ImageView imv_allergens_back;
    @BindView(R.id.imv_sign)
    ImageView imv_sign;

    @OnClick(R.id.imv_allergens_back)
    void onClickAllergensBack() {
        loadOptionFragment();
    }
    public AllergensFragment() {
    }
    @OnClick(R.id.imv_sign)
    void showMenu(){
        showPopup();

    }

    private void showPopup() {
        PopupMenu popup = new PopupMenu(getActivity(), imv_sign);
        popup.getMenuInflater()
                .inflate(R.menu.menu, popup.getMenu());
        popup.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_allergens, container, false);
        ButterKnife.bind(this,view);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadAllergens();
    }

    private void loadAllergens() {
        List<String> list = new ArrayList<>();
        list.add("Viabiovit");
        list.add("KSol");
        list.add("Prenatal");
        list.add("Safaria");
        list.add("Viabiovit");
        list.add("KSol");
        list.add("Prenatal");
        list.add("Safaria");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,list);
        lvAllergens.setAdapter(arrayAdapter);
    }
    private void loadOptionFragment() {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new OptionFragment(), CaseRecordActivity.OPTION_FRAGMENT)
                .commit();
    }

}
