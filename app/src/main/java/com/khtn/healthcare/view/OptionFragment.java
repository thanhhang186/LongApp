package com.khtn.healthcare.view;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.khtn.healthcare.R;
import com.khtn.healthcare.view.caseRecord.adapter.OptionAdapter;
import com.khtn.healthcare.view.caseRecord.OnClickedOption;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class OptionFragment extends Fragment implements OnClickedOption{
    @BindView(R.id.listOption)
    RecyclerView listOption;
    @BindView(R.id.imv_option_back)
    ImageView imv_option_back;
    @BindView(R.id.imv_sign)
    ImageView imv_sign;
    private OptionAdapter optionAdapter;
    private Callback callback;

    @OnClick(R.id.imv_option_back)
    void onClickBack(){
        getActivity().onBackPressed();
    }

    @OnClick(R.id.imv_sign)
    void showMenu(){
       showPopup();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback = (Callback) context;
    }

    public interface Callback{
        void onOptionClicked(String option);
        void logoutOption();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_option, container, false);
        ButterKnife.bind(this,view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<String> options = new ArrayList<>();
        options.add("Medical History");
        options.add("Used Drugs");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        listOption.setLayoutManager(layoutManager);
        listOption.setHasFixedSize(true);
        listOption.setItemAnimator(new DefaultItemAnimator());

        optionAdapter = new OptionAdapter(options, this);
        listOption.setAdapter(optionAdapter);
    }

    @Override
    public void onClickedOption(String option) {
        switch (option){
            case "Medical History":
                callback.onOptionClicked("Medical History");
                break;
            case "Used Drugs":
                callback.onOptionClicked("Used Drugs");
                break;
        }
    }

    private void showPopup() {
        PopupMenu popup = new PopupMenu(getActivity(), imv_sign);
        popup.getMenuInflater()
                .inflate(R.menu.menu, popup.getMenu());
        popup.show();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.menu_logout){
                    callback.logoutOption();
                }
                return false;
            }
        });
    }

}
