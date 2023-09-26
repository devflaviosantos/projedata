package br.com.devflaviosantos;

import br.com.devflaviosantos.entidades.Funcionario;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        // 3.1 - Inserir funcionários
        System.out.println("---------------------------------------------");
        List<Funcionario> funcionarios = new ArrayList<>();

        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, Month.OCTOBER, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("Joao", LocalDate.of(1990, Month.MAY, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, Month.MAY, 2), new BigDecimal("9836.14"), "Cordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, Month.OCTOBER, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, Month.JANUARY, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, Month.NOVEMBER, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, Month.MARCH, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1993, Month.JULY, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloisa", LocalDate.of(2003, Month.MAY, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, Month.SEPTEMBER, 2), new BigDecimal("2799.93"), "Gerente"));



        System.out.println("---------------------------------------------");
        // 3.2 - Remover funcionário "João"
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));
        System.out.println("---------------------------------------------");
        // 3.3 - Imprimir funcionários
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
        System.out.println("Funcionários:");
        funcionarios.forEach(funcionario -> {
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(dateFormatter));
            System.out.println("Salário: " + decimalFormat.format(funcionario.getSalario()));
            System.out.println("Função: " + funcionario.getFuncao());
            System.out.println();
        });
        System.out.println("---------------------------------------------");
        // 3.4 - Aumentar salário em 10%
        BigDecimal percentualAumento = new BigDecimal("0.10");
        funcionarios.forEach(funcionario -> funcionario.aumentarSalario(percentualAumento));
        System.out.println("---------------------------------------------");
        // 3.5 - Agrupar por função em um MAP
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
        System.out.println("---------------------------------------------");
        // 3.6 - Imprimir funcionários agrupados por função
        System.out.println("3.6 Funcionários Agrupados por Função:");
        funcionariosPorFuncao.forEach((funcao, listaFuncionarios) -> {
            System.out.println("Função: " + funcao);
            listaFuncionarios.forEach(funcionario -> System.out.println(funcionario.getNome()));
            System.out.println();
        });
        System.out.println("---------------------------------------------");
        // 3.8 - Imprimir funcionários com aniversário em outubro e dezembro
        System.out.println("3.8 Funcionários com Aniversário em Outubro e Dezembro:");
        funcionarios.stream()
                .filter(funcionario -> funcionario.getDataNascimento().getMonthValue() == 10 ||
                        funcionario.getDataNascimento().getMonthValue() == 12)
                .forEach(funcionario -> System.out.println(funcionario.getNome() + " - "+ funcionario.getDataNascimento()));
        System.out.println("---------------------------------------------");
        // 3.9 - Funcionário com a maior idade
        System.out.println("3.9 - Funcionário com a maior idade:");
        // Encontre o funcionário mais velho
        Optional<Funcionario> funcionarioMaisVelho1 = funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento));

        if (funcionarioMaisVelho1.isPresent()) {
            Funcionario maisVelho = funcionarioMaisVelho1.get();
            System.out.println("Funcionário Mais Velho:");
            System.out.println("Nome: " + maisVelho.getNome());
            System.out.println("Data de Nascimento: " + maisVelho.getDataNascimento());
            System.out.println("Salário: " + maisVelho.getSalario());
            System.out.println("Função: " + maisVelho.getFuncao());
        } else {
            System.out.println("Nenhum funcionário encontrado.");
        }


        System.out.println("---------------------------------------------");
        // 3.10 - Lista de funcionários em ordem alfabética
        System.out.println("Funcionários em Ordem Alfabética:");
        funcionarios.stream()
                .map(Funcionario::getNome)
                .sorted()
                .forEach(System.out::println);
        System.out.println("---------------------------------------------");
        // 3.11 - Total dos salários dos funcionários
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total dos Salários: " + decimalFormat.format(totalSalarios));
        System.out.println("---------------------------------------------");
        // 3.12 - Salários mínimos
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("Salários em Salários Mínimos:");
        funcionarios.forEach(funcionario -> {
            BigDecimal salariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_DOWN);
            System.out.println(funcionario.getNome() + ": " + salariosMinimos + " salários mínimos");
        });

        System.out.println("---------------------------------------------");
    }
}