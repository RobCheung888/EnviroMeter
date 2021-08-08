package com.example.envirometer.data;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.envirometer.R;

import java.util.ArrayList;

public class TaskRecycleView extends RecyclerView.Adapter<TaskRecycleView.ViewHolder>
{
    private ArrayList<Task> goal = new ArrayList<>();
    private Context context;

    public TaskRecycleView(Context context)
    {
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtGoal;
        private CardView parent;
        private TextView currentState, completeState, amountWorth;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            txtGoal = itemView.findViewById(R.id.goalname);
            parent = itemView.findViewById(R.id.parent);
            currentState = itemView.findViewById(R.id.completed);
            completeState = itemView.findViewById(R.id.target);
            amountWorth = itemView.findViewById(R.id.size);
        }
    }

    @NonNull
    @Override
    public TaskRecycleView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_target,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public void setGoal(ArrayList<Task> goals)
    {
        this.goal = goals;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull TaskRecycleView.ViewHolder holder, int position)
    {
          holder.txtGoal.setText(goal.get(position).getGoal());
          holder.currentState.setText(goal.get(position).getCurrentState());
          holder.completeState.setText(goal.get(position).getCompleteState());
          holder.amountWorth.setText(goal.get(position).getPointsWorth() + " mL");
    }

    @Override
    public int getItemCount()
    {
        return goal.size();
    }


}
