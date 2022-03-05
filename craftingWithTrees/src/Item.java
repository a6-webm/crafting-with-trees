public class Item
{
    
    private String id;
    private String name;
    private boolean doCraft;
    private Recipe recipe;

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
}
