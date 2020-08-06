package name.lkk.oneday.adapter;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import name.lkk.oneday.MainViewModel;
import name.lkk.oneday.R;
import name.lkk.oneday.data.Day;


public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {
    List<Day> alldays = new ArrayList<>();


    public void setAlldays(List<Day> alldays) {
        this.alldays = alldays;
    }

    public MainAdapter() {
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView;
        itemView = layoutInflater.inflate(R.layout.cell_day_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        Day day = alldays.get(position);

        final Bundle bundle = new Bundle();
        bundle.putLong("arg_dayid",day.getDayId());
        holder.textViewNumber.setText(String.valueOf(position + 1));
        holder.day_title.setText(day.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("kktest", "onClick: ");
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_dayFragment_to_checkFragment,bundle);
            }
        });
    }


    @Override
    public int getItemCount() {
        return alldays.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNumber, day_title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNumber = itemView.findViewById(R.id.textViewNumber);
            day_title = itemView.findViewById(R.id.textViewMain);

        }
    }
}
