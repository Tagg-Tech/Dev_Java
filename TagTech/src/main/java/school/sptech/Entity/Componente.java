package school.sptech.Entity;

public class Componente {

    private Integer idRegistro;
    private String nomeMaquina;
    private String sistemaOperacional;
    private Long qtdTotalMemoria;
    private Double percentualMemoria;
    private Long qtdTotalDisco;
    private Long qtdUtilizadaDisco;
    private Double percentualDisco;
    private Integer qtdNucleosCPU;
    private Integer qtdNucleosVirtuaisCPU;
    private Double percentualCPU;
    private Double frequenciaCPU;
    private String nomeUsuario;
    private String dataHora;

    public Componente(Integer idRegistro, String nomeMaquina, String sistemaOperacional, Long qtdTotalMemoria, Double percentualMemoria, Long qtdTotalDisco, Long qtdUtilizadaDisco, Double percentualDisco, Integer qtdNucleosCPU, Integer qtdNucleosVirtuaisCPU, Double percentualCPU, Double frequenciaCPU, String nomeUsuario, String dataHora) {
        this.idRegistro = idRegistro;
        this.nomeMaquina = nomeMaquina;
        this.sistemaOperacional = sistemaOperacional;
        this.qtdTotalMemoria = qtdTotalMemoria;
        this.percentualMemoria = percentualMemoria;
        this.qtdTotalDisco = qtdTotalDisco;
        this.qtdUtilizadaDisco = qtdUtilizadaDisco;
        this.percentualDisco = percentualDisco;
        this.qtdNucleosCPU = qtdNucleosCPU;
        this.qtdNucleosVirtuaisCPU = qtdNucleosVirtuaisCPU;
        this.percentualCPU = percentualCPU;
        this.frequenciaCPU = frequenciaCPU;
        this.nomeUsuario = nomeUsuario;
        this.dataHora = dataHora;
    }

    public Componente() {
        this.idRegistro = null;
        this.nomeMaquina = "";
        this.sistemaOperacional = "";
        this.qtdTotalMemoria = 0L;
        this.percentualMemoria = 0.0;
        this.qtdTotalDisco = 0L;
        this.qtdUtilizadaDisco = 0L;
        this.percentualDisco = 0.0;
        this.qtdNucleosCPU = 0;
        this.qtdNucleosVirtuaisCPU = 0;
        this.percentualCPU = 0.0;
        this.frequenciaCPU = 0.0;
        this.nomeUsuario = "";
        this.dataHora = "";
    }

    public Integer getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getNomeMaquina() {
        return nomeMaquina;
    }

    public void setNomeMaquina(String nomeMaquina) {
        this.nomeMaquina = nomeMaquina;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }

    public Long getQtdTotalMemoria() {
        return qtdTotalMemoria;
    }

    public void setQtdTotalMemoria(Long qtdTotalMemoria) {
        this.qtdTotalMemoria = qtdTotalMemoria;
    }

    public Double getPercentualMemoria() {
        return percentualMemoria;
    }

    public void setPercentualMemoria(Double percentualMemoria) {
        this.percentualMemoria = percentualMemoria;
    }

    public Long getQtdTotalDisco() {
        return qtdTotalDisco;
    }

    public void setQtdTotalDisco(Long qtdTotalDisco) {
        this.qtdTotalDisco = qtdTotalDisco;
    }

    public Long getQtdUtilizadaDisco() {
        return qtdUtilizadaDisco;
    }

    public void setQtdUtilizadaDisco(Long qtdUtilizadaDisco) {
        this.qtdUtilizadaDisco = qtdUtilizadaDisco;
    }

    public Double getPercentualDisco() {
        return percentualDisco;
    }

    public void setPercentualDisco(Double percentualDisco) {
        this.percentualDisco = percentualDisco;
    }

    public Integer getQtdNucleosCPU() {
        return qtdNucleosCPU;
    }

    public void setQtdNucleosCPU(Integer qtdNucleosCPU) {
        this.qtdNucleosCPU = qtdNucleosCPU;
    }

    public Integer getQtdNucleosVirtuaisCPU() {
        return qtdNucleosVirtuaisCPU;
    }

    public void setQtdNucleosVirtuaisCPU(Integer qtdNucleosVirtuaisCPU) {
        this.qtdNucleosVirtuaisCPU = qtdNucleosVirtuaisCPU;
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

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }
}
