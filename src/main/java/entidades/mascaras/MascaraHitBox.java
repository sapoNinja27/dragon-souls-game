package entidades.mascaras;


public class MascaraHitBox extends Mascara{

    public MascaraHitBox(String nome, Integer posicaoX, Integer posicaoY, Integer largura, Integer autura) {
        super(nome, posicaoX, posicaoY, largura, autura);
    }

    public MascaraHitBox(Integer posicaoX, Integer posicaoY, Integer largura, Integer autura) {
        super(posicaoX, posicaoY, largura, autura);
    }

    public MascaraHitBox(){

    }

}
