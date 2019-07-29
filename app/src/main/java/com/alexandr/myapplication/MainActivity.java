package com.alexandr.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rubikstudio.library.LuckyWheelView;
import rubikstudio.library.model.LuckyItem;

public class MainActivity extends Activity {

    List<LuckyItem> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final LuckyWheelView luckyWheelView = findViewById(R.id.luckyWheel1);
        final LuckyWheelView luckyWheelView2 = findViewById(R.id.luckyWheel2);
        final LuckyWheelView luckyWheelView3 = findViewById(R.id.luckyWheel3);
        //Создание количества секций
        LuckyItem luckyItem1 = new LuckyItem("100", R.drawable.test1, 0xffFFF3E0);
        LuckyItem luckyItem2 = new LuckyItem("200", R.drawable.test2, 0xffFFF3E0);
        LuckyItem luckyItem3 = new LuckyItem("300", R.drawable.test3, 0xffFFF3E0);
        LuckyItem luckyItem4 = new LuckyItem("100", R.drawable.test1, 0xffFFF3E0);
        LuckyItem luckyItem5 = new LuckyItem("200", R.drawable.test2, 0xffFFF3E0);
        LuckyItem luckyItem6 = new LuckyItem("300", R.drawable.test3, 0xffFFF3E0);
        LuckyItem luckyItem7 = new LuckyItem("100", R.drawable.test1, 0xffFFF3E0);
        LuckyItem luckyItem8 = new LuckyItem("200", R.drawable.test2, 0xffFFF3E0);
        LuckyItem luckyItem9 = new LuckyItem("300", R.drawable.test3, 0xffFFF3E0);
        data.add(luckyItem1);
        data.add(luckyItem2);
        data.add(luckyItem3);
        data.add(luckyItem4);
        data.add(luckyItem5);
        data.add(luckyItem6);
        data.add(luckyItem7);
        data.add(luckyItem8);
        data.add(luckyItem9);
        settingsLuckyWheel(luckyWheelView, data);
        settingsLuckyWheel(luckyWheelView2, data);
        settingsLuckyWheel(luckyWheelView3, data);


        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = getRandomIndex();
                luckyWheelView.startLuckyWheelWithTargetIndex(index);
                luckyWheelView2.startLuckyWheelWithTargetIndex(index);
                luckyWheelView3.startLuckyWheelWithTargetIndex(index);
            }
        });
        //Enable false - отключена прокрутка пальцем
        setTouchEnabled(luckyWheelView, false);
        setTouchEnabled(luckyWheelView2, false);
        setTouchEnabled(luckyWheelView3, false);
//Написать распределение выйгрыша
//        luckyWheelView.setLuckyRoundItemSelectedListener(new LuckyWheelView.LuckyRoundItemSelectedListener() {
//            @Override
//            public void LuckyRoundItemSelected(int index) {
//                Toast.makeText(getApplicationContext(), data.get(index).topText, Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    public void settingsLuckyWheel(LuckyWheelView luckyWheelView, List<LuckyItem> data) {
        luckyWheelView.setData(data);
        luckyWheelView.setRound(5);
    }

    private int getRandomIndex() {
        Random rand = new Random();
        return rand.nextInt(data.size() - 1);
    }

    private void setTouchEnabled(LuckyWheelView luckyWheelView, boolean enable) {
        luckyWheelView.setTouchEnabled(enable);
    }
}
