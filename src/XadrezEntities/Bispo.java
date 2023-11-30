package XadrezEntities;

import TabuleiroEntities.Enums.Cor;
import TabuleiroEntities.Peca;
import TabuleiroEntities.Posicao;
import TabuleiroEntities.Tabuleiro;

public class Bispo extends Peca {

    public Bispo(Cor cor, Tabuleiro tab) {
        super(cor, tab);
    }

    @Override
    public String toString() {
        return "B";
    }

    private boolean podeMover(Posicao pos) {
        Peca p = getTab().peca(pos);
        return p == null || p.getCor() != getCor();
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] mat = new boolean[getTab().getLinhas()][getTab().getColunas()];

        Posicao pos = new Posicao(0, 0);

        //no
        pos.definirValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
        while(getTab().posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
            if(getTab().peca(pos) != null && getTab().peca(pos).getCor() != getCor()) {
                break;
            }
            pos.definirValores(pos.getLinha() - 1, pos.getColuna() - 1);
        }

        //ne
        pos.definirValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
        while(getTab().posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
            if(getTab().peca(pos) != null && getTab().peca(pos).getCor() != getCor()) {
                break;
            }
            pos.definirValores(pos.getLinha() - 1, pos.getColuna() + 1);
        }

        //se
        pos.definirValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
        while(getTab().posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
            if(getTab().peca(pos) != null && getTab().peca(pos).getCor() != getCor()) {
                break;
            }
            pos.definirValores(pos.getLinha() + 1, pos.getColuna() + 1);
        }

        //so
        pos.definirValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
        while(getTab().posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
            if(getTab().peca(pos) != null && getTab().peca(pos).getCor() != getCor()) {
                break;
            }
            pos.definirValores(pos.getLinha() + 1, pos.getColuna() - 1);
        }

        return mat;
    }
}
