package org.dominio;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Bilhete {
    private Long id;
    private Veiculo veiculo;
    private LocalDateTime entrada;
    private LocalDateTime saida;

    public Bilhete(Long id, Veiculo veiculo, LocalDateTime entrada, LocalDateTime saida) {
        this.id = id;
        this.veiculo = veiculo;
        this.entrada = entrada;
        this.saida = saida;
    }
    public Bilhete(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    public LocalDateTime getSaida() {
        return saida;
    }

    public void setSaida(LocalDateTime saida) {
        this.saida = saida;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(!(obj instanceof Bilhete)) {
            return false;
        }
        Bilhete b = (Bilhete) obj;
        return this.id.equals(b.id);
    }

    public String formatarLocalDateTime(LocalDateTime dateTime) {
        if(dateTime == null) {
            return "Veiculo estacionado";
        }
        return dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    @Override
    public String toString() {
        return "ID: " + this.id + "\nPlaca do Veículo: " +
                this.veiculo.getPlaca() + "\nDono do Veículo: " +
                "\nEntrada: " + formatarLocalDateTime(this.entrada) +
                "\nSaida: " + formatarLocalDateTime(this.saida) + "\n";
    }
}
