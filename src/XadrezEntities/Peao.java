package XadrezEntities;

import TabuleiroEntities.Enums.Cor;
import TabuleiroEntities.Peca;
import TabuleiroEntities.Posicao;
import TabuleiroEntities.Tabuleiro;

public class Peao extends Peca {
    public Peao(Cor cor, Tabuleiro tab) {
        super(cor, tab);
    }

    @Override
    public String toString() {
        return "P";
    }

    private boolean existeInimigo(Posicao pos) {
        Peca p = getTab().peca(pos);
        return p != null && p.getCor() != getCor();
    }

    private boolean livre(Posicao pos) {
        return getTab().peca(pos) == null;
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] mat = new boolean[getTab().getLinhas()][getTab().getColunas()];

        Posicao pos = new Posicao(0, 0);

        if(getCor() == Cor.Branca) {
            pos.definirValores(posicao.getLinha() - 1, posicao.getColuna());
            if(getTab().posicaoValida(pos) && livre(pos)) {
                mat[pos.getLinha()][pos.getColuna()] = true;
            }
            pos.definirValores(posicao.getLinha() - 2, posicao.getColuna());
            if(getTab().posicaoValida(pos) && livre(pos) && getQtdeMovimentos() == 0) {
                mat[pos.getLinha()][pos.getColuna()] = true;
            }
            pos.definirValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
            if (getTab().posicaoValida(pos) && existeInimigo(pos))
            {
                mat[pos.getLinha()][pos.getColuna()] = true;
            }
            pos.definirValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
            if (getTab().posicaoValida(pos) && existeInimigo(pos))
            {
                mat[pos.getLinha()][pos.getColuna()] = true;
            }
        }
        else {
            pos.definirValores(posicao.getLinha() + 1, posicao.getColuna());
            if(getTab().posicaoValida(pos) && livre(pos)) {
                mat[pos.getLinha()][pos.getColuna()] = true;
            }
            pos.definirValores(posicao.getLinha() + 2, posicao.getColuna());
            if(getTab().posicaoValida(pos) && livre(pos) && getQtdeMovimentos() == 0) {
                mat[pos.getLinha()][pos.getColuna()] = true;
            }
            pos.definirValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
            if (getTab().posicaoValida(pos) && existeInimigo(pos))
            {
                mat[pos.getLinha()][pos.getColuna()] = true;
            }
            pos.definirValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
            if (getTab().posicaoValida(pos) && existeInimigo(pos))
            {
                mat[pos.getLinha()][pos.getColuna()] = true;
            }
        }

        return mat;
    }
}
