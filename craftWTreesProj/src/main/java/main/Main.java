package main;

import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;

import java.util.HashMap;

public class Main
{

    public static void main(String[] args)
    {
        initialiseItems();
        System.out.println(makeCraftItinerary(new Slot(1,"wooden_axe")));
    }

    static void initialiseItems()
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

    static String makeCraftItinerary(Slot itemToBeCrafted)
    {
        HashMap<String,Craft> totalCrafts = new HashMap<String,Craft>();
        MutableValueGraph<String,Boolean> craftGraph = ValueGraphBuilder.directed().build();

        return "";
    }
}
