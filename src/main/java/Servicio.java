
import com.google.gson.Gson;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.model.Delay;
import org.mockserver.model.Header;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;

public class Servicio {

    static MockServerClient mockServer = startClientAndServer(8081);
    static Gson gson;

    public static void consulta(String metodo, String ruta, int codigo, String content, String body, int delay) {

        mockServer.when(
                request().withMethod(metodo)
                .withPath(ruta)
        )
        .respond(
                response().withStatusCode(codigo)
                .withHeader(new Header("Content-Type", content))
                .withBody(body)
                .withDelay(new Delay(TimeUnit.MILLISECONDS, delay))
        );
    }

    public static void main(String[] args) {
        gson = new Gson();
        Item item = new Item(12345);

        Category category1 = new Category("1");
        Category category2 = new Category("2");
        Currency currency = new Currency("1");

        Category[] categories = {category1, category2};
        MyMl myml = new MyMl(categories, currency);

        User user1 = new User(1);

        Country country = new Country("VE");

        consulta("GET", "/items/.*", 200, "application/json; charset=utf-8", gson.toJson(item), 10);

        consulta("GET", "/users/.*", 200, "application/json; charset=utf-8", gson.toJson(user1), 10);

        consulta("GET", "/sites/.*/categories", 200, "application/json; charset=utf-8", gson.toJson(categories), 10);

        consulta("GET", "/classified_locations/countries/.*", 200, "application/json; charset=utf-8", gson.toJson(country), 10);

        consulta("GET", "/currencies/.*", 200, "application/json; charset=utf-8", gson.toJson(currency), 10);

    }
}
