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
public class UsedDrugsFragment extends Fragment {

    @BindView(R.id.lvDrugs)
    ListView lvDrugs;
    @BindView(R.id.imv_drugs_back)
    ImageView imv_drugs_back;
    @BindView(R.id.imv_sign)
    ImageView imv_sign;
    @OnClick(R.id.imv_drugs_back)
    void onClickDrugsBack() {
        loadOptionFragment();
    }
    public UsedDrugsFragment() {
        // Required empty public constructor
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
        View view = inflater.inflate(R.layout.fragment_used_drugs, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadDrugs();
    }

    private void loadDrugs() {
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
        lvDrugs.setAdapter(arrayAdapter);
    }
    private void loadOptionFragment() {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new OptionFragment(), CaseRecordActivity.OPTION_FRAGMENT)
                .commit();
    }
}
