public class Main {
    public static void main(String[] args) {
        new Item("wooden_plank",
                "Wooden Plank",
                true,
                new Recipe(4, new Slot[]{new Slot(1, "oak_log")})
        );

    }
}
