import TabuleiroEntities.Enums.Cor;
import TabuleiroEntities.Exceptions.TabuleiroException;
import TabuleiroEntities.Peca;
import TabuleiroEntities.Tabuleiro;
import XadrezEntities.PartidaDeXadrez;
import XadrezEntities.PosicaoXadrez;

import java.util.HashSet;
import java.util.Scanner;

public class Tela {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";

    public static void limpaTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void imprimirPartida(PartidaDeXadrez partida) {
        imprimirTabuleiro(partida.getTab());
        System.out.println();
        imprimirPecasCapturadas(partida);
        System.out.println();
        System.out.println("Turno: " + partida.getTurno());
        if(!partida.isTerminada()) {
            System.out.println("Aguardando jogada: " + partida.getJogadorAtual());
            if(partida.isXeque()) {
                System.out.println("XEQUE!");
            }
        }
        else {
            System.out.println("XEQUEMATE!");
            System.out.println("Vencedor: " + partida.getJogadorAtual());
        }
    }

    public static void imprimirPecasCapturadas(PartidaDeXadrez partida) {
        System.out.println("Peças capturadas:");
        System.out.print("Branca: ");
        imprimirConjunto(partida.pecasCapturadas(Cor.Branca));
        System.out.println();
        System.out.print("Pretas: ");
        System.out.print(ANSI_YELLOW);
        imprimirConjunto(partida.pecasCapturadas(Cor.Preta));
        System.out.print(ANSI_RESET);
        System.out.println();
    }

    public static void imprimirConjunto(HashSet<Peca> conjunto) {
        System.out.print("[");
        for (Peca x : conjunto) {
            System.out.print(x + " ");
        }
        System.out.print("]");
    }

    public static void imprimirTabuleiro(Tabuleiro tab) {
        for (int i = 0; i < tab.getLinhas(); i++) {
            System.out.print(8 - i + " ");
            for (int j = 0; j < tab.getColunas(); j++) {
                imprimirPeca(tab.peca(i, j));
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    public static void imprimirTabuleiro(Tabuleiro tab, boolean[][] posicoesPossiveis) {
        for (int i = 0; i < tab.getLinhas(); i++) {
            System.out.print(8 - i + " ");
            for(int j = 0; j < tab.getColunas(); j++) {
                if(posicoesPossiveis[i][j]) {
                    System.out.print(ANSI_BLUE_BACKGROUND);
                }
                else {
                    System.out.print(ANSI_RESET);
                }
                imprimirPeca(tab.peca(i, j));
                System.out.print(ANSI_RESET);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
        System.out.print(ANSI_RESET);
    }

    public static PosicaoXadrez lerPosicaoXadrez() throws TabuleiroException {
        try {
            Scanner sc = new Scanner(System.in);

            String s = sc.nextLine();
            char coluna = s.charAt(0);
            int linha = Integer.parseInt(s.substring(1));
            return new PosicaoXadrez(coluna, linha);
        }
        catch(Exception e) {
            throw new TabuleiroException("Digite corretamente!");
        }
    }

    public static void imprimirPeca(Peca peca) {
        if (peca == null) {
            System.out.print("- ");
        }
        else {
            if(peca.getCor() == Cor.Branca) {
                System.out.print(peca);
            }
            else {
                System.out.print(ANSI_YELLOW);
                System.out.print(peca);
                System.out.print(ANSI_RESET);
            }
            System.out.print(" ");
        }
    }
}
