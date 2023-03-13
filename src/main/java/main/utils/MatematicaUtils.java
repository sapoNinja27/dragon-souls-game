package main.utils;

public class MatematicaUtils {

    public static int posicaoMeio(int tamanhoObj, int tamanhoTela) {
        int parteOff = tamanhoTela - tamanhoObj;
        return parteOff / 2;
    }

    public static int porcentagem(int total, double porcentagem) {
       return (int)(total * (porcentagem / 100.0));
    }
}
