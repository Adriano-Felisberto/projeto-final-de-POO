import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String priceTag() {
        return name + " $ " + String.format("%.2f", price);
    }
}

class UsedProduct extends Product {
    private Date manufactureDate;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public UsedProduct(String name, double price, Date manufactureDate) {
        super(name, price);
        this.manufactureDate = manufactureDate;
    }

    @Override
    public String priceTag() {
        return getName() + " (used) $ " + String.format("%.2f", getPrice()) + " (Manufacture date: " + sdf.format(manufactureDate) + ")";
    }
}

class ImportedProduct extends Product {
    private double customsFee;

    public ImportedProduct(String name, double price, double customsFee) {
        super(name, price);
        this.customsFee = customsFee;
    }

    public double totalPrice() {
        return getPrice() + customsFee;
    }

    @Override
    public String priceTag() {
        return getName() + " $ " + String.format("%.2f", totalPrice()) + " (Customs fee: $ " + String.format("%.2f", customsFee) + ")";
    }
}

public class questão02 {
    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        List<Product> products = new ArrayList<>();

        System.out.print("Quantidade de produtos que deseja cadastrar: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("Produto #" + (i + 1) + " data:");
            System.out.println("[1] Normal");
            System.out.println("[2] Usado");
            System.out.println("[3] Importado");

            int type = sc.nextInt();
            sc.nextLine();

            System.out.print("Nome do produto: ");
            String name = sc.nextLine();

            System.out.print("Preço: ");
            double price = sc.nextDouble();

            if (type == 3) {
                System.out.print("Taxa de alfândega: ");
                double customsFee = sc.nextDouble();
                products.add(new ImportedProduct(name, price, customsFee));
            } else if (type == 2) {
                System.out.print("Data de manufatura (dd/MM/yyyy): ");
                Date manufactureDate = sdf.parse(sc.next());
                products.add(new UsedProduct(name, price, manufactureDate));
            } else {
                products.add(new Product(name, price));
            }
        }

        System.out.println();
        System.out.println("Produtos e preços:");
        for (Product product : products) {
            System.out.println(product.priceTag());
        }

        sc.close();
    }
}
