package com.example.envirometer;

public class Target
{
    private String goal;
    private int currentState;
    private int completeState;

    public Target(String goal, int currentState, int completeState)
    {
        this.goal = goal;
        this.currentState = currentState;
        this.completeState = completeState;
    }

    public String getGoal()
    {
        return goal;
    }

    public void setGoal(String goal)
    {
        this.goal = goal;
    }

    public int getCurrentState()
    {
        return currentState;
    }

    public void setCurrentState(int currentState)
    {
        this.currentState = currentState;
    }

    public int getCompleteState()
    {
        return completeState;
    }

    public void setCompleteState(int completeState)
    {
        this.completeState = completeState;
    }

    @Override
    public String toString()
    {
        return "Target{" +
                "goal='" + goal + '\'' +
                ", currentState=" + currentState +
                ", completeState=" + completeState +
                '}';
    }
}
