import TabuleiroEntities.Exceptions.TabuleiroException;
import TabuleiroEntities.Posicao;
import XadrezEntities.PartidaDeXadrez;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws TabuleiroException {
        Scanner sc = new Scanner(System.in);
        PartidaDeXadrez partida = new PartidaDeXadrez();

        while(!partida.isTerminada()) {
            try {
                Tela.limpaTela();
                Tela.imprimirPartida(partida);

                System.out.println();
                System.out.print("Origem: ");
                Posicao origem = Tela.lerPosicaoXadrez().toPosicao();
                partida.validarPosicaoOrigem(origem);

                boolean[][] posicoesPossiveis = partida.getTab().peca(origem).movimentosPossiveis();

                Tela.limpaTela();
                Tela.imprimirTabuleiro(partida.getTab(), posicoesPossiveis);
                System.out.println();
                System.out.print("Destino: ");
                Posicao destino = Tela.lerPosicaoXadrez().toPosicao();
                partida.validarPosicaoDestino(origem, destino);

                partida.realizaJogada(origem, destino);
            }
            catch(TabuleiroException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
            catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
        Tela.limpaTela();
        Tela.imprimirPartida(partida);
    }
}