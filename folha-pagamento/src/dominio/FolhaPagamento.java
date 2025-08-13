package dominio;

public class FolhaPagamento {
    public static void imprimir(Empresa empresa, Funcionario funcionario, Contrato contrato, HistoricoMensal historico) {
        int horasTrabalhadas = contrato.getQuantidadeDiasTrabalhadosNaSemana()*4 - historico.getFaltas();
        int horasExtrasTrabalhadas = historico.getHorasTrabalhadas() - horasTrabalhadas;
        Double salarioBase = Double.valueOf(contrato.getSalarioBase() * horasTrabalhadas);
        Double descontoINSS = Double.valueOf(salarioBase * contrato.getDescontoINSS());
        Double descontoIRRF = Double.valueOf(salarioBase * contrato.getDescontoIRRF());
        Double descontoVT = Double.valueOf(salarioBase * contrato.getDescontoVT());
        String mesAnoFormat = historico.getMes() + " - " + historico.getAno();
        System.out.println("EMPREGADOR\t\t\t\t\t\tRecibo de Pagamento de Salário");
        System.out.printf("Nome: %-30s \tReferente ao Mês/Ano\n", empresa.getNome());
        System.out.printf("CNPJ: %-30s \t%20s\n", empresa.getCnpj(), mesAnoFormat);
        System.out.println("--------------------------------------------------------------");
        System.out.println("CÓDIGO\tNOME DO FUNCIONÁRIO           \tCBO\tFUNÇÃO");
        System.out.printf("%06d\t%-30s\t   \t%s\n", contrato.getContradoID(), funcionario.getNome(), funcionario.getFuncao());
        System.out.println("CÓD.\tDescrição   \t\tReferência\tProventos\tDescontos");
        System.out.printf("    \tSALÁRIO BASE\t\t%7d:00\t%6.2f\n", horasTrabalhadas, salarioBase);
        System.out.printf("    \tHORAS EXTRAS\t\t%7d:00\t%6.2f\n", horasExtrasTrabalhadas, horasTrabalhadas*contrato.getValorHoraExtra());
        System.out.printf("    \tINSS        \t\t%6.2f%%\t          \t\t%6.2f\n", contrato.getDescontoINSS()*100, descontoINSS);
        System.out.printf("    \tIRRF        \t\t%6.2f%%\t          \t\t%6.2f\n", contrato.getDescontoIRRF()*100, descontoIRRF);
        System.out.printf("    \tVALE TRANSP.\t\t%6.2f%%\t          \t\t%6.2f\n", contrato.getDescontoVT()*100, descontoVT);
        System.out.println("--------------------------------------------------------------");
        Double bruto = salarioBase + horasExtrasTrabalhadas* contrato.getValorHoraExtra();
        Double totDescontos = descontoIRRF + descontoIRRF + descontoVT;
        System.out.printf("    \tTotal Bruto\t\t\t%6.2f\t          \t\t%6.2f\n", bruto, totDescontos);
        System.out.printf("    \tTot. Líq.  \t\t\t%6.2f\n", (bruto - totDescontos));
        System.out.println("--------------------------------------------------------------");
    }
}
