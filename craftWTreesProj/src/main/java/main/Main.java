package main;

import com.google.common.graph.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        initialiseItems();
        CraftingProcess woodenAxeProcess = new CraftingProcess(new Slot(1,"wooden_axe"));
        System.out.println(woodenAxeProcess);
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
}
