package main;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Scanner;

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

    public Recipe(String stringRecipe) throws InvalidParameterException, NumberFormatException
    {
        ArrayList<Slot> tempRecipeArr = new ArrayList<Slot>();
        ArrayList<Boolean> tempConsumesArr = new ArrayList<Boolean>();

        Scanner sc = new Scanner(stringRecipe);

        if (!sc.hasNext() || !sc.next().equals("yield"))
            throw new InvalidParameterException("recipe string: must start with \"yield\"");

        if (!sc.hasNextInt())
            throw new NumberFormatException("recipe string: yield int malformed");
        this.yield = sc.nextInt();

        while (sc.hasNext())
        {
            String next = sc.next();
            if (next.equals("{"))
            {
                if (sc.hasNext())
                    next = sc.next();
                else
                    throw new InvalidParameterException("recipe string: ended too soon");
                if (next.equals("}"))
                    throw new InvalidParameterException("recipe string: missing amt");
                int amt = Integer.parseInt(next);

                if (sc.hasNext())
                    next = sc.next();
                else
                    throw new InvalidParameterException("recipe string: ended too soon");
                if (next.equals("}"))
                    throw new InvalidParameterException("recipe string: missing itemID");
                String itemID = next;

                if (sc.hasNext())
                    next = sc.next();
                else
                    throw new InvalidParameterException("recipe string: ended too soon");
                if (next.equals("}"))
                    throw new InvalidParameterException("recipe string: missing consumed");
                boolean consumed = !next.equals("n");

                if (!sc.hasNext())
                    throw new InvalidParameterException("recipe string: ended too soon");
                if (!sc.next().equals("}"))
                    throw new InvalidParameterException("recipe string: unclosed brace");

                tempRecipeArr.add(new Slot(amt,itemID));
                tempConsumesArr.add(consumed);
            } else if (next.equals("__emptySlot"))
            {
                tempRecipeArr.add(new Slot());
                tempConsumesArr.add(true);
            } else
            {
                tempRecipeArr.add(new Slot(1,next));
                tempConsumesArr.add(true);
            }
        }

        this.recipeArr = new Slot[tempRecipeArr.size()];
        this.consumesCraft = new boolean[tempRecipeArr.size()];
        for (int i = 0; i < tempRecipeArr.size(); i++) {
            this.recipeArr[i] = tempRecipeArr.get(i);
            this.consumesCraft[i] = tempConsumesArr.get(i);
        }
    }
}
