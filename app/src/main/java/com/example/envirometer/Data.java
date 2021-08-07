package com.example.envirometer;

import java.util.ArrayList;

public class Data
{
    private ArrayList<Target> task = new ArrayList<>();

    public Data(ArrayList<Target> task)
    {
        this.task = task;
        task.add(new Target("Turn Off AC", "12", "50"));
    }

    public ArrayList<Target> getTask()
    {
        return task;
    }

    public void setTask(ArrayList<Target> task)
    {
        this.task = task;
    }
}
