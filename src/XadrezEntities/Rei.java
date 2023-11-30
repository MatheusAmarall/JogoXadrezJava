package XadrezEntities;

import TabuleiroEntities.Enums.Cor;
import TabuleiroEntities.Peca;
import TabuleiroEntities.Posicao;
import TabuleiroEntities.Tabuleiro;

public class Rei extends Peca {
    public Rei(Cor cor, Tabuleiro tab) {
        super(cor, tab);
    }

    @Override
    public String toString() {
        return "R";
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
        if(getTab().posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
        }
        //ne
        pos.definirValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
        if(getTab().posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
        }
        //direita
        pos.definirValores(posicao.getLinha(), posicao.getColuna() + 1);
        if(getTab().posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
        }
        //se
        pos.definirValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
        if(getTab().posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
        }
        //abaixo
        pos.definirValores(posicao.getLinha() + 1, posicao.getColuna());
        if(getTab().posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
        }
        //so
        pos.definirValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
        if(getTab().posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
        }
        //esquerda
        pos.definirValores(posicao.getLinha(), posicao.getColuna() - 1);
        if(getTab().posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
        }
        //no
        pos.definirValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
        if(getTab().posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
        }

        return mat;
    }
}
