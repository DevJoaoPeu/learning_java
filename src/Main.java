import server.Server;
import service.ProductService;

import java.io.IOException;

public class Main {
    public static void  main (String[] args) throws IOException {
        ProductService service = new ProductService();

        Server server = new Server(8080, service);
        server.start();
    }
}