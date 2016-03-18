package kize.meteolpdw;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.viewHolder> {
    private ArrayList<Item> item;
    private OnClickListener listener;

    public ListAdapter(ArrayList<Item> item, OnClickListener listener) {
        this.item = item;
        this.listener = listener;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.town_view, parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        holder.cityName.setText(item.get(position).name);
        holder.regionName.setText(item.get(position).pays);
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public void removeTown(int position){
        item.remove(position);
        notifyItemRemoved(position);
    }

    public class viewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        TextView cityName;
        TextView regionName;

        public viewHolder(View itemView) {
            super(itemView);
            cityName = (TextView) itemView.findViewById(R.id.view1);
            regionName = (TextView) itemView.findViewById(R.id.view2);

            itemView.setOnClickListener(this);
        }

        public void onClick(View v){
            if(listener != null){
                int position= getAdapterPosition();
                listener.onClick(position, item.get(position));
            }
        }
    }

    public interface OnClickListener{
        void onClick(int position, Item item);
    }
}