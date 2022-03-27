package main;

import com.google.common.graph.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        initialiseItems();
        System.out.println(makeCraftItinerary(new Slot(1,"wooden_axe")));
    }

    private static void initialiseItems() {
        new Item(
            "oak_log",
            "Oak log",
            false,
            new Recipe()
        );
        new Item(
            "wooden_plank",
            "Wooden plank",
            true,
            new Recipe(true,4,"oak_log")
        );
        new Item(
            "stick",
            "Stick",
            true,
            new Recipe(
                false,4,
                "wooden_plank __emptySlot __emptySlot " +
                          "wooden_plank"
            )
        );
        new Item(
            "wooden_axe",
            "Wooden axe",
            true,
            new Recipe(
                false,1,
                "__emptySlot wooden_plank wooden_plank " +
                          "__emptySlot    stick     wooden_plank " +
                          "__emptySlot    stick     __emptySlot"
            )
        );
    }

    private static String makeCraftItinerary(Slot itemToBeCrafted) {
        HashMap<String,Craft> totalCrafts = new HashMap<String,Craft>();
        MutableValueGraph<String,Integer> craftGraph = ValueGraphBuilder.directed().build();

        populateCraftGraph(itemToBeCrafted.getItemID(),craftGraph);

        for (String node : craftGraph.nodes())
            totalCrafts.put(node, new Craft());

        ArrayList<String> craftOrder = new ArrayList();

        populateTotalCrafts(itemToBeCrafted.getAmt(), itemToBeCrafted.getItemID(), craftGraph, totalCrafts, craftOrder);

        return craftItineraryStringMaker(craftGraph,totalCrafts, craftOrder);
    }

    private static String craftItineraryStringMaker(MutableValueGraph<String,Integer> craftGraph, HashMap<String,Craft> totalCrafts, ArrayList<String> craftOrder) {
        String out = "";
        int stepIt = craftOrder.size();
        for (String itemID : craftOrder) {
            Craft craft = totalCrafts.get(itemID);
            Item item = Item.getItem(itemID);
            String itemName = item.getName();

            out += "|Step " + stepIt + "| " + itemName + ": crafts:" + craft.numOfCrafts + "\n";
            stepIt--;
        }
        return out;
    }
    
    private static void populateTotalCrafts(
            int amtToCraft, String itemIDToCraft,
            MutableValueGraph<String,
            Integer> craftGraph,
            HashMap<String,
            Craft> totalCrafts,
            ArrayList<String> craftOrder
    ) {
        Item item = Item.getItem(itemIDToCraft);
        Craft craft = totalCrafts.get(itemIDToCraft);
        if (item.isDoCraft()) {
            craftOrder.remove(itemIDToCraft);
            craftOrder.add(itemIDToCraft);

            Recipe recipe = item.getRecipe();

            craft.numRequired += amtToCraft;

            int newNumCrafts = (craft.numRequired - 1) / recipe.getYield() + 1;
            int craftDiff = newNumCrafts - craft.numOfCrafts;

            if (craftDiff > 0) {
                craft.numOfCrafts = newNumCrafts;
                for (String ingredItemID : craftGraph.adjacentNodes(itemIDToCraft)) {
                    if (craftGraph.hasEdgeConnecting(itemIDToCraft,ingredItemID)) {
                        int ingredNumRequired = craftGraph.edgeValueOrDefault(itemIDToCraft, ingredItemID, 0);
                        int amt = ingredNumRequired * craftDiff;
                        populateTotalCrafts(amt, ingredItemID, craftGraph, totalCrafts, craftOrder);
                    }
                }
            }
        } else {
            craft.numRequired += amtToCraft;
        }
    }

    private static void populateCraftGraph(String itemID, MutableValueGraph<String,Integer> craftGraph) {
        Item item = Item.getItem(itemID);
        if (item.isDoCraft()) {
            Slot[] recipeArr = item.getRecipe().getRecipeArr();
            for (Slot slot : recipeArr) {
                String ingredientID = slot.getItemID();
                if (!ingredientID.isEmpty()) {
                    if (!craftGraph.nodes().contains(ingredientID))
                        populateCraftGraph(ingredientID, craftGraph);
                    if (!craftGraph.hasEdgeConnecting(itemID,ingredientID)) {
                        craftGraph.putEdgeValue(itemID,ingredientID,slot.getAmt());
                    } else {
                        int updatedVal = slot.getAmt() + craftGraph.edgeValueOrDefault(itemID,ingredientID,0);
                        craftGraph.putEdgeValue(itemID,ingredientID,updatedVal);
                    }
                }
            }
        }
    }
}
