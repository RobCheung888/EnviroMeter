package com.example.envirometer.storage;

public class Task
{
    private String goal;
    private String currentState;
    private String completeState;
    private int pointsWorth;

    public Task(String goal, String  currentState, String completeState, int pointsWorth)
    {
        this.goal = goal;
        this.currentState = currentState;
        this.completeState = completeState;
        this.pointsWorth = pointsWorth;
    }

    public String getGoal()
    {
        return goal;
    }

    public void setGoal(String goal)
    {
        this.goal = goal;
    }

    public String getCurrentState()
    {
        return currentState;
    }

    public void setCurrentState(String currentState)
    {
        this.currentState = currentState;
    }

    public String getCompleteState()
    {
        return completeState;
    }

    public void setCompleteState(String completeState)
    {
        this.completeState = completeState;
    }

    public int getPointsWorth() {
        return pointsWorth;
    }

    public void setPointsWorth(int pointsWorth) {
        this.pointsWorth = pointsWorth;
    }

    @Override
    public String toString()
    {
        return "Target{" +
                "goal='" + goal + '\'' +
                ", currentState=" + currentState +
                ", completeState=" + completeState +
                ", pointsWorth=" + pointsWorth +
                '}';
    }
}
