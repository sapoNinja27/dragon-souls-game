package Menu;

public class Mascara {

    private String nome;
    private Integer posicaoX,posicaoY,largura,autura;

    public Mascara(String nome, Integer posicaoX, Integer posicaoY, Integer largura, Integer autura) {
        this.nome = nome;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.largura = largura;
        this.autura = autura;
    }

    public Mascara(Integer posicaoX, Integer posicaoY, Integer largura, Integer autura) {
        this.nome = "padrao";
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.largura = largura;
        this.autura = autura;
    }

    public Mascara(){

    }

    public String getNome() {
        return nome;
    }

    public Integer getPosicaoX() {
        return posicaoX;
    }

    public Integer getPosicaoY() {
        return posicaoY;
    }

    public Integer getLargura() {
        return largura;
    }

    public Integer getAutura() {
        return autura;
    }

}
