package com.victorminerva.app.firebaserealtimedb.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.victorminerva.app.firebaserealtimedb.R;
import com.victorminerva.app.firebaserealtimedb.model.Favored;

import java.util.List;

/**
 * Created by victo on 09/12/2017.
 */

public class ItemFavoredAdapter extends BaseAdapter {

    private List<Favored> favoreds;
    private final Activity act;

    public ItemFavoredAdapter(List<Favored> favoreds, Activity act) {
        this.favoreds = favoreds;
        this.act = act;
    }

    @Override
    public int getCount() {
        return favoreds.size();
    }

    @Override
    public Object getItem(int i) {
        return favoreds.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = act.getLayoutInflater().inflate(R.layout.item_favored, viewGroup, false);

        Favored favored = favoreds.get(i);
        //pegando as referências das Views
        TextView nome = view.findViewById(R.id.name_favored);
        TextView email = view.findViewById(R.id.email_favored);
        TextView phone = view.findViewById(R.id.phone_favored);

        //populando as Views
        nome.setText(favored.getName());
        email.setText(favored.getEmail());
        if(favored.getNumberPhone() != null) {
            phone.setText(String.valueOf(favored.getNumberPhone()));
        }

        return view;
    }
}
