package enums;

import java.util.Arrays;
import java.util.HashMap;

public enum AnimacaoPlayer {
	CAINDO(4, 15, 17, 0),
	SUBINDO(13, 15, 17, 0),
	POUSANDO(4, 15, 17, 0),
	ANDANDO(4, 6, 12, 0),
	PARANDO(12, 15, 17, 0),
	ATACANDO(27, 5, 33, 0),
	RESPIRANDO(0, 17, 4, 0),
	RESPIRANDO_EM_COMBATE(4, 15, 17, 0),
	INVESTINDO(4, 15, 17, 0),
	PARANDO_INVESTIDA(4, 15, 17, 0);

	private Integer frames = 0;
	private Integer indexAtualizado = 0;
	private Integer index, framesMaximos, indexMaximo;

	public static HashMap<AnimacaoPlayer, Boolean> criarAnimacaoPadrao(){
		HashMap<AnimacaoPlayer, Boolean> lista = new HashMap<>();
		Arrays.stream(AnimacaoPlayer.values()).forEach(acaoPlayer -> {
			lista.put(acaoPlayer, false);
		});
		return lista;
	}
	private AnimacaoPlayer(Integer index, Integer framesMaximos, Integer indexMaximo, Integer limiteExecucao) {
		this.frames = 0;
		this.index = index;
		this.framesMaximos = framesMaximos;
		this.indexMaximo = indexMaximo;
	}

	public Integer getFrameAtual(){
		frames++;
		if (frames >= framesMaximos) {
			frames = 0;
			indexAtualizado ++;
		}
		if(indexAtualizado >= indexMaximo){
			indexAtualizado = index;
		}
		return  indexAtualizado;
	}
}
