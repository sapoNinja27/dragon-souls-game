package main.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Colisao {
    private String atual;
    private String alvo;
    private Entidade entidadeAlvo;

    @Override
    public boolean equals(Object o) {
        Colisao colisao = (Colisao) o;
        return Objects.equals(atual, colisao.atual) && Objects.equals(alvo, colisao.alvo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(atual, alvo);
    }
}
