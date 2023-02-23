package entidades.mascaras;


public class MascaraHurtBox extends Mascara{

    public MascaraHurtBox(String nome, Integer posicaoX, Integer posicaoY, Integer largura, Integer autura) {
        super(nome, posicaoX, posicaoY, largura, autura);
    }

    public MascaraHurtBox(Integer posicaoX, Integer posicaoY, Integer largura, Integer autura) {
        super(posicaoX, posicaoY, largura, autura);
    }

    public MascaraHurtBox(){

    }

}
