package main;

import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;

import java.util.HashMap;

public class Main
{

    public static void main(String[] args)
    {

    }

    static String makeCraftItinerary(Slot itemToBeCrafted)
    {
        HashMap<String,Craft> totalCrafts = new HashMap<String,Craft>();

        MutableValueGraph<String,Boolean> craftGraph = ValueGraphBuilder.directed().build();

        return "";
    }
}
