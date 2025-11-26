package org.dominio;

public class Veiculo {
    private String placa;
    private String dono;

    public Veiculo(String placa, String dono) {
        this.placa = placa;
        this.dono = dono;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getDono() {
        return dono;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(!(obj instanceof Veiculo)) {
            return false;
        }
        Veiculo v = (Veiculo) obj;
        return this.placa.equals(v.placa);
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "placa='" + placa + '\'' +
                ", dono='" + dono + '\'' +
                '}';
    }
}
