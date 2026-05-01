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
                if (sb.length() >1) {
                   sb.append(",");
                }

                sb.append(product);
            }

            sb.append("]");

            String jsonString = sb.toString();

            byte[] response = jsonString.getBytes();
            exchange.sendResponseHeaders(200, response.length);
            exchange.getResponseBody().write(response);
            exchange.getResponseBody().close();
        }

        if (method.equals("GET") && path.startsWith("/products/")) {
            String id = path.replace("/products/", "");

            if(!isNumber(id)) {
                String message = "O parametro id deve ser um numero valido";
                sendResponse(exchange, 400, message);
                return;
            }

            try {
                Product item = productService.findById(Long.parseLong(id));
                String jsonString = item.toString();

                byte[] response = jsonString.getBytes();
                exchange.sendResponseHeaders(200, response.length);
                exchange.getResponseBody().write(response);
                exchange.getResponseBody().close();
            }
            catch (Exception e) {
                byte[] response = e.getMessage().getBytes();
                exchange.sendResponseHeaders(404, response.length);
                exchange.getResponseBody().write(response);
                exchange.getResponseBody().close();
            }

            return;
        }

        if (method.equals("POST") && path.equals("/products")) {
            String body = new String(exchange.getRequestBody().readAllBytes());
            Product item = productService.create(parseProduct(body));

            String jsonString = item.toString();

            byte[] response = jsonString.getBytes();
            exchange.sendResponseHeaders(200, response.length);
            exchange.getResponseBody().write(response);
            exchange.getResponseBody().close();
        }

        if (method.equals("PUT") && path.startsWith("/products/")) {
            String body = new String(exchange.getRequestBody().readAllBytes());
            String id = path.replace("/products/", "");

            Product item = productService.update(Long.parseLong(id), parseProduct(body));

            String jsonString = item.toString();

            byte[] response = jsonString.getBytes();
            exchange.sendResponseHeaders(200, response.length);
            exchange.getResponseBody().write(response);
            exchange.getResponseBody().close();
        }

        if (method.equals("DELETE") && path.startsWith("/products/")) {
            String id = path.replace("/products/", "");

            try {
                boolean item = productService.deleteById(Long.parseLong(id));

                String message;

                if (item) {
                    message = "Item deletado com sucesso";
                } else {
                    message = "Falha ao deletar item: " + id;
                }

                byte[] response = message.getBytes();

                exchange.sendResponseHeaders(200, response.length);
                exchange.getResponseBody().write(response);
                exchange.getResponseBody().close();
            }
            catch (Exception e) {
                byte[] response = e.getMessage().getBytes();
                exchange.sendResponseHeaders(404, response.length);
                exchange.getResponseBody().write(response);
                exchange.getResponseBody().close();
            }
        }
    }

    private Product parseProduct(String body) {
        String cleaned = body.replace("{", "").replace("}", "").replace("\"", "");
        String[] pairs = cleaned.split(",");

        String name = null;
        double price = 0;
        int quantity = 0;

        for (String pair : pairs) {
            String[] parts = pair.split(":");
            String key = parts[0].trim();
            String value = parts[1].trim();

            if (key.equals("name")) {
                name = value;
            } else if (key.equals("price")) {
                price = Double.parseDouble(value);
            } else if (key.equals("quantity")) {
                quantity = Integer.parseInt(value);
            }
        }

        return new Product(null, name, price, quantity);
    }

    private void sendResponse(HttpExchange exchange, int status, String message) throws IOException {
        String json = "{\"message\":\"" + message + "\"}";
        byte[] response = json.getBytes();
        exchange.sendResponseHeaders(status, response.length);
        exchange.getResponseBody().write(response);
        exchange.getResponseBody().close();
    }

    public static boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
