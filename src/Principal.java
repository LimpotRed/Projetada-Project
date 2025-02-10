//Lucas Lopes Bastos
// testei o codigo e por algum erro de logica ele mostra a Heloisa com a maior idade, estou em duvida de como arrumar, mas em geral acho que foi isso que foi solicitado,
// desde ja agradeço, teste bastante interessante,
// foi utilizado Visual Estudio Code para a programação e Git para subir meu codigo para o repositorio do Github.
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>(Arrays.asList(
                new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"),
                new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"),
                new Funcionario("Heloisa", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"),
                new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"),
                new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"),
                new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"),
                new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"),
                new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"),
                new Funcionario("Caio", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"),
                new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente")
        )
    );

        funcionarios.removeIf(f -> f.getNome().equals("João"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        funcionarios.forEach(f -> System.out.println("Nome: " + f.getNome() + ", Data de Nascimento: " + f.getDataNascimento().format(formatter) +
                ", Salário: " + String.format("%,.2f", f.getSalario()).replace('.', ',') + ", Função: " + f.getFuncao()));

        funcionarios.forEach(f -> f.setSalario(f.getSalario().multiply(new BigDecimal("1.10"))));

        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));

        funcionariosPorFuncao.forEach((funcao, listaFuncionarios) -> {
            System.out.println("Função: " + funcao);
            listaFuncionarios.forEach(f -> System.out.println(f));
        }
    );
        System.out.println("-------------------------------------");
        System.out.println("Funcionários que fazem aniversário em outubro e dezembro:");
        funcionarios.stream().filter(f -> f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12)
                .forEach(f -> System.out.println(f));
        System.out.println("-------------------------------------");

        Funcionario maisVelho = Collections.max(funcionarios, Comparator.comparing(Pessoa::getDataNascimento));
        System.out.println("Funcionário com a maior idade: " + maisVelho.getNome() + ", Idade: " + (LocalDate.now().getYear() - maisVelho.getDataNascimento().getYear()));
        System.out.println("-------------------------------------");
       
        System.out.println("Funcionários em ordem alfabética:");
        funcionarios.stream().sorted(Comparator.comparing(Pessoa::getNome)).forEach(f -> System.out.println(f));
        System.out.println("-------------------------------------");
       
        BigDecimal totalSalarios = funcionarios.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total dos salários: " + String.format("%,.2f", totalSalarios).replace('.', ','));
        System.out.println("-------------------------------------");
      
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        funcionarios.forEach(f -> System.out.println(f.getNome() + " ganha " + f.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP).toString().replace('.', ',') + " salários mínimos."));
    }
    
}