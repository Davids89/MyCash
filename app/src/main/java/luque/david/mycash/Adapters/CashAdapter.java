package luque.david.mycash.Adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

        private final TextView subject;
        public TextView value;
        public ImageView image;

        public CashAdapterHolder(View v){
            super(v);
            value = (TextView) v.findViewById(R.id.valueCard);
            image = (ImageView) v.findViewById(R.id.imageCard);
            subject = (TextView) v.findViewById(R.id.subject_card);
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

        if(mList.get(position).getmValue() != null){

            String category = mList.get(position).getmCategory();

            if(category.equals("Ropa")){
                holder.image.setImageResource(R.mipmap.ic_clothes);
            }else if(category.equals("Restaurantes")){
                holder.image.setImageResource(R.mipmap.ic_restaurant);
            }else if(category.equals("Coche")){
                holder.image.setImageResource(R.mipmap.ic_car);
            }else if(category.equals("Comida")){
                holder.image.setImageResource(R.mipmap.ic_food);
            }else if(category.equals("Transporte")){
                holder.image.setImageResource(R.mipmap.ic_transport);
            }else if(category.equals("Salud")){
                holder.image.setImageResource(R.mipmap.ic_health);
            }else if(category.equals("Servicios")){
                holder.image.setImageResource(R.mipmap.ic_services);
            }else if(category.equals("Animales")){
                holder.image.setImageResource(R.mipmap.ic_pets);
            }else if(category.equals("Regalos")){
                holder.image.setImageResource(R.mipmap.ic_gift);
            }

            if(mList.get(position).getmValue() > 0){
                holder.value.setTextColor(Color.parseColor("#43A047"));
            }else if(mList.get(position).getmValue() < 0){
                holder.value.setTextColor(Color.parseColor("#D32F2F"));
            }

            holder.value.setText(mList.get(position).getmValue().toString());

            holder.subject.setText(mList.get(position).getmSubject());
        }else{
            holder.image.setImageResource(R.mipmap.ic_error);
            holder.value.setText("No hay resultados");
        }
    }
}
