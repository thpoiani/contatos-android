package br.com.thpoiani.contatos;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Contatos extends Application {
    private static Context context;

    public void onCreate() {
        super.onCreate();
        Contatos.context = getApplicationContext();

        RealmConfiguration configuration = new RealmConfiguration.Builder(getAppContext())
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
    }

    public static Context getAppContext() {
        return Contatos.context;
    }
}
