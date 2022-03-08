package main;

import java.util.HashMap;

public class Item
{
    private static HashMap<String,Item> idToItemMap;

    private String id;
    private String name;
    private boolean doCraft;
    private Recipe recipe;

    public static Item getItem(String id)
    {
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
