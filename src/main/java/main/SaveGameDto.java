package main;

import lombok.Builder;
import lombok.Getter;
import main.entidades.players.Player;
import main.enums.TipoAmbiente;

@Getter
@Builder
public class SaveGameDto {
    private TipoAmbiente local;
    private boolean dia;
    private String pathArea;
    private boolean musica;
    private Boolean efeitos;
    private Boolean idioma;
    private Integer volume;
    private PlayerDto playerDto;

    public Player getPlayer(DadosGame dadosGame) {
        return playerDto.getPlayer(dadosGame);
    }

    public void setPlayer(Player player) {
        this.playerDto = new PlayerDto(player);
    }

    static class PlayerDto {
        int x, y;
        int nivel;
        int xp;
        int pontosHabilidade;
        int vida, vidaMaxima, mana, defesa, manaMaxima, resistencia;

        PlayerDto(Player player) {
            this.x = player.getX();
            this.y = player.getY();
            this.nivel = player.getNivel();
            this.vidaMaxima = player.getVidaMaxima();
            this.pontosHabilidade = player.getPontosHabilidade();
            this.vida = player.getVida();
            this.mana = player.getMana();
            this.manaMaxima = player.getManaMaxima();
            this.defesa = player.getDefesa();
            this.resistencia = player.getResistencia();
            this.xp = player.getXp();
        }

        Player getPlayer(DadosGame dadosGame) {
            return new Player(
                    x, y, dadosGame.getTileSize(), dadosGame.getTileSize(),
                    nivel, xp, pontosHabilidade, vida, vidaMaxima, mana,
                    manaMaxima, defesa, resistencia
            );
        }

        public String toString() {
            return "{" +
                    "nivel:" + nivel +
                    "xp:" + xp +
                    "pontosHabilidade:" + pontosHabilidade +
                    "vida:" + vida +
                    "vidaMaxima:" + vidaMaxima +
                    "mana:" + mana +
                    "manaMaxima:" + manaMaxima +
                    "defesa:" + defesa +
                    "resistencia:" + resistencia + "}";
        }
    }
}
