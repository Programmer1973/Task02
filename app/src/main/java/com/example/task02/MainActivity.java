package com.example.task02;

/**
 * Задание на взаимную оценку №2.
 *
 * Часть 1.
 * Создать проект.
 * Добавить в активити меню с 3 пунктами - Настройки, Поиск, Выход.
 * Добавить в разметку активити FrameLayout, который занимает весь экран.
 * При нажатии на пункты меню появляется тост с названием нажатого пункта.
 *
 * Часть 2.
 * Создать 3 фрагмента.
 *
 * Эти фрагменты будут меняться в контейнере на экране при нажатии на соответствующие пункты меню.
 * Так же они должны быть добавлены в backstack FragmentManager'а.
 * Главный - открывается по умолчанию при запуске приложения. (добавляется в контейнер). Добавить
 * разметку на ваше усмотрение.
 * Настройки - открывается по нажатию на пункт меню Настройки. На разметке находятся RadioButton'ы
 * - три пункта - Google, Яндекс, Bing. Сохранять выбранный пункт в SharedPreferences. Извлекать из
 * SharedPreferences при запуске экрана.
 * Поиск - открывается по нажатию на пункт Поиск. На разметке находится EditText и Кнопка поиск.
 * При нажатии на кнопку - открывается браузер с поиском введенного текста в выбранном на экране настроек поисковике.
 * При нажатии на пункт Выход - приложение закрывается.
 *
 * @created 24.02.2019
 * @author Andrey Dudin <programmer1973@mail.ru>
 * @version 0.1.0.2019.02.24
 */

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction()
                    .replace(R.id.fragment_container, FirstFragment.getInstance())
                    .addToBackStack(FirstFragment.class.getName())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager fm = getSupportFragmentManager();

        switch (item.getItemId()) {
            case R.id.menu_settings:
                fm.beginTransaction()
                        .replace(R.id.fragment_container, SettingsFragment.getInstance())
                        .addToBackStack(SettingsFragment.class.getName())
                        .commit();
                showToast(getString(R.string.m_settings));
                break;
            case R.id.menu_search:
                fm.beginTransaction()
                        .replace(R.id.fragment_container, SearchFragment.getInstance())
                        .addToBackStack(SearchFragment.class.getName())
                        .commit();
                showToast(getString(R.string.m_search));
                break;
            case R.id.menu_logout:
                finish();
                showToast(getString(R.string.m_logout));
                break;
            default:
                showToast(getString(R.string.something_went_wrong));
        }

        return super.onOptionsItemSelected(item);
    }

    private void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}