package main;

import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;

import java.util.HashMap;

public class Main
{
    HashMap<String,Recipe> recipes = new HashMap<String,Recipe>();
    HashMap<String,Craft> totalCrafts = new HashMap<String,Craft>();

    MutableValueGraph<String,Boolean> craftGraph = ValueGraphBuilder.directed().build();

    
}
