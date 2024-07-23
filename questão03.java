import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class questão03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            System.out.print("Número do contrato: ");
            String numeroContrato = scanner.nextLine();
            
            System.out.print("Data do contrato (dd/MM/yyyy): ");
            String dataContratoStr = scanner.nextLine();

            LocalDate dataContrato = null;
            try {
                dataContrato = LocalDate.parse(dataContratoStr, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Data do contrato inválida. Certifique-se de usar o formato dd/MM/yyyy.");
                return;
            }

            System.out.print("Valor total do contrato: ");
            if (!scanner.hasNextDouble()) {
                System.out.println("Valor total do contrato inválido. Certifique-se de inserir um número.");
                return;
            }
            double valorTotal = scanner.nextDouble();

            System.out.print("Número de meses para parcelamento: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Número de meses para parcelamento inválido. Certifique-se de inserir um número inteiro.");
                return;
            }
            int numeroMeses = scanner.nextInt();

            // Consumir a nova linha deixada pelo nextInt()
            scanner.nextLine();

            double valorParcelaBase = valorTotal / numeroMeses;
            double taxaJuros = 0.01;
            double taxaPagamento = 0.02;

            System.out.println("\nParcelas:");
            for (int i = 1; i <= numeroMeses; i++) {
                LocalDate dataParcela = dataContrato.plusMonths(i);
                double valorParcela = valorParcelaBase * (1 + taxaJuros + taxaPagamento);
                System.out.printf("Parcela %d: Data: %s, Valor: %.2f\n", i, dataParcela.format(formatter), valorParcela);
            }

        } catch (Exception e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
