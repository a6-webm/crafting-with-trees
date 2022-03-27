package main;

import com.google.common.graph.*;
import java.util.*;

public class CraftingProcess {
    HashMap<String,Craft> totalCrafts;
    MutableValueGraph<String,Integer> craftGraph;
    ArrayList<String> craftOrder;

    public CraftingProcess(Slot... slots) {
        this.totalCrafts = new HashMap<>();
        this.craftGraph = ValueGraphBuilder.directed().build();
        this.craftOrder = new ArrayList<>();

        populateCraftGraph(slots);

        for (String node : craftGraph.nodes())
            totalCrafts.put(node, new Craft());

        populateTotalCrafts();
    }

    private void populateCraftGraph(Slot[] slots) {
        for (Slot slot : slots) {
            String ingredientID = slot.getItemID();
            if (!ingredientID.isEmpty()) {
                if (!craftGraph.nodes().contains(ingredientID))
                    populateCraftGraphRecurse(ingredientID);
                if (!craftGraph.hasEdgeConnecting("",ingredientID)) {
                    craftGraph.putEdgeValue("",ingredientID,slot.getAmt());
                } else {
                    int updatedVal = slot.getAmt() + craftGraph.edgeValueOrDefault("",ingredientID,0);
                    craftGraph.putEdgeValue("",ingredientID,updatedVal);
                }
            }
        }
    }

    private void populateCraftGraphRecurse(String itemID) {
        Item item = Item.getItem(itemID);
        if (item.isDoCraft()) {
            Slot[] recipeArr = item.getRecipe().getRecipeArr();
            for (Slot slot : recipeArr) {
                String ingredientID = slot.getItemID();
                if (!ingredientID.isEmpty()) {
                    if (!craftGraph.nodes().contains(ingredientID))
                        populateCraftGraphRecurse(ingredientID);
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

    private void populateTotalCrafts() {
        for (String ingredItemID : craftGraph.adjacentNodes("")) {
            int ingredNumRequired = craftGraph.edgeValueOrDefault("", ingredItemID, 0);
            populateTotalCraftsRecurse(ingredNumRequired, ingredItemID);
        }
    }

    private void populateTotalCraftsRecurse(int amtToCraft, String itemIDToCraft) {
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
                        populateTotalCraftsRecurse(amt, ingredItemID);
                    }
                }
            }
        } else {
            craft.numRequired += amtToCraft;
        }
    }

    @Override
    public String toString() {
        String out = "Crafts: \n";
        int stepIt = craftOrder.size();
        for (String itemID : craftOrder) {
            Craft craft = totalCrafts.get(itemID);
            Item item = Item.getItem(itemID);
            String itemName = item.getName();

            out += "|Step " + stepIt + "| " + itemName + ": crafts:" + craft.numOfCrafts + "\n";
            stepIt--;
        }

        out += "\nResources: \n";
        for (Map.Entry<String,Craft> entry : totalCrafts.entrySet()) {
            String itemID = entry.getKey();
            if (!itemID.equals("")) {
                Item item = Item.getItem(itemID);
                Craft craft = entry.getValue();

                if (!item.isDoCraft())
                    out += itemID + ": " + craft.numRequired + "\n";
            }
        }
        return out;
    }
}
