import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

// Interface Pagamento
interface Pagamento {
    double calcularPagamento();
    String getNome();
}

// Classe Funcionario implementando a interface Pagamento
class Funcionario implements Pagamento {
    private String nome;
    private int horasTrabalhadas;
    private double valorPorHora;

    public Funcionario(String nome, int horasTrabalhadas, double valorPorHora) {
        this.nome = nome;
        this.horasTrabalhadas = horasTrabalhadas;
        this.valorPorHora = valorPorHora;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public double calcularPagamento() {
        return horasTrabalhadas * valorPorHora;
    }
}

// Classe FuncionarioTerceirizado que herda de Funcionario e implementa Pagamento
class FuncionarioTerceirizado extends Funcionario {
    private double despesaAdicional;

    public FuncionarioTerceirizado(String nome, int horasTrabalhadas, double valorPorHora, double despesaAdicional) {
        super(nome, horasTrabalhadas, valorPorHora);
        this.despesaAdicional = despesaAdicional;
    }

    @Override
    public double calcularPagamento() {
        return super.calcularPagamento() + 1.1 * despesaAdicional;
    }
}

public class questão01 {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner ler = new Scanner(System.in);

        System.out.print("Quantos funcionarios são: ");
        int n = ler.nextInt();
        List<Pagamento> listaDeFuncionarios = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            System.out.println("Informações do Funcionario #" + i + ":");

            char ch;
            while (true) {
                System.out.print("Esse funcionario é terceirizado (s/n)? ");
                ch = ler.next().charAt(0);
                if (ch == 's' || ch == 'S' || ch == 'n' || ch == 'N') {
                    break;
                } else {
                    System.out.println("Opção inválida. Por favor, insira 's' para sim ou 'n' para não.");
                }
            }

            System.out.print("Nome do Funcionario: ");
            ler.nextLine();  // Consumir a quebra de linha
            String nome = ler.nextLine();
            System.out.print("Quantas horas esse Funcionario trabalhou? ");
            int horasTrabalhadas = ler.nextInt();
            System.out.print("Qual o valor por hora? ");
            double valorPorHora = ler.nextDouble();

            if (ch == 's' || ch == 'S') {
                System.out.print("Despesa adicional: ");
                double despesaAdicional = ler.nextDouble();
                listaDeFuncionarios.add(new FuncionarioTerceirizado(nome, horasTrabalhadas, valorPorHora, despesaAdicional));
            } else {
                listaDeFuncionarios.add(new Funcionario(nome, horasTrabalhadas, valorPorHora));
            }
        }

        System.out.println();
        System.out.println("Pagamentos:");
        for (Pagamento pagavel : listaDeFuncionarios) {
            System.out.println(pagavel.getNome() + " - R$ " + String.format("%.2f", pagavel.calcularPagamento()));
        }

        ler.close();
    }
}
