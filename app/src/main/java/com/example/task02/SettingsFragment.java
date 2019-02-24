package com.example.task02;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SettingsFragment extends Fragment {

    private final String GOOGLE_SEARCH_ENGINE = "Google";
    private static final String YANDEX_SEARCH_ENGINE = "Яндекс";
    private static final String BING_SEARCH_ENGINE = "Bing";

    private RadioGroup mRadioGroup;
    private RadioButton mGoogleBtn;
    private RadioButton mYandexBtn;
    private RadioButton mBingBtn;

    private SharedPreferencesHelper mSharedPreferencesHelper;

    public static SettingsFragment getInstance() {
        return new SettingsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mSharedPreferencesHelper = new SharedPreferencesHelper(getActivity());

        View view = inflater.inflate(R.layout.settings_fragment, container, false);
        mRadioGroup = view.findViewById(R.id.radio_group);
        mGoogleBtn = view.findViewById(R.id.google_radio_button);
        mYandexBtn = view.findViewById(R.id.yandex_radio_button);
        mBingBtn = view.findViewById(R.id.bing_radio_button);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        String choosenButtonOfSearchEngine = mSharedPreferencesHelper.getPressedRadioButton();

        switch(choosenButtonOfSearchEngine) {
            case GOOGLE_SEARCH_ENGINE:
                mGoogleBtn.setChecked(true);
                break;
            case YANDEX_SEARCH_ENGINE:
                mYandexBtn.setChecked(true);
                break;
            case BING_SEARCH_ENGINE:
                mBingBtn.setChecked(true);
                break;
            default:
                Toast.makeText(getActivity(), getString(R.string.choose_search_engine), Toast.LENGTH_SHORT).show();
        }

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.google_radio_button:
                        savePressedRadioBtn(mGoogleBtn.getText().toString());
                        break;
                    case R.id.yandex_radio_button:
                        savePressedRadioBtn(mYandexBtn.getText().toString());
                        break;
                    case R.id.bing_radio_button:
                        savePressedRadioBtn(mBingBtn.getText().toString());
                        break;
                    default:
                        Toast.makeText(getActivity(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void savePressedRadioBtn(String str) {
        mSharedPreferencesHelper.addPressedRadioButton(str);
    }
}