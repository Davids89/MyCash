package luque.david.mycash.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import luque.david.mycash.Models.Cash;
import luque.david.mycash.R;

/**
 * Created by David on 24/10/15.
 */
public class CashAdapter extends RecyclerView.Adapter<CashAdapter.CashAdapterHolder> {

    private List<Cash> mList;

    public static class CashAdapterHolder extends RecyclerView.ViewHolder{

        public TextView value;

        public CashAdapterHolder(View v){
            super(v);
            value = (TextView) v.findViewById(R.id.valueCard);
        }
    }

    public CashAdapter(List<Cash> items){
        this.mList = items;
    }

    @Override
    public CashAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cash_card, parent, false);

        return new CashAdapterHolder(v);
    }

    @Override
    public int getItemCount() {
        return this.mList.size();
    }

    @Override
    public void onBindViewHolder(CashAdapter.CashAdapterHolder holder, int position) {

        if(mList.get(position).getValue() != null){
            holder.value.setText(mList.get(position).getValue().toString());
        }else{
            holder.value.setText("No hay resultados");
        }
    }
}
