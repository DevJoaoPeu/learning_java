import model.Product;
import repository.ProductRepository;
import service.ProductService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void  main (String[] args) {
        ProductService service = new ProductService();

        Scanner scanner = new Scanner(System.in);
        int option;

        while (true) {
            System.out.println("1 - Listar produtos");
            System.out.println("2 - Buscar um produto");
            System.out.println("3 - Remover um produto");
            System.out.println("4 - Atualizar um produto");
            System.out.println("5 - Cadastrar um produto");
            System.out.println("0 - Sair");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    List<Product> items = service.findAll();
                    System.out.println(items);
                    break;
                case 2:
                    try {
                        System.out.println("Me envie o id");

                        Long id = scanner.nextLong();
                        Product item = service.findById(id);

                        System.out.println("Item encontrado: " + item);
                    }
                    catch (RuntimeException error) {
                        System.out.println(error.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Me envie o id para a remoção");

                    Long idToRemove = scanner.nextLong();
                    boolean wasRemoved = service.deleteById(idToRemove);

                    System.out.println("Foi removido?  " + wasRemoved);
                    break;
                case 4:
                    System.out.println("Me envie o id");
                    Long idToUpdate = scanner.nextLong();
                    scanner.nextLine();

                    System.out.println("Me envie o nome");
                    String nameToUpdate = scanner.nextLine();

                    System.out.println("Me envie o preço");
                    double priceToUpdate = scanner.nextDouble();

                    System.out.println("Me envie a quantidade");
                    int quantityToUpdate = scanner.nextInt();

                    Product itemToUpdate = service.update(idToUpdate, new Product(idToUpdate, nameToUpdate, priceToUpdate, quantityToUpdate));

                    System.out.println("Item encontrado: " + itemToUpdate);
                    break;
                case 5:
                    scanner.nextLine();
                    System.out.println("Me envie o nome");
                    String nameToCreate = scanner.nextLine();

                    System.out.println("Me envie o preço");
                    double priceToCreate = scanner.nextDouble();

                    System.out.println("Me envie a quantidade");
                    int quantityToCreate = scanner.nextInt();

                    Product itemToCreate = service.create(new Product(null, nameToCreate, priceToCreate, quantityToCreate));

                    System.out.println("Item encontrado: " + itemToCreate);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção invalida");
            }
        }
    }
}