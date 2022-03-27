package main;

import com.google.common.graph.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        initialiseAE2Items();
        CraftingProcess theDrives = new CraftingProcess(
            new Slot(4,"16k_ME_Storage_Cell"),
            new Slot(4,"4k_ME_Storage_Cell"),
            new Slot(2,"1k_ME_Storage_Cell")
        );
        System.out.println(theDrives);
    }

    private static void initialiseAE2Items() {
        new Item(
                "64k_ME_Storage_Cell",
                "64k ME Storage Cell",
                true,
                new Recipe(
                        false,1,
                        "quartz_glass          redstone          quartz_glass " +
                                "redstone    64k_ME_Storage_Component   redstone " +
                                "iron_ingot                  iron_ingot                iron_ingot"
                )
        );
        new Item(
                "64k_ME_Storage_Component",
                "64k ME Storage Component",
                true,
                new Recipe(
                        false,1,
                        "glowstone_dust          calculation_processor          glowstone_dust " +
                                "16k_ME_Storage_Component       quartz_glass        16k_ME_Storage_Component " +
                                "glowstone_dust             16k_ME_Storage_Component        glowstone_dust"
                )
        );
        new Item(
                "16k_ME_Storage_Cell",
                "16k ME Storage Cell",
                true,
                new Recipe(
                        false,1,
                        "quartz_glass          redstone          quartz_glass " +
                                "redstone    16k_ME_Storage_Component   redstone " +
                                "iron_ingot                  iron_ingot                iron_ingot"
                )
        );
        new Item(
                "16k_ME_Storage_Component",
                "16k ME Storage Component",
                true,
                new Recipe(
                        false,1,
                        "glowstone_dust          calculation_processor          glowstone_dust " +
                                "4k_ME_Storage_Component       quartz_glass        4k_ME_Storage_Component " +
                                "glowstone_dust             4k_ME_Storage_Component        glowstone_dust"
                )
        );
        new Item(
                "4k_ME_Storage_Cell",
                "4k ME Storage Cell",
                true,
                new Recipe(
                        false,1,
                        "quartz_glass          redstone          quartz_glass " +
                                "redstone    4k_ME_Storage_Component   redstone " +
                                "iron_ingot                  iron_ingot                iron_ingot"
                )
        );
        new Item(
                "4k_ME_Storage_Component",
                "4k ME Storage Component",
                true,
                new Recipe(
                        false,1,
                        "redstone          calculation_processor          redstone " +
                                "1k_ME_Storage_Component       quartz_glass        1k_ME_Storage_Component " +
                                "redstone             1k_ME_Storage_Component        redstone"
                )
        );
        new Item(
                "1k_ME_Storage_Cell",
                "1k ME Storage Cell",
                true,
                new Recipe(
                        false,1,
                        "quartz_glass          redstone          quartz_glass " +
                                "redstone    1k_ME_Storage_Component   redstone " +
                                "iron_ingot                  iron_ingot                iron_ingot"
                )
        );
        new Item(
                "1k_ME_Storage_Component",
                "1k ME Storage Component",
                true,
                new Recipe(
                        false,1,
                        "redstone          certus_quartz_crystal          redstone " +
                                "certus_quartz_crystal       logic_processor        certus_quartz_crystal " +
                                "redstone             certus_quartz_crystal        redstone"
                )
        );
        new Item(
                "calculation_processor",
                "Calculation Processor",
                true,
                new Recipe(
                    true,1,
                    "printed_calculation_circuit redstone printed_silicon"
                )
        );
        new Item(
                "printed_calculation_circuit",
                "Printed Calculation Circuit",
                true,
                new Recipe(
                    true,1,
                    "inscriber_calculation_press pure_certus_quartz_crystal"
                )
        );
        new Item(
                "pure_certus_quartz_crystal",
                "Pure Certus Quartz Crystal",
                true,
                new Recipe(
                        true,1,
                        "certus_quartz_crystal"
                )
        );
        new Item(
                "logic_processor",
                "Logic Processor",
                true,
                new Recipe(
                        true,1,
                        "printed_logic_circuit redstone printed_silicon"
                )
        );
        new Item(
                "printed_logic_circuit",
                "Printed Logic Circuit",
                true,
                new Recipe(
                        true,1,
                        "inscriber_calculation_press gold_ingot"
                )
        );
        new Item(
                "quartz_glass",
                "Quartz Glass",
                true,
                new Recipe(
                        false,4,
                        "nether_quartz_dust        glass          nether_quartz_dust " +
                                "glass                  nether_quartz_dust         glass " +
                                "nether_quartz_dust        glass             nether_quartz_dust"
                )
        );
        new Item(
                "nether_quartz_dust",
                "Nether Quartz Dust",
                true,
                new Recipe(
                        true,1,
                        "nether_quartz"
                )
        );
        new Item(
                "nether_quartz",
                "Nether Quartz",
                false,
                new Recipe()
        );
        new Item(
                "glass",
                "Glass",
                true,
                new Recipe(
                        true,1,
                        "sand"
                )
        );
        new Item(
                "sand",
                "Sand",
                false,
                new Recipe()
        );
        new Item(
                "redstone",
                "Redstone",
                false,
                new Recipe()
        );
        new Item(
                "glowstone_dust",
                "Glowstone Dust",
                false,
                new Recipe()
        );
        new Item(
                "inscriber_calculation_press",
                "Inscriber Calculation Press",
                false,
                new Recipe()
        );
        new Item(
                "certus_quartz_crystal",
                "Certus Quartz Crystal",
                false,
                new Recipe()
        );
        new Item(
                "printed_silicon",
                "Printed Silicon",
                true,
                new Recipe(
                        true,1,
                        "inscriber_silicon_press silicon"
                )
        );
        new Item(
                "silicon",
                "Silicon",
                false,
                new Recipe()
        );
        new Item(
                "inscriber_silicon_press",
                "Inscriber Silicon Press",
                false,
                new Recipe()
        );
        new Item(
                "gold_ingot",
                "Gold Ingot",
                false,
                new Recipe()
        );
        new Item(
                "iron_ingot",
                "Iron Ingot",
                false,
                new Recipe()
        );
    }

    private static void initialiseWoodenAxeItems() {
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
