public class CraftNode
{
    private Slot toCraft;
    private CraftNode[] childNodes;

    public CraftNode(Slot toCraft)
    {
        this.toCraft = toCraft;
        this.childNodes = new CraftNode[toCraft.getItem().getRecipe().getRecipeArr().length];
        for (Slot recipeSlot : toCraft.getItem().getRecipe().getRecipeArr())
        {

        }
    }
}
