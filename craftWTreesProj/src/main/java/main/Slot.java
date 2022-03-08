package main;

public class Slot
{
    private int amt;
    private String itemID;

    public int getAmt()
    {
        return amt;
    }

    public String getItemID()
    {
        return itemID;
    }

    public Slot(int amt, String itemID)
    {
        this.amt = amt;
        this.itemID = itemID;
    }
}
