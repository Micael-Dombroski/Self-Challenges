package dominio;

public class Funcionario {
    private String nome;
    private String cpf;
    private String funcao;
    private Contrato contrato;

    public Funcionario(String nome, String cpf, String funcao, Contrato contrato) {
        this.nome = nome;
        this.cpf = cpf;
        this.funcao = funcao;
        this.contrato = contrato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
}
