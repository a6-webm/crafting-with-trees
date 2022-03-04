public class CraftTree
{
    private CraftNode root;

    public CraftNode getRoot()
    {
        return root;
    }

    public CraftTree(Slot root)
    {
        this.root = new CraftNode(root);
    }
}
