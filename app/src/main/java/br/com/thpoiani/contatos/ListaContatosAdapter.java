package br.com.thpoiani.contatos;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import java.util.List;

import br.com.thpoiani.contatos.pojo.Contact;

public class ListaContatosAdapter extends ArrayAdapter {
    private LayoutInflater inflater;

    public ListaContatosAdapter(Activity activity, List<Contact> objects)	{
        super(activity, R.layout.contato_cell, objects);
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.contato_cell, null);
            viewHolder  = new ViewHolder();

            setViewHolder(convertView, viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        assemblyContatoInViewHolderByPosition(viewHolder, position);

        return convertView;
    }

    private void setViewHolder(View convertView, ViewHolder viewHolder) {
        viewHolder.id      = (TextView) convertView.findViewById(R.id.id);
        viewHolder.name    = (TextView) convertView.findViewById(R.id.name);
        viewHolder.surname = (TextView) convertView.findViewById(R.id.surname);
        viewHolder.image   = (ImageView) convertView.findViewById(R.id.image);

        convertView.setTag(viewHolder);
    }

    private void assemblyContatoInViewHolderByPosition(ViewHolder holder, int position) {
        Contact contato = (Contact) getItem(position);

        holder.id.setText(String.valueOf(contato.getId()));
        holder.name.setText(contato.getName());
        holder.surname.setText(contato.getSurname());
        Ion.with(super.getContext()).load(contato.getImage()).intoImageView(holder.image);
    }

    private static class ViewHolder {
        public TextView id;
        public TextView name;
        public TextView surname;
        public ImageView image;
    }
}
