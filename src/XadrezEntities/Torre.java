package XadrezEntities;

import TabuleiroEntities.Enums.Cor;
import TabuleiroEntities.Peca;
import TabuleiroEntities.Posicao;
import TabuleiroEntities.Tabuleiro;

public class Torre extends Peca {
    public Torre(Cor cor, Tabuleiro tab) {
        super(cor, tab);
    }

    @Override
    public String toString() {
        return "T";
    }

    private boolean podeMover(Posicao pos) {
        Peca p = getTab().peca(pos);
        return p == null || p.getCor() != getCor();
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] mat = new boolean[getTab().getLinhas()][getTab().getColunas()];

        Posicao pos = new Posicao(0, 0);

        //acima
        pos.definirValores(posicao.getLinha() - 1, posicao.getColuna());
        while(getTab().posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
            if(getTab().peca(pos) != null && getTab().peca(pos).getCor() != getCor()) {
                break;
            }

            pos.setLinha(pos.getLinha() - 1);
        }

        //abaixo
        pos.definirValores(posicao.getLinha() + 1, posicao.getColuna());
        while(getTab().posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
            if(getTab().peca(pos) != null && getTab().peca(pos).getCor() != getCor()) {
                break;
            }

            pos.setLinha(pos.getLinha() + 1);
        }

        //direita
        pos.definirValores(posicao.getLinha(), posicao.getColuna() + 1);
        while(getTab().posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
            if(getTab().peca(pos) != null && getTab().peca(pos).getCor() != getCor()) {
                break;
            }

            pos.setColuna(pos.getColuna() + 1);
        }

        //esquerda
        pos.definirValores(posicao.getLinha(), posicao.getColuna() - 1);
        while(getTab().posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
            if(getTab().peca(pos) != null && getTab().peca(pos).getCor() != getCor()) {
                break;
            }

            pos.setColuna(pos.getColuna() - 1);
        }

        return mat;
    }
}
