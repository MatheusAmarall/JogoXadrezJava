package XadrezEntities;

import TabuleiroEntities.Enums.Cor;
import TabuleiroEntities.Exceptions.TabuleiroException;
import TabuleiroEntities.Peca;
import TabuleiroEntities.Posicao;
import TabuleiroEntities.Tabuleiro;

import java.util.HashSet;

public class PartidaDeXadrez {
    private Tabuleiro tab;
    private int turno;
    private Cor jogadorAtual;
    private boolean terminada;
    private HashSet<Peca> pecas;
    private HashSet<Peca> capturadas;
    private boolean xeque;

    public PartidaDeXadrez() throws TabuleiroException {
        this.tab = new Tabuleiro(8, 8);
        this.turno = 1;
        this.jogadorAtual = Cor.Branca;
        this.terminada = false;
        this.xeque = false;
        this.pecas = new HashSet<Peca>();
        this.capturadas = new HashSet<Peca>();
        colocarPecas();
    }

    public Tabuleiro getTab() {
        return tab;
    }

    public int getTurno() {
        return turno;
    }

    public boolean isTerminada() {
        return terminada;
    }

    public Cor getJogadorAtual() {
        return jogadorAtual;
    }

    public boolean isXeque() {
        return xeque;
    }

    public Peca executaMovimento(Posicao origem, Posicao destino) throws TabuleiroException {
        Peca p = tab.retirarPeca(origem);
        p.incrementarQtdeMovimentos();
        Peca pecaCapturada = tab.retirarPeca(destino);
        tab.colocarPeca(p, destino);
        if(pecaCapturada != null) {
            capturadas.add(pecaCapturada);
        }

        return pecaCapturada;
    }

