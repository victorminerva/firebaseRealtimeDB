package com.victorminerva.app.firebaserealtimedb.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.victorminerva.app.firebaserealtimedb.R;
import com.victorminerva.app.firebaserealtimedb.model.Loan;

import java.util.List;

/**
 * Created by victo on 07/12/2017.
 */

public class ItemLoanAdapter extends BaseAdapter {

    private List<Loan> loans;
    private final Activity act;

    public ItemLoanAdapter(List<Loan> loans, Activity act) {
        this.loans = loans;
        this.act = act;
    }

    @Override
    public int getCount() {
        return loans.size();
    }

    @Override
    public Object getItem(int i) {
        return loans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = act.getLayoutInflater().inflate(R.layout.item_main, viewGroup, false);

        Loan loan = loans.get(i);

        //pegando as referências das Views
        TextView nome = view.findViewById(R.id.name);
        TextView descricao = view.findViewById(R.id.what);

        //populando as Views
        nome.setText(loan.getFavored().getName());
        descricao.setText(loan.getWhat());

        return view;
    }
}
