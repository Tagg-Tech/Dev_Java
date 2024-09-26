package school.sptech.Entity;

public class Componente {

    private Integer idRegistro;
    private Double percentualMemoria;
    private Double percentualDisco;
    private Double percentualCPU;
    private Double frequenciaCPU;
    private String dataHora;

    public Componente(Integer idRegistro, Double percentualMemoria, Double percentualDisco, Double percentualCPU, Double frequenciaCPU, String dataHora) {
        this.idRegistro = idRegistro;
        this.percentualMemoria = percentualMemoria;
        this.percentualDisco = percentualDisco;
        this.percentualCPU = percentualCPU;
        this.frequenciaCPU = frequenciaCPU;
        this.dataHora = dataHora;
    }

    public Componente() {
        this.idRegistro = null;
        this.percentualMemoria = 0.0;
        this.percentualDisco = 0.0;
        this.percentualCPU = 0.0;
        this.frequenciaCPU = 0.0;
        this.dataHora = "";
    }

    public Integer getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Double getPercentualMemoria() {
        return percentualMemoria;
    }

    public void setPercentualMemoria(Double percentualMemoria) {
        this.percentualMemoria = percentualMemoria;
    }

    public Double getPercentualDisco() {
        return percentualDisco;
    }

    public void setPercentualDisco(Double percentualDisco) {
        this.percentualDisco = percentualDisco;
    }

    public Double getPercentualCPU() {
        return percentualCPU;
    }

    public void setPercentualCPU(Double percentualCPU) {
        this.percentualCPU = percentualCPU;
    }

    public Double getFrequenciaCPU() {
        return frequenciaCPU;
    }

    public void setFrequenciaCPU(Double frequenciaCPU) {
        this.frequenciaCPU = frequenciaCPU;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }
}
