package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import model.Product;
import service.ProductService;

import java.io.IOException;
import java.util.List;

public class ProductHandler implements HttpHandler {
    private ProductService productService;

    public ProductHandler(ProductService service) {
        productService = service;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        if (method.equals("GET") && path.equals("/products")) {
            List<Product> products = productService.findAll();
            StringBuilder sb = new StringBuilder("[");

            for (Product product : products) {
                sb.append(product).append(",");
            }

            sb.append("]");

            String jsonString = sb.toString();

            byte[] response = jsonString.getBytes();
            exchange.sendResponseHeaders(200, response.length);
            exchange.getResponseBody().write(response);
            exchange.getResponseBody().close();
        }

        if (method.equals("GET") && path.startsWith("/products/")) {
            // listById
        }

        if (method.equals("POST") && path.equals("/products")) {
            // save
        }

        if (method.equals("PUT") && path.startsWith("/products/")) {
            // listAll
        }

        if (method.equals("DELETE") && path.startsWith("/products/")) {
            // listAll
        }
    }
}
