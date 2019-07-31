package com.alexandr.myapplication;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import rubikstudio.library.LuckyWheelView;

public class MainActivity extends Activity implements ActivityView {
    public ActivityPresenter mActivityPresenter;
    public TextView scoreTextView;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivityPresenter = new ActivityPresenter(this, this);
        //Запуск единоразово googleReferrer
        // getGoogleRef(savedInstanceState);
        //Проверка подключение интернета
        connectionInternet(initNetworkInfo());
        //Инициализация кругов
        final LuckyWheelView luckyWheelView = findViewById(R.id.luckyWheel1);
        final LuckyWheelView luckyWheelView2 = findViewById(R.id.luckyWheel2);
        final LuckyWheelView luckyWheelView3 = findViewById(R.id.luckyWheel3);
        scoreTextView = findViewById(R.id.score);
        //Создание количества секций
        mActivityPresenter.initItems();

        //Критерий количества оборотов
        mActivityPresenter.settingsLuckyWheel(luckyWheelView, mActivityPresenter.getDataOneWheel());
        mActivityPresenter.settingsLuckyWheel(luckyWheelView2, mActivityPresenter.getDataTwoWheel());
        mActivityPresenter.settingsLuckyWheel(luckyWheelView3, mActivityPresenter.getDataThreeWheel());

        //Клик на кнопку
        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                luckyWheelView.startLuckyWheelWithTargetIndex(mActivityPresenter.getRandomIndex(mActivityPresenter.getDataOneWheel()));
                luckyWheelView2.startLuckyWheelWithTargetIndex(mActivityPresenter.getRandomIndex(mActivityPresenter.getDataTwoWheel()));
                luckyWheelView3.startLuckyWheelWithTargetIndex(mActivityPresenter.getRandomIndex(mActivityPresenter.getDataThreeWheel()));
            }
        });
        //Enable false - отключена прокрутка пальцем
        setTouchEnabled(luckyWheelView, luckyWheelView2, luckyWheelView3, false);
        luckyWheelView.setLuckyRoundItemSelectedListener(new LuckyWheelView.LuckyRoundItemSelectedListener() {
            @Override
            public void LuckyRoundItemSelected(int index) {
                mActivityPresenter.countScoreOneWheel(index);
                mActivityPresenter.setMainScore();
            }
        });
        luckyWheelView2.setLuckyRoundItemSelectedListener(new LuckyWheelView.LuckyRoundItemSelectedListener() {
            @Override
            public void LuckyRoundItemSelected(int index) {
                mActivityPresenter.countScoreTwoWheel(index);
                mActivityPresenter.setMainScore();
            }
        });
        luckyWheelView3.setLuckyRoundItemSelectedListener(new LuckyWheelView.LuckyRoundItemSelectedListener() {
            @Override
            public void LuckyRoundItemSelected(int index) {
                mActivityPresenter.countScoreThreeWheel(index);
                mActivityPresenter.setMainScore();
            }
        });


    }


    private NetworkInfo initNetworkInfo() {
        ConnectivityManager cm =
                (ConnectivityManager) getBaseContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

    public void connectionInternet(NetworkInfo networkInfo) {
        if (networkInfo == null) {
            mActivityPresenter.showMessageError(true);
        }
    }

    private void setTouchEnabled(LuckyWheelView luckyWheelView,
                                 LuckyWheelView luckyWheelViewTwo,
                                 LuckyWheelView luckyWheelViewThree,
                                 boolean enable) {
        luckyWheelView.setTouchEnabled(enable);
        luckyWheelViewTwo.setEnabled(enable);
        luckyWheelViewThree.setEnabled(enable);
    }
/*
    public void getGoogleRef(Bundle bundle) {
        if (bundle != null) {
            bundle = getIntent().getExtras();
            String startGoogleRefer = bundle.getString("initG");
            if (startGoogleRefer.equals("google")) {
                mActivityPresenter.initInstallRefer(getBaseContext());
            }

        }
    }
*/


    @Override
    public void showToastErrorConnection() {
        Toast.makeText(this, "Пожалуйста включите интернет и перезапустите приложение", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showScore(String score) {
        String s = "Вы выйграли : ";
        scoreTextView.setText(s.concat(score));
    }
}
