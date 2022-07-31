package Menu;

public class Animacao {

    private String momento;
    private Integer frames = 0;
    private Integer index, framesMaximos, indexMaximo;
    private Boolean executando;

    public Animacao(Integer index, Integer framesMaximos, Integer indexMaximo) {
        this.index = index;
        this.framesMaximos = framesMaximos;
        this.indexMaximo = indexMaximo;
    }
    private void pre(){

    }

    private void loopCaminhar(){
        int indexMinimoAndando = 4, indexMaximoAndando = 11, framesMaximosAndando = 6;
        if(index < indexMinimoAndando){
            index = indexMinimoAndando;
        }
        frames++;
        if(frames >= framesMaximosAndando){
            index++;
            frames = 0;
        }
        if(index > indexMaximoAndando){
            index = indexMinimoAndando;
        }
    }

    private void terminar(){

    }

    public void anexarIndex() {

    }
}
