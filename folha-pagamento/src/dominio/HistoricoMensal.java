package dominio;

import java.util.Date;

public class HistoricoMensal {
    private int ano;
    private int mes;
    private Empresa empresa;
    private Funcionario funcionario;
    private int horasTrabalhadas;
    private int faltas;

    public HistoricoMensal(int ano, int mes, Empresa empresa, Funcionario funcionario) {
        this.ano = ano;
        this.mes = mes;
        this.empresa = empresa;
        this.funcionario = funcionario;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
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

    public int getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(int horasTrabalhadas) {
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }
}
