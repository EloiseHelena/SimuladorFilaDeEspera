import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Paciente {
    private String nome;
    private int idade;
    private String motivoConsulta;

    public Paciente(String nome, int idade, String motivoConsulta) {
        this.nome = nome;
        this.idade = idade;
        this.motivoConsulta = motivoConsulta;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return String.format("| %-20s | %-5d | %-30s |", nome, idade, motivoConsulta);
    }
}

class FilaDeAtendimento {
    private Queue<Paciente> fila;

    public FilaDeAtendimento() {
        fila = new LinkedList<>();
    }

    public void adicionarPaciente(Paciente paciente) {
        fila.add(paciente);
        System.out.println("Paciente " + paciente.getNome() + " adicionado à fila.");
    }

    public Paciente atenderPaciente() {
        if (fila.isEmpty()) {
            System.out.println("Não há pacientes na fila.");
            return null;
        } else {
            Paciente paciente = fila.poll();
            System.out.println("Paciente " + paciente.getNome() + " se dirija ao consultório 1");
            return paciente;
        }
    }

    public void mostrarFila() {
        if (fila.isEmpty()) {
            System.out.println("A fila está vazia.");
        } else {
            System.out.println("+----------------------+-------+--------------------------------+");
            System.out.println("| Nome                 | Idade | Motivo da Consulta             |");
            System.out.println("+----------------------+-------+--------------------------------+");
            for (Paciente paciente : fila) {
                System.out.println(paciente);
                System.out.println("+----------------------+-------+--------------------------------+");
            }
        }
    }
}

public class SimuladorFilaDeAtendimento {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FilaDeAtendimento fila = new FilaDeAtendimento();

        boolean continuar = true;
        while (continuar) {
            System.out.println("\n=====================================");
            System.out.println("     Simulador de Fila de Atendimento");
            System.out.println("=====================================");
            System.out.println("[1] - Recepção e Check-in");
            System.out.println("[2] - Mostrar Fila");
            System.out.println("[3] - Atender Paciente");
            System.out.println("[4] - Encerrar Simulação");
            System.out.println("=====================================");
            System.out.print("Escolha uma opção: ");

            int opcao = 0;
            boolean entradaValida = false;
            while (!entradaValida) {
                try {
                    opcao = scanner.nextInt();
                    scanner.nextLine();  // Limpar buffer
                    entradaValida = true;
                } catch (InputMismatchException e) {
                    System.out.println("Por favor, insira um número válido.");
                    scanner.nextLine();
                }
            }

            switch (opcao) {
                case 1:
                    System.out.print("Nome do Paciente: ");
                    String nome = scanner.nextLine();
                    int idade = 0;

                    entradaValida = false;
                    while (!entradaValida) {
                        try {
                            System.out.print("Idade do Paciente: ");
                            idade = scanner.nextInt();
                            scanner.nextLine();  // Limpar buffer
                            entradaValida = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Por favor, insira uma idade válida (número inteiro).");
                            scanner.nextLine();
                        }
                    }

                    System.out.print("Motivo da Consulta: ");
                    String motivoConsulta = scanner.nextLine();

                    Paciente paciente = new Paciente(nome, idade, motivoConsulta);
                    fila.adicionarPaciente(paciente);
                    break;

                case 2:
                    fila.mostrarFila();
                    break;

                case 3:
                    fila.atenderPaciente();
                    break;

                case 4:
                    continuar = false;
                    System.out.println("Encerrando Simulação...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }

        scanner.close();
    }
}

