package dominio;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private String nome;
    private String cnpj;
    private String endereco;
    private static int contratosIDs = 0;
    private List<Contrato> contratos;
    public Empresa(String nome, String cnpj, String endereco) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.contratos = new ArrayList<>();
    }

    public Empresa(String nome, String cnpj, String endereco, List<Contrato> contratos) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.contratos = contratos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }
    public void criarContrato(Contrato contrato) {
        contratosIDs++;
        contrato.setContradoID(contratosIDs);
        contratos.add(contrato);
    }
}
