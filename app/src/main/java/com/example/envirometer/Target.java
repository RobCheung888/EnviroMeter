package com.example.envirometer;

public class Target
{
    private String goal;
    private String currentState;
    private String completeState;

    public Target(String goal, String  currentState, String completeState)
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
