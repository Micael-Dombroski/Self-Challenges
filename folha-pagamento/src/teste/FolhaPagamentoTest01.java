package teste;

import dominio.*;

public class FolhaPagamentoTest01 {
    public static void main(String[] args) {
        Empresa empresa = new Empresa("Disney", "000000000000", "Nova York");
        Contrato contrato = new Contrato(empresa, 39.0, 5, 8, 35.0, 0.14, 0.06 ,0.27);
        Funcionario funcionario = new Funcionario("Jorge Mateus", "11111111111", "Operador", contrato);
        contrato.setFuncionario(funcionario);
        empresa.criarContrato(contrato);
        HistoricoMensal hist = new HistoricoMensal(2025, 8, empresa, funcionario);
        hist.setHorasTrabalhadas(180);
        FolhaPagamento.imprimir(empresa, funcionario, contrato, hist);
    }
}
