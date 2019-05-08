public class Item {

    int id;
    String title;
    String category_id;
    float price;
    String currency_id;
    int quantity;
    String condition;
    String[] pictures;

    public Item(int id) {
        this.id = id;
        title = "Item de ID: " + id;
        category_id = "MLA" + id;
        price = 100;
        currency_id = "XXXX";
        quantity = 10;
        condition = "Nuevo";
        pictures = new String[] {"img1.png", "img2.jpg"};
    }
}
