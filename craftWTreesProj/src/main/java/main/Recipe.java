package main;

public class Recipe
{
    private int yield;
    private Slot[] recipeArr;
    private boolean[] consumesCraft;

    public int getYield()
    {
        return yield;
    }

    public Slot[] getRecipeArr()
    {
        return recipeArr;
    }

    public boolean[] getConsumesCraft()
    {
        return consumesCraft;
    }

    public Recipe()
    {
        this.yield = 0;
        this.recipeArr = new Slot[0];
        this.consumesCraft = new boolean[0];
    }

    public Recipe(int yield, Slot[] recipeArr)
    {
        this.yield = yield;
        this.recipeArr = recipeArr;
        this.consumesCraft = new boolean[recipeArr.length];
        for (int i = 0; i < this.consumesCraft.length; i++)
            this.consumesCraft[i] = true;
    }

    public Recipe(int yield, Slot[] recipeArr, boolean[] consumesCraft)
    {
        this.yield = yield;
        this.recipeArr = recipeArr;
        this.consumesCraft = consumesCraft;
    }
}
