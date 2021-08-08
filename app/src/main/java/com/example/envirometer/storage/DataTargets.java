package com.example.envirometer.storage;

import java.util.ArrayList;

public class DataTargets
{
    private ArrayList<Task> tasks = new ArrayList<>();

    public DataTargets()
    {
        tasks.add(new Task("Turn off AC", "12", "50", 50));
        tasks.add(new Task("Go bike-riding", "5", "10", 70));
        tasks.add(new Task("Clean up litter", "2", "12", 90));
        tasks.add(new Task("Take public transit", "4", "20", 60));
        tasks.add(new Task("Open windows", "5", "10", 40));
        tasks.add(new Task("Turn off lights", "1", "10", 20));
    }

    public ArrayList<Task> getTasks()
    {
        return tasks;
    }
}
