package main;

public class Recipe
{
    private int yield;
    private Slot[] recipeArr;

    public int getYield()
    {
        return yield;
    }

    public Slot[] getRecipeArr()
    {
        return recipeArr;
    }

    public Recipe()
    {
        this.yield = 0;
        this.recipeArr = new Slot[0];
    }

    public Recipe(int yield, Slot[] recipeArr)
    {
        this.yield = yield;
        this.recipeArr = recipeArr;
    }
}
