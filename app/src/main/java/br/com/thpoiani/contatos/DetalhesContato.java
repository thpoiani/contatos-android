package br.com.thpoiani.contatos;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;

import br.com.thpoiani.contatos.entity.ContactEntity;
import io.realm.Realm;

public class DetalhesContato extends ActionBarActivity {

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_contato);

        realm = Realm.getDefaultInstance();

        Long id = getIntent().getLongExtra("id", 0L);

        ContactEntity entity = realm.where(ContactEntity.class).equalTo("id", id).findFirst();
        Log.d("Entity", entity.toString());

        assembly(entity);
    }

    private void assembly(ContactEntity entity) {
        ((TextView) findViewById(R.id.name)).setText(entity.getName());
        ((TextView) findViewById(R.id.surname)).setText(entity.getSurname());
        ((TextView) findViewById(R.id.age)).setText(String.valueOf(entity.getAge()));
        ((TextView) findViewById(R.id.phoneNumber)).setText(entity.getPhoneNumber());
    }
}
