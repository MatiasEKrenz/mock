public class Currency {

    String id;
    String symbol;
    String description;
    int decimalPlaces;

    public Currency(String id) {
        this.id = id;
        symbol = "$";
        description = "devaluado";
        decimalPlaces = 1;
    }
}