    public void desfazMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) throws TabuleiroException {
        Peca p = tab.retirarPeca(destino);
        p.decrementarQtdeMovimentos();
        if(pecaCapturada != null) {
            tab.colocarPeca(pecaCapturada, destino);
            capturadas.remove(pecaCapturada);
        }
        tab.colocarPeca(p, origem);
    }

    public void realizaJogada(Posicao origem, Posicao destino) throws TabuleiroException {
        Peca pecaCapturada = executaMovimento(origem, destino);

        if(estaEmXeque(jogadorAtual)) {
            desfazMovimento(origem, destino, pecaCapturada);
            throw new TabuleiroException("Você não pode se colocar em xeque");
        }

        if(estaEmXeque(adversaria(jogadorAtual))) {
            xeque = true;
        }
        else {
            xeque = false;
        }

        if(testeXequeMate(adversaria(jogadorAtual))) {
            terminada = true;
        }
        else {
            turno++;
            mudaJogador();
        }
    }

    public void validarPosicaoOrigem(Posicao pos) throws TabuleiroException {
        if(!tab.posicaoValida(pos)) {
            throw new TabuleiroException("Posição inválida!");
        }
        if(tab.peca(pos) == null) {
            throw new TabuleiroException("Não existe peça na posição de origem escolhida!");
        }
        if(jogadorAtual != tab.peca(pos).getCor())
        {
            throw new TabuleiroException("A peça de origem escolhida não é sua!");
        }
        if(!tab.peca(pos).existeMovimentosPossiveis())
        {
            throw new TabuleiroException("Não há movimentos possíveis para a peça de origem escolhida");
        }
    }

    public void validarPosicaoDestino(Posicao origem, Posicao destino) throws TabuleiroException {
        if(!tab.posicaoValida(destino)) {
            throw new TabuleiroException("Posição inválida!");
        }
        if(!tab.peca(origem).movimentoPossivel(destino)) {
            throw new TabuleiroException("Posição de destino inválida!");
        }
    }

    private void mudaJogador() {
        if(jogadorAtual == Cor.Branca) {
            jogadorAtual = Cor.Preta;
        }
        else {
            jogadorAtual = Cor.Branca;
        }
    }

    public HashSet<Peca> pecasCapturadas(Cor cor) {
        HashSet<Peca> aux = new HashSet<Peca>();
        for (Peca x : capturadas) {
            if(x.getCor() == cor) {
                aux.add(x);
            }
        }
        return aux;
    }

    public HashSet<Peca> pecasEmJogo(Cor cor) {
        HashSet<Peca> aux = new HashSet<Peca>();
        for (Peca x : pecas) {
            if(x.getCor() == cor) {
                aux.add(x);
            }
        }
        aux.removeAll(pecasCapturadas(cor));
        return aux;
    }

    private Cor adversaria(Cor cor) {
        if(cor == Cor.Branca) {
            return Cor.Preta;
        }
        else {
            return Cor.Branca;
        }
    }

    private Peca rei(Cor cor) {
        for (Peca x : pecasEmJogo(cor)) {
            if(x instanceof Rei) {
                return x;
            }
        }
        return null;
    }

    public boolean estaEmXeque(Cor cor) throws TabuleiroException {
        Peca r = rei(cor);
        if(r == null) {
            throw new TabuleiroException("Não tem rei da cor " + cor + " no tabuleiro!");
        }

        for (Peca x: pecasEmJogo(adversaria(cor))) {
            boolean[][] mat = x.movimentosPossiveis();
            if(mat[r.posicao.getLinha()][r.posicao.getColuna()]) {
                return true;
            }
        }
        return false;
    }

    public boolean testeXequeMate(Cor cor) throws TabuleiroException {
        if(!estaEmXeque(cor)) {
            return false;
        }
        for (Peca x : pecasEmJogo(cor)) {
            boolean[][] mat = x.movimentosPossiveis();
            for (int i = 0; i < tab.getLinhas(); i++) {
                for (int j = 0; j < tab.getColunas(); j++) {
                    if(mat[i][j]) {
                        Posicao origem = x.posicao;
                        Posicao destino = new Posicao(i, j);
                        Peca pecaCapturada = executaMovimento(origem, destino);
                        boolean testeXeque = estaEmXeque(cor);
                        desfazMovimento(origem, destino, pecaCapturada);
                        if(!testeXeque) {
                            return false;
                        }

                    }
                }
            }
        }
        return true;
    }

    public void colocarNovaPeca(char coluna, int linha, Peca peca) throws TabuleiroException {
        tab.colocarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
        pecas.add(peca);
    }

    private void colocarPecas() throws TabuleiroException {
        colocarNovaPeca('a', 1, new Torre(Cor.Branca, tab));
        colocarNovaPeca('b', 1, new Cavalo(Cor.Branca, tab));
        colocarNovaPeca('c', 1, new Bispo(Cor.Branca, tab));
        colocarNovaPeca('d', 1, new Dama(Cor.Branca, tab));
        colocarNovaPeca('e', 1, new Rei(Cor.Branca, tab));
        colocarNovaPeca('f', 1, new Bispo(Cor.Branca, tab));
        colocarNovaPeca('g', 1, new Cavalo(Cor.Branca, tab));
        colocarNovaPeca('h', 1, new Torre(Cor.Branca, tab));
        colocarNovaPeca('a', 2, new Peao(Cor.Branca, tab));
        colocarNovaPeca('b', 2, new Peao(Cor.Branca, tab));
        colocarNovaPeca('c', 2, new Peao(Cor.Branca, tab));
        colocarNovaPeca('d', 2, new Peao(Cor.Branca, tab));
        colocarNovaPeca('e', 2, new Peao(Cor.Branca, tab));
        colocarNovaPeca('f', 2, new Peao(Cor.Branca, tab));
        colocarNovaPeca('g', 2, new Peao(Cor.Branca, tab));
        colocarNovaPeca('h', 2, new Peao(Cor.Branca, tab));

        colocarNovaPeca('a', 8, new Torre(Cor.Preta, tab));
        colocarNovaPeca('b', 8, new Cavalo(Cor.Preta, tab));
        colocarNovaPeca('c', 8, new Bispo(Cor.Preta, tab));
        colocarNovaPeca('d', 8, new Dama(Cor.Preta, tab));
        colocarNovaPeca('e', 8, new Rei(Cor.Preta, tab));
        colocarNovaPeca('f', 8, new Bispo(Cor.Preta, tab));
        colocarNovaPeca('g', 8, new Cavalo(Cor.Preta, tab));
        colocarNovaPeca('h', 8, new Torre(Cor.Preta, tab));
        colocarNovaPeca('a', 7, new Peao(Cor.Preta, tab));
        colocarNovaPeca('b', 7, new Peao(Cor.Preta, tab));
        colocarNovaPeca('c', 7, new Peao(Cor.Preta, tab));
        colocarNovaPeca('d', 7, new Peao(Cor.Preta, tab));
        colocarNovaPeca('e', 7, new Peao(Cor.Preta, tab));
        colocarNovaPeca('f', 7, new Peao(Cor.Preta, tab));
        colocarNovaPeca('g', 7, new Peao(Cor.Preta, tab));
        colocarNovaPeca('h', 7, new Peao(Cor.Preta, tab));
    }

}
