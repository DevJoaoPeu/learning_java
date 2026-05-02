package server;
import com.sun.net.httpserver.HttpServer;
import handler.ProductHandler;
import service.ProductService;

import java.net.InetSocketAddress;
import java.io.IOException;

public class Server {
    private HttpServer server;
    private ProductService productService;

    public Server(int port, ProductService service) throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        productService = service;
    }

    public void start() {
        server.createContext("/products", new ProductHandler(productService));
        server.start();
    }
}
