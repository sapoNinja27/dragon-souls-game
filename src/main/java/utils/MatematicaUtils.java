package utils;

public class MatematicaUtils {

    public static int posicaoMeio(int tamanhoObj, int tamanhoTela) {
        int parteOff = tamanhoTela - tamanhoObj;
        return parteOff / 2;
    }
}
