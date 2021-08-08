package com.example.envirometer.data;

import java.util.ArrayList;

public class DataTargets
{
    private ArrayList<Task> tasks = new ArrayList<>();

    public DataTargets()
    {
        tasks.add(new Task("Turn off AC", "0", "50", 50));
        tasks.add(new Task("Go bike-riding", "0", "10", 70));
        tasks.add(new Task("Clean up litter", "0", "12", 90));
        tasks.add(new Task("Take public transit", "0", "20", 60));
        tasks.add(new Task("Open windows", "0", "10", 40));
        tasks.add(new Task("Turn off lights", "0", "10", 20));
    }

    public ArrayList<Task> getTasks()
    {
        return tasks;
    }
}
