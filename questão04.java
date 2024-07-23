import java.util.Scanner;

public class questão04 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double taxa1 = 0.20; // 20% para valores até 100.00
        double taxa2 = 0.15; // 15% para valores acima de 100.00

        System.out.print("Digite o modelo do carro: ");
        String modeloCarro = input.nextLine();

        System.out.print("Digite o instante inicial da locação (em horas): ");
        double instanteInicial = input.nextDouble();

        System.out.print("Digite o instante final da locação (em horas): ");
        double instanteFinal = input.nextDouble();

        System.out.print("Digite o valor por hora da locação: ");
        double valorHora = input.nextDouble();

        System.out.print("Digite o valor diário da locação: ");
        double valorDiario = input.nextDouble();

        double duracao = instanteFinal - instanteInicial;
        double valorLocacao;
        
        if (duracao <= 12) {
            valorLocacao = duracao * valorHora;
        } else {
            valorLocacao = valorDiario;
        }

        double imposto;
        if (valorLocacao <= 100.00) {
            imposto = valorLocacao * taxa1;
        } else {
            imposto = valorLocacao * taxa2;
        }

        double valorTotal = valorLocacao + imposto;

        System.out.printf("Modelo do carro: %s\n", modeloCarro);
        System.out.printf("Duração da locação: %.2f horas\n", duracao);
        System.out.printf("Valor da locação: %.2f\n", valorLocacao);
        System.out.printf("Imposto: %.2f\n", imposto);
        System.out.printf("Valor total a pagar: %.2f\n", valorTotal);

        input.close();
    }
}
