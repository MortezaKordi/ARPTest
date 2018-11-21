package morteza.packag.com.arptest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    private final List<Entry> entries = new ArrayList<>();

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                    int viewType) {
        View myView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(myView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Entry entry = entries.get(position);
        holder.setEntryName(entry.getEntryName());
        holder.setEntryPrice(entry.getEntryPrice());
        holder.setEntryDate(entry.getEntryDate());

    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public void addEntry(Entry entry) {
        this.entries.add(entry);
        notifyItemInserted(entries.size() - 1);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtEntryName)
        TextView entryName;
        @BindView(R.id.txtEntryPrice)
        TextView entryPrice;
        @BindView(R.id.txtEntryDate)
        TextView entryDate;

        private final NumberFormat ENTRY_PRICE_FORMAT = new DecimalFormat("#0.00");

        public void setEntryName(String en) {
            this.entryName.setText(en);
        }
        private void setEntryPrice(BigDecimal ep) {

            this.entryPrice.setText(ENTRY_PRICE_FORMAT.format(ep.doubleValue()));
        }
        private void setEntryDate(Date ed) {
            this.entryDate.setText(android.text.format.DateFormat.format("yyyy-MM-dd hh:mm", ed));
        }

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
