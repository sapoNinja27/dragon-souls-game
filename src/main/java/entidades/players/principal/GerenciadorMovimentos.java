package entidades.players.principal;

import main.enums.MovimentoPlayer;
import lombok.Getter;

import java.awt.image.BufferedImage;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static main.enums.MovimentoPlayer.*;
import static java.util.Objects.nonNull;

public class GerenciadorMovimentos {
    private MovimentoPlayer ultimaAcao = RESPIRANDO;
    @Getter
    private MovimentoPlayer acaoAtual = RESPIRANDO;

    private final Set<MovimentoPlayer> acoesEmCooldown = new HashSet<>(0);
    boolean processando = false;

    public void tick() {
        atualizarCooldown();
        acaoAtual.getAnimacao().tick(criarAcoesAdicionais());
    }

    private void atualizarCooldown(){
        if(processando) return;
        MovimentoPlayer acao = acoesEmCooldown.stream().findAny().orElse(null);
        if(nonNull(acao)){
            processando = true;
            esperar(acao.getInitialCooldown(), () -> {
                acoesEmCooldown.remove(acao);
                processando = false;
            });
        }
    }
    public BufferedImage getSprite() {
        return acaoAtual.getAnimacao().getSprite();
    }

    private Runnable[] criarAcoesAdicionais() {
        List<Runnable> acoes = new ArrayList<>();
        switch (acaoAtual) {
            case PARANDO:
            case POUSANDO:
                acoes.add(() -> esperar(0.2, () -> setarAnimacao(RESPIRANDO)));
                break;
            case SUBINDO:
                acoes.add(() -> setarAnimacao(CAINDO));
                break;
        }
        return acoes.toArray(new Runnable[0]);
    }

    public void setarAnimacao(MovimentoPlayer movimento) {
        if (acoesEmCooldown.contains(movimento)) {
            return;
        }
        if (movimento.equals(ANDANDO) && (acaoAtual.equals(SUBINDO) || acaoAtual.equals(CAINDO))) {
            return;
        }
        if (movimento.equals(POUSANDO) && !acaoAtual.equals(CAINDO)) {
            return;
        }
        if (movimento.equals(SUBINDO) && (ultimaAcao.equals(CAINDO) || ultimaAcao.equals(SUBINDO))) {
            return;
        }
        ultimaAcao = acaoAtual;
        acaoAtual = movimento;
        if(movimento.getInitialCooldown() > 0){
            acoesEmCooldown.add(movimento);
        }
    }

    public void setarAnimacao(MovimentoPlayer movimentoPlayer, double segundosDelay) {
        esperar(segundosDelay, () -> setarAnimacao(movimentoPlayer));
    }

    private void esperar(double segundos, Runnable then) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.schedule(() -> {
            then.run();
            executor.shutdown();
        }, (long) (segundos * 1000), TimeUnit.MILLISECONDS);
    }

    public boolean noAr() {
        return acaoAtual.equals(SUBINDO) || acaoAtual.equals(CAINDO);
    }
}
