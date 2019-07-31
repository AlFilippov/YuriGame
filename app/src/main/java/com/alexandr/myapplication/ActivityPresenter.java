package com.alexandr.myapplication;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;

import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rubikstudio.library.LuckyWheelView;
import rubikstudio.library.model.LuckyItem;

class ActivityPresenter {
    private ActivityView mActivityView;
    private InstallReferrerClient mClient;
    private List<LuckyItem> dataOneWheel = new ArrayList<>();
    private List<LuckyItem> dataTwoWheel = new ArrayList<>();
    private List<LuckyItem> dataThreeWheel = new ArrayList<>();
    private InstallReferrerStateListener installReferrerStateListener;
    private static int scoreOneWheel;
    private static int scoreTwoWheel;
    private static int scoreThreeWheel;
    private static int mainScore;

    ActivityPresenter(Context context, ActivityView activityView) {
        Context context1 = context;
        mActivityView = activityView;
    }

    void initItems() {
        LuckyItem luckyItem1 = new LuckyItem(100, R.drawable.test1, 0xffFFF3E0);
        LuckyItem luckyItem2 = new LuckyItem(200, R.drawable.test2, 0xffFFE0B2);
        LuckyItem luckyItem3 = new LuckyItem(300, R.drawable.test3, 0xffFFCC80);
        LuckyItem luckyItem4 = new LuckyItem(400, R.drawable.test4, 0xffFFF3E0);
        LuckyItem luckyItem5 = new LuckyItem(500, R.drawable.test5, 0xffFFE0B2);
        LuckyItem luckyItem6 = new LuckyItem(600, R.drawable.test6, 0xffFFCC80);

        LuckyItem luckyItem7 = new LuckyItem(100, R.drawable.test1, 0xffFFF3E0);
        LuckyItem luckyItem8 = new LuckyItem(200, R.drawable.test2, 0xffFFE0B2);
        LuckyItem luckyItem9 = new LuckyItem(300, R.drawable.test3, 0xffFFCC80);
        LuckyItem luckyItem10 = new LuckyItem(400, R.drawable.test4, 0xffFFF3E0);
        LuckyItem luckyItem11 = new LuckyItem(500, R.drawable.test5, 0xffFFE0B2);
        LuckyItem luckyItem12 = new LuckyItem(600, R.drawable.test6, 0xffFFCC80);

        LuckyItem luckyItem13 = new LuckyItem(100, R.drawable.test1, 0xffFFF3E0);
        LuckyItem luckyItem14 = new LuckyItem(200, R.drawable.test2, 0xffFFE0B2);
        LuckyItem luckyItem15 = new LuckyItem(300, R.drawable.test3, 0xffFFCC80);
        LuckyItem luckyItem16 = new LuckyItem(400, R.drawable.test4, 0xffFFF3E0);
        LuckyItem luckyItem17 = new LuckyItem(500, R.drawable.test5, 0xffFFE0B2);
        LuckyItem luckyItem18 = new LuckyItem(600, R.drawable.test6, 0xffFFCC80);
        setDataOneWheel(luckyItem1, luckyItem7, luckyItem13);
        setDataOneWheel(luckyItem2, luckyItem8, luckyItem14);
        setDataOneWheel(luckyItem3, luckyItem9, luckyItem15);
        setDataOneWheel(luckyItem4, luckyItem10, luckyItem16);
        setDataOneWheel(luckyItem5, luckyItem11, luckyItem17);
        setDataOneWheel(luckyItem6, luckyItem12, luckyItem18);
    }

    private void setDataOneWheel(LuckyItem luckyItem, LuckyItem luckyItemtwo, LuckyItem luckyItemThree) {
        dataOneWheel.add(luckyItem);
        dataTwoWheel.add(luckyItemtwo);
        dataThreeWheel.add(luckyItemThree);
    }

    void settingsLuckyWheel(LuckyWheelView luckyWheelView, List<LuckyItem> data) {
        luckyWheelView.setData(data);
        luckyWheelView.setRound(3);
    }

    int getRandomIndex(List<LuckyItem> dataWheel) {
        Random rand = new Random();
        return rand.nextInt(dataWheel.size() - 1);
    }

    List<LuckyItem> getDataOneWheel() {
        return dataOneWheel;
    }

    List<LuckyItem> getDataTwoWheel() {
        return dataTwoWheel;
    }

    List<LuckyItem> getDataThreeWheel() {
        return dataThreeWheel;
    }

    void countScoreOneWheel(int index) {
        scoreOneWheel = dataOneWheel.get(index).score;
    }

    void countScoreTwoWheel(int index) {
        scoreTwoWheel = dataTwoWheel.get(index).score;
    }

    void countScoreThreeWheel(int index) {
        scoreThreeWheel = dataThreeWheel.get(index).score;
    }

    void setMainScore() {
        mActivityView.showScore(mainScoreWheels());
    }

    private String mainScoreWheels() {
        mainScore = mainScore + scoreOneWheel + scoreTwoWheel + scoreThreeWheel;
        return String.valueOf(mainScore );
    }

    //Нужно будет для сторонних трекеров
    void initInstallRefer(Context context) {
        mClient = InstallReferrerClient.newBuilder(context).build();

        installReferrerStateListener = new InstallReferrerStateListener() {
            @Override
            public void onInstallReferrerSetupFinished(int responseCode) {
                switch (responseCode) {
                    case InstallReferrerClient.InstallReferrerResponse.OK:
                        try {
                            getInstallReferrer();
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        break;
                    case InstallReferrerClient.InstallReferrerResponse.FEATURE_NOT_SUPPORTED:
                        Log.d("Not", "API недоступен");
                    case InstallReferrerClient.InstallReferrerResponse.SERVICE_UNAVAILABLE:
                        Log.d("Not", "Соединение не может быть установлено");
                }
            }

            @Override
            public void onInstallReferrerServiceDisconnected() {
                mClient.startConnection(installReferrerStateListener);
            }
        };

        mClient.startConnection(installReferrerStateListener);

    }

    void endConnection() {
        mClient.endConnection();
    }

    void showMessageError(boolean error) {
        if (error) {
            mActivityView.showToastErrorConnection();
        }

    }

    private void getInstallReferrer() throws RemoteException {
        if (mClient.isReady()) {
            ReferrerDetails response = mClient.getInstallReferrer();
            response.getInstallReferrer();
            response.getReferrerClickTimestampSeconds();
            response.getInstallBeginTimestampSeconds();
        }
    }
}
