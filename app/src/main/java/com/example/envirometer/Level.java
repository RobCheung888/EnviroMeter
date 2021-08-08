package com.example.envirometer;

public class Level
{
    private int level;
    private int waterLevel;

    public Level(int level, int waterLevel)
    {
        this.level = level;
        this.waterLevel = waterLevel;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public int getWaterLevel()
    {
        return waterLevel;
    }

    public void setWaterLevel(int waterLevel)
    {
        this.waterLevel = waterLevel;
    }
}
