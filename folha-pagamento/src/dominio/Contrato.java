package dominio;

public class Contrato {
    private int contradoID;
    private Empresa empresa;
    private Funcionario funcionario;
    private double salarioBaseHora;
    private int quantidadeDiasTrabalhadosNaSemana;
    private int cargaHorariaDiaria;
    private Double valorHoraExtra;
    private Double descontoINSS;
    private Double descontoVT;
    private Double descontoIRRF;

    public Contrato(Empresa empresa,
                    Double salarioBase, int quantidadeDiasTrabalhadosNaSemana,
                    int cargaHorariaDiaria, Double valorHoraExtra,
                    Double descontoINSS, Double descontoVT, Double descontoIRRF) {
        this.empresa = empresa;
        this.salarioBaseHora = salarioBase;
        this.quantidadeDiasTrabalhadosNaSemana = quantidadeDiasTrabalhadosNaSemana;
        this.cargaHorariaDiaria = cargaHorariaDiaria;
        this.valorHoraExtra = valorHoraExtra;
        this.descontoINSS = descontoINSS;
        this.descontoVT = descontoVT;
        this.descontoIRRF = descontoIRRF;
    }
    public Contrato(int contradoID, Empresa empresa, Funcionario funcionario,
                    Double salarioBase,int quantidadeDiasTrabalhadosNaSemana, int cargaHorariaDiaria,
                    Double valorHoraExtra, Double descontoINSS,
                    Double descontoVT, Double descontoIRRF) {
        this.contradoID = contradoID;
        this.empresa = empresa;
        this.funcionario = funcionario;
        this.salarioBaseHora = salarioBase;
        this.quantidadeDiasTrabalhadosNaSemana = quantidadeDiasTrabalhadosNaSemana;
        this.cargaHorariaDiaria = cargaHorariaDiaria;
        this.valorHoraExtra = valorHoraExtra;
        this.descontoINSS = descontoINSS;
        this.descontoVT = descontoVT;
        this.descontoIRRF = descontoIRRF;
    }

    public int getContradoID() {
        return contradoID;
    }

    public void setContradoID(int contradoID) {
        this.contradoID = contradoID;
    }

    public Double getSalarioBase() {
        return salarioBaseHora;
    }

    public void setSalarioBase(Double salarioBase) {
        this.salarioBaseHora = salarioBase;
    }

    public double getValorHoraExtra() {
        return valorHoraExtra;
    }

    public void setValorHoraExtra(Double valorHoraExtra) {
        this.valorHoraExtra = valorHoraExtra;
    }

    public double getDescontoINSS() {
        return descontoINSS;
    }

    public void setDescontoINSS(Double descontoINSS) {
        this.descontoINSS = descontoINSS;
    }

    public double getDescontoVT() {
        return descontoVT;
    }

    public void setDescontoVT(Double descontoVT) {
        this.descontoVT = descontoVT;
    }

    public double getDescontoIRRF() {
        return descontoIRRF;
    }

    public void setDescontoIRRF(Double descontoIRRF) {
        this.descontoIRRF = descontoIRRF;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public int getcargaHorariaDiaria() {
        return cargaHorariaDiaria;
    }

    public void setcargaHorariaDiaria(int cargaHorariaDiaria) {
        this.cargaHorariaDiaria = cargaHorariaDiaria;
    }

    public Double getSalarioBaseHora() {
        return salarioBaseHora;
    }

    public void setSalarioBaseHora(Double salarioBaseHora) {
        this.salarioBaseHora = salarioBaseHora;
    }

    public int getQuantidadeDiasTrabalhadosNaSemana() {
        return quantidadeDiasTrabalhadosNaSemana;
    }

    public void setQuantidadeDiasTrabalhadosNaSemana(int quantidadeDiasTrabalhadosNaSemana) {
        this.quantidadeDiasTrabalhadosNaSemana = quantidadeDiasTrabalhadosNaSemana;
    }

    public int getCargaHorariaDiaria() {
        return cargaHorariaDiaria;
    }

    public void setCargaHorariaDiaria(int cargaHorariaDiaria) {
        this.cargaHorariaDiaria = cargaHorariaDiaria;
    }
}
