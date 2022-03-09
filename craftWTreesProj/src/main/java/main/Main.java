package main;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;

import java.util.HashMap;

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
        MutableGraph<String> craftGraph = GraphBuilder.directed().build();

        populateCraftGraph(itemToBeCrafted.getItemID(),craftGraph);

        for (String node : craftGraph.nodes())
            totalCrafts.put(node, new Craft());

        populateTotalCrafts(itemToBeCrafted, craftGraph, totalCrafts);

        // TODO finish

        return "";
    }

    // TODO doesn't work, possibly wrong
    private static void populateTotalCrafts(Slot itemToBeCrafted, MutableGraph<String> craftGraph, HashMap<String,Craft> totalCrafts)
    {
        Item item = Item.getItem(itemToBeCrafted.getItemID());
        if (item.isDoCraft())
        {
            Recipe recipe = item.getRecipe();
            Craft craft = totalCrafts.get(item.getId());

            craft.numRequired += itemToBeCrafted.getAmt();

            int newNumCrafts = craft.numRequired % recipe.getYield();
            int craftDiff = newNumCrafts - craft.numOfCrafts;

            if (newNumCrafts != craft.numRequired)
            {
                craft.numOfCrafts = newNumCrafts;
                for (Slot recipeSlot : recipe.getRecipeArr())
                {
                    int amt = recipeSlot.getAmt() * craftDiff; // TODO Left off here
                    populateTotalCrafts(new Slot(amt,recipeSlot.getItemID()), craftGraph, totalCrafts);
                }
            }
        } else
        {
            Craft craft = totalCrafts.get(item.getId());
            craft.numRequired += itemToBeCrafted.getAmt();
        }
    }

    private static void populateCraftGraph(String itemID, MutableGraph<String> craftGraph)
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
                if (!craftGraph.hasEdgeConnecting(ingredientID,itemID))
                    craftGraph.putEdge(ingredientID,itemID);
            }
        }
    }
}
