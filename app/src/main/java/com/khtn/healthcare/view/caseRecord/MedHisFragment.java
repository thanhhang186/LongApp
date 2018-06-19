package com.khtn.healthcare.view.caseRecord;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import com.khtn.healthcare.R;
import com.khtn.healthcare.view.CaseRecordActivity;
import com.khtn.healthcare.view.OptionFragment;
import com.khtn.healthcare.view.caseRecord.adapter.HistoryAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MedHisFragment extends Fragment {

    @BindView(R.id.lvHistory)
    ListView lvHistory;
    @BindView(R.id.imv_med_his_back)
    ImageView imvMedHisBack;
    @BindView(R.id.imv_sign)
    ImageView imv_sign;
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


    public MedHisFragment() {
        // Required empty public constructor
    }
    @OnClick(R.id.imv_sign)
    void onClickSign(){
        createMenu();
    }

    private void createMenu() {
    }

    @OnClick(R.id.imv_med_his_back)
    void onClickMedHisBack() {
        loadOptionFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_med_his, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadHistory();
    }
    
    public void loadHistory() {
        List<String> list = new ArrayList<>();
        list.add("aaaaaa");
        list.add("as");
        list.add("aasdd");
        list.add("aaaaaa");
        list.add("as");
        list.add("aasdd");
        list.add("aaaaaa");
        list.add("as");
        list.add("aasdd");
        list.add("aaaaaa");
        list.add("as");
        list.add("aasdd");
        list.add("aaaaaa");
        list.add("as");
        list.add("aasdd");
        HistoryAdapter historyAdapter = new HistoryAdapter(getActivity(), list);
        lvHistory.setAdapter(historyAdapter);
        lvHistory.setBackgroundColor(Color.WHITE);
    }

    private void loadOptionFragment() {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new OptionFragment(), CaseRecordActivity.OPTION_FRAGMENT)
                .commit();
    }
}
