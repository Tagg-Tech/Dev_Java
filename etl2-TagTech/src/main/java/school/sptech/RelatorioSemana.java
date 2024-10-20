package school.sptech;

import java.util.OptionalDouble;

public class RelatorioSemana {
    private OptionalDouble picoCpu;
    private Double mediaCpu;
    private OptionalDouble picoRam;
    private Double mediaRam;
    private Long mediaDisc;

    public RelatorioSemana(OptionalDouble picoCpu, Double mediaCpu, OptionalDouble picoRam, Double mediaRam, Long mediaDisc) {
        this.picoCpu = picoCpu;
        this.mediaCpu = mediaCpu;
        this.picoRam = picoRam;
        this.mediaRam = mediaRam;
        this.mediaDisc = mediaDisc;
    }


    public OptionalDouble getPicoCpu() {return picoCpu;}
    public void setPicoCpu(Double picoCpu) {this.picoCpu = OptionalDouble.of(picoCpu);}

    public void setMediaCpu(Double mediaCpu) {this.mediaCpu = mediaCpu;}
    public Double getMediaCpu() {return mediaCpu;}

    public OptionalDouble getPicoRam() {return picoRam;}
    public void setPicoRam(Double picoRam) {this.picoRam = OptionalDouble.of(picoRam);}

    public Double getMediaRam() {return mediaRam;}
    public void setMediaRam(Double mediaRam) {this.mediaRam = mediaRam;}

    public Long getMediaDisc() { return mediaDisc; }
    public void setMediaDisc(Long mediaDisc) { this.mediaDisc = mediaDisc; }

    @Override
    public String toString() {
        return "RelatorioSemana{" +
                "picoCpu=" + picoCpu +
                ", mediaCpu=" + mediaCpu +
                ", picoRam=" + picoRam +
                ", mediaRam=" + mediaRam +
                ", mediaDisc=" + mediaDisc +
                '}';
    }
}
