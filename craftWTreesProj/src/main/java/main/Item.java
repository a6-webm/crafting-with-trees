package main;

import java.security.InvalidParameterException;
import java.util.HashMap;

public class Item
{
    private static HashMap<String,Item> idToItemMap = new HashMap<String,Item>();

    private final String id;
    private final String name;
    private final boolean doCraft;
    private final Recipe recipe;

    public static Item getItem(String id) throws InvalidParameterException
    {
        if (!idToItemMap.containsKey(id))
            throw new InvalidParameterException("item: " + id + " does not exist");
        return idToItemMap.get(id);
    }

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public boolean isDoCraft()
    {
        return doCraft;
    }

    public Recipe getRecipe()
    {
        return recipe;
    }

    public Item(String id, String name, boolean doCraft, Recipe recipe)
    {
        this.id = id;
        this.name = name;
        this.doCraft = doCraft;
        this.recipe = recipe;
        idToItemMap.put(id,this);
    }
}
