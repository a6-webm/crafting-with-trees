public class CraftNode
{
    private Slot toCraft;
    private CraftNode[] childNodes;

    public CraftNode(Slot toCraft)
    {
        this.toCraft = toCraft;
        this.childNodes = new CraftNode[Item.getItem(toCraft.getItemID()).getRecipe().getRecipeArr().length];

        for (int i = 0; i < childNodes.length; i++) // TODO this might be a little hasty, possibly leave this functionality in CraftTree
        {
            Slot st = Item.getItem(toCraft.getItemID()).getRecipe().getRecipeArr()[i];
            childNodes[i] = new CraftNode(st);
        }
    }
}
