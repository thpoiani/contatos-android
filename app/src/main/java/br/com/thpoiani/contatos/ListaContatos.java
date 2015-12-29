package br.com.thpoiani.contatos;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.util.*;

import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

import br.com.thpoiani.contatos.entity.ContactEntity;
import br.com.thpoiani.contatos.pojo.Contact;
import io.realm.Realm;

public class ListaContatos extends ListActivity {

    private static final String PEOPLE_API = "http://private-61391-person9.apiary-mock.com/people";
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();

        if (isNetworkAvailable(Contatos.getAppContext())) {
            Log.d("Network", "Available");

            Ion.with(ListaContatos.this)
                    .load(PEOPLE_API)
                    .setLogging("PEOPLE_API", Log.DEBUG)
                    .as(new TypeToken<List<Contact>>() {
                    })
                    .setCallback(new FutureCallback<List<Contact>>() {
                        @Override
                        public void onCompleted(Exception e, List<Contact> contacts) {
                            ListaContatosAdapter adapter = new ListaContatosAdapter(ListaContatos.this, contacts);
                            setListAdapter(adapter);

                            realm.beginTransaction();
                            realm.copyToRealmOrUpdate(getContactEntitiesFromContacts(contacts));
                            realm.commitTransaction();
                        }
                    });
        } else {
            Log.d("Network", "Unavailable");

            List<ContactEntity> entities = realm.where(ContactEntity.class).findAll();
            ListaContatosAdapter adapter = new ListaContatosAdapter(ListaContatos.this, getContactsFromEntities(entities));
            setListAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista_contatos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return item.getItemId() == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    public void buttonClick(View v) {
        Intent intent = new Intent(ListaContatos.this, DetalhesContato.class);
        TextView id   = (TextView) v.findViewById(R.id.id);

        intent.putExtra("id", Long.parseLong(id.getText().toString()));
        startActivity(intent);
    }

    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        return info != null && info.isConnected() && info.isAvailable();
    }

    @NonNull
    private List<Contact> getContactsFromEntities(List<ContactEntity> entities) {
        List<Contact> contacts = new ArrayList<>();
        for (ContactEntity entity : entities) {
            contacts.add(new Contact(entity));
        }
        return contacts;
    }

    @NonNull
    private List<ContactEntity> getContactEntitiesFromContacts(List<Contact> contacts) {
        List<ContactEntity> entities = new ArrayList<>();
        for (Contact contact : contacts) {
            entities.add(new ContactEntity(contact));
        }
        return entities;
    }
}
