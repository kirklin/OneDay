package name.lkk.oneday.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import name.lkk.oneday.R;
import name.lkk.oneday.data.Check;

public class CheckAdapter extends RecyclerView.Adapter<CheckAdapter.MyViewHolder> {
    List<Check> allchecks = new ArrayList<>();

    public void setAllchecks(List<Check> allchecks) {
        this.allchecks = allchecks;
    }

    public CheckAdapter() {
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView;
        itemView = layoutInflater.inflate(R.layout.cell_check_card, parent, false);
        return new CheckAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Check check = allchecks.get(position);
        holder.textViewCheckTitle.setText(check.getTitle());
        holder.textViewCheckContent.setText(check.getContents());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("check",check);
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_checkFragment_to_editCheckFragment,bundle);
            }
        });
    }


    @Override
    public int getItemCount() {
        return allchecks.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewCheckTitle,textViewCheckContent;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCheckTitle = itemView.findViewById(R.id.textViewCheckTitle);
            textViewCheckContent = itemView.findViewById(R.id.textViewCheckContent);
        }
    }
}
