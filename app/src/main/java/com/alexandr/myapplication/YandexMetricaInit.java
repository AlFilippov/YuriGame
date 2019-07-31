package com.alexandr.myapplication;

import android.app.Application;

import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.YandexMetricaConfig;
/*
public class YandexMetricaInit extends Application {
    public YandexMetricaConfig mYandexMetricaConfig;
    @Override
    public void onCreate() {
        activateYandexMetrica(setYandexMetricaConfig("API_KEY"));
        YandexMetrica.enableActivityAutoTracking(this);
        super.onCreate();
    }
    public YandexMetricaConfig setYandexMetricaConfig(String key) {
        return mYandexMetricaConfig = YandexMetricaConfig.newConfigBuilder(key).build();
    }

    private void activateYandexMetrica(YandexMetricaConfig yandexMetricaConfig ) {
        com.yandex.metrica.YandexMetrica.activate(getApplicationContext(),yandexMetricaConfig);

    }
}
*/