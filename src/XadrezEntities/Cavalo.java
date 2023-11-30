package XadrezEntities;

import TabuleiroEntities.Enums.Cor;
import TabuleiroEntities.Peca;
import TabuleiroEntities.Posicao;
import TabuleiroEntities.Tabuleiro;

public class Cavalo extends Peca {
    public Cavalo(Cor cor, Tabuleiro tab) {
        super(cor, tab);
    }

    @Override
    public String toString() {
        return "C";
    }

    private boolean podeMover(Posicao pos) {
        Peca p = getTab().peca(pos);
        return p == null || p.getCor() != getCor();
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] mat = new boolean[getTab().getLinhas()][getTab().getColunas()];

        Posicao pos = new Posicao(0, 0);

        pos.definirValores(posicao.getLinha() - 1, posicao.getColuna() - 2);
        if(getTab().posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
        }
        pos.definirValores(posicao.getLinha() - 2, posicao.getColuna() - 1);
        if(getTab().posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
        }
        pos.definirValores(posicao.getLinha() - 2, posicao.getColuna() + 1);
        if(getTab().posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
        }
        pos.definirValores(posicao.getLinha() - 1, posicao.getColuna() + 2);
        if(getTab().posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
        }
        pos.definirValores(posicao.getLinha() + 1, posicao.getColuna() + 2);
        if(getTab().posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
        }
        pos.definirValores(posicao.getLinha() + 2, posicao.getColuna() + 1);
        if(getTab().posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
        }
        pos.definirValores(posicao.getLinha() + 2, posicao.getColuna() - 1);
        if(getTab().posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
        }
        pos.definirValores(posicao.getLinha() + 1, posicao.getColuna() - 2);
        if(getTab().posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
        }

        return mat;
    }
}
