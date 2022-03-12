package main;

import com.google.common.graph.*;

import java.util.HashMap;
import java.util.Map;

public class Main
{

    public static void main(String[] args)
    {
        initialiseItems();
        System.out.println(makeCraftItinerary(new Slot(1,"wooden_axe")));
    }

    private static void initialiseItems()
    {
        new Item("oak_log",
                "Oak log",
                false,
                new Recipe()
        );
        new Item("wooden_plank",
                "Wooden plank",
                true,
                new Recipe(4, new Slot[]{new Slot(1, "oak_log")})
        );
        new Item("stick",
                "Stick",
                true,
                new Recipe(4, new Slot[]{new Slot(2, "wooden_plank")})
        );
        new Item("wooden_axe",
                "Wooden axe",
                true,
                new Recipe(1, new Slot[]{new Slot(3, "wooden_plank"), new Slot(2,"stick")})
        );
    }

    private static String makeCraftItinerary(Slot itemToBeCrafted)
    {
        HashMap<String,Craft> totalCrafts = new HashMap<String,Craft>();
        MutableValueGraph<String,Integer> craftGraph = ValueGraphBuilder.directed().build();

        populateCraftGraph(itemToBeCrafted.getItemID(),craftGraph);

        for (String node : craftGraph.nodes())
            totalCrafts.put(node, new Craft());

        populateTotalCrafts(itemToBeCrafted.getAmt(), itemToBeCrafted.getItemID(), craftGraph, totalCrafts);

        return craftItineraryStringMaker(craftGraph,totalCrafts);
    }

    // TODO make sure crafts are printed step by step, i.e. in some realistic order
    private static String craftItineraryStringMaker(MutableValueGraph<String,Integer> craftGraph, HashMap<String,Craft> totalCrafts)
    {
        String out = "";
        for (Map.Entry<String,Craft> entry : totalCrafts.entrySet())
        {
            String itemID = entry.getKey();
            Craft craft = entry.getValue();
            Item item = Item.getItem(itemID);
            String itemName = item.getName();

            out += itemName + ": crafts:" + craft.numOfCrafts + ", required:" + craft.numRequired + "\n";
        }
        return out;
    }

    private static void populateTotalCrafts(int amtToCraft, String itemIDToCraft, MutableValueGraph<String,Integer> craftGraph, HashMap<String,Craft> totalCrafts)
    {
        Item item = Item.getItem(itemIDToCraft);
        Craft craft = totalCrafts.get(itemIDToCraft);
        if (item.isDoCraft())
        {
            Recipe recipe = item.getRecipe();

            craft.numRequired += amtToCraft;

            int newNumCrafts = (craft.numRequired - 1) / recipe.getYield() + 1;
            int craftDiff = newNumCrafts - craft.numOfCrafts;

            if (craftDiff > 0)
            {
                craft.numOfCrafts = newNumCrafts;
                for (String ingredItemID : craftGraph.adjacentNodes(itemIDToCraft))
                {
                    int ingredNumRequired = craftGraph.edgeValueOrDefault(itemIDToCraft,ingredItemID,0);
                    int amt = ingredNumRequired * craftDiff;
                    populateTotalCrafts(amt, ingredItemID, craftGraph, totalCrafts);
                }
            }
        } else
        {
            craft.numRequired += amtToCraft;
        }
    }

    private static void populateCraftGraph(String itemID, MutableValueGraph<String,Integer> craftGraph)
    {
        Item item = Item.getItem(itemID);
        if (item.isDoCraft())
        {
            Slot[] recipeArr = item.getRecipe().getRecipeArr();
            for (Slot slot : recipeArr)
            {
                String ingredientID = slot.getItemID();
                if (!craftGraph.nodes().contains(ingredientID))
                    populateCraftGraph(ingredientID, craftGraph);
                if (!craftGraph.hasEdgeConnecting(itemID,ingredientID))
                {
                    craftGraph.putEdgeValue(itemID,ingredientID,slot.getAmt());
                } else
                {
                    int updatedVal = slot.getAmt() + craftGraph.edgeValueOrDefault(itemID,ingredientID,0);
                    craftGraph.putEdgeValue(itemID,ingredientID,updatedVal);
                }
            }
        }
    }
}
