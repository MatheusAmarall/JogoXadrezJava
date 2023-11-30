package TabuleiroEntities;

import TabuleiroEntities.Enums.Cor;

public abstract class Peca {
    public Posicao posicao;
    private Cor cor;
    private int qtdeMovimentos;
    private Tabuleiro tab;

    public Peca(Cor cor, Tabuleiro tab) {
        this.posicao = null;
        this.cor = cor;
        this.tab = tab;
        this.qtdeMovimentos = 0;
    }

    public void incrementarQtdeMovimentos() {
        qtdeMovimentos++;
    }

    public void decrementarQtdeMovimentos() {
        qtdeMovimentos--;
    }

    public boolean existeMovimentosPossiveis() {
        boolean[][] mat = movimentosPossiveis();
        for(int i = 0; i < tab.getLinhas(); i++) {
            for(int j = 0; j < tab.getColunas(); j++) {
                if(mat[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean movimentoPossivel(Posicao pos) {
        return movimentosPossiveis()[pos.getLinha()][pos.getColuna()];
    }

    public abstract boolean[][] movimentosPossiveis();

    public Cor getCor() {
        return cor;
    }

    public int getQtdeMovimentos() {
        return qtdeMovimentos;
    }

    public Tabuleiro getTab() {
        return tab;
    }

    public Posicao getPosicao() {
        return posicao;
    }
}
