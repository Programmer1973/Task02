package com.example.task02;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchFragment extends Fragment {

    private EditText mSearchEditTextView;
    private Button mButtonSearch;

    private SharedPreferencesHelper mSharedPreferencesHelper;

    private static final String GOOGLE_SEARCH_ENGINE = "Google";
    private static final String YANDEX_SEARCH_ENGINE = "Яндекс";
    private static final String BING_SEARCH_ENGINE = "Bing";

    private static final String GOOGLE_SEARCH_START_STRING = "https://www.google.com/search?q";
    private static final String YANDEX_SEARCH_START_STRING = "https://yandex.ru/search/?text";
    private static final String BING_SEARCH_START_STRING = "https://www.bing.com/search?q";

    public static SearchFragment getInstance() {
        return new SearchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mSharedPreferencesHelper = new SharedPreferencesHelper(getActivity());

        View view = inflater.inflate(R.layout.search_fragment, container, false);
        mSearchEditTextView = view.findViewById(R.id.edit_text_for_search);
        mButtonSearch =  view.findViewById(R.id.button_to_search);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        mButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String queryDetails = mSearchEditTextView.getText().toString().trim();

                if (!TextUtils.isEmpty(queryDetails)) {

                    String choosenButtonOfSearchEngine = mSharedPreferencesHelper.getPressedRadioButton();

                    switch (choosenButtonOfSearchEngine){
                        case GOOGLE_SEARCH_ENGINE:
                            startSearch(GOOGLE_SEARCH_START_STRING + "=" + queryDetails);
                            break;
                        case YANDEX_SEARCH_ENGINE:
                            startSearch(YANDEX_SEARCH_START_STRING + "=" + queryDetails);
                            break;
                        case BING_SEARCH_ENGINE:
                            startSearch(BING_SEARCH_START_STRING + "=" + queryDetails);
                            break;
                        default:
                            Toast.makeText(getActivity(), getString(R.string.search_engine_not_choosen), Toast.LENGTH_SHORT).show();
                    }
                } else
                    Toast.makeText(getActivity(), getString(R.string.empty_enquiry), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startSearch(String searchQuery) {
        Uri uri = Uri.parse(searchQuery);
        Intent openSearchEngine = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(openSearchEngine);
    }

    public static String getGoogleSearchEngine() {
        return GOOGLE_SEARCH_ENGINE;
    }

    public static String getYandexSearchEngine() {
        return YANDEX_SEARCH_ENGINE;
    }

    public static String getBingSearchEngine() {
        return BING_SEARCH_ENGINE;
    }
}