import java.util.Scanner;
import java.util.Random;
import java.io.*;

public class Main {
  public static void imprimirEstado(double vidaAlly, double vidaEnemy, double mpAlly) {
    System.out.println("Tu Pokemon:");
    System.out.println("Vida: " + vidaAlly);
    System.out.println("Mp: " + mpAlly);
    System.out.println("Pokemon enemigo:");
    System.out.println("Vida: " + vidaEnemy);
  }

  public static void imprimirDanho(double danho, String nomAttacker, String nomDeffender, String nomAtk, int crit) {
    System.out.println(nomAttacker + " usó " + nomAtk);
    if (crit == 2) {
      System.out.println("CRÍTICO");
      System.out.println(nomAttacker + " hizo " + danho + " puntos de daño a " + nomDeffender);
    } else if (crit == 1) {
      System.out.println(nomAttacker + " ha fallado");
    } else {
      System.out.println(nomAttacker + " hizo " + danho + "puntos de vida a " + nomDeffender);
    }

  }

  public static int probabilidad(int maxRand, int minRand) {
    // final String RESET = "\u001B[0m"; DEBUG
    // final String RED = "\u001B[31m"; DEBUG
    Random rand = new Random();
    int randNum;
    randNum = rand.nextInt(maxRand - minRand + 1) + 1;
    // System.out.println("Número aleatorio es: " + randNum ); DEBUG
    return randNum;
  }

  public static boolean vidasCombate(double hpPkm1, double hpPkmn2, String pkm1, String pkm2) {
    boolean estado = false;
    if (hpPkm1 <= 0 || hpPkmn2 <= 0) {
      if (hpPkm1 <= 0) {
        System.out.println(pkm1 + " ha sido derrotado, el ganador es " + pkm2);
        estado = true;
      }
      if (hpPkmn2 <= 0) {
        System.out.println(pkm2 + " ha sido derrotado, el ganador es " + pkm1);
        estado = true;
      }
      return estado;
    }

    return estado;
  }

  public static int depurarInput(Scanner gameInput, int maxValue, int minValue) {
    int numInput = -1; // Inicializamos la variable a -1 para que siempre entre en el loop
    numInput = gameInput.nextInt();
    while (!(minValue <= numInput && numInput <= maxValue)) {
      System.out.println("Por favor escribe un valor válido:");
      numInput = gameInput.nextInt();
    }
    return (numInput);
  }

  public static void main(String[] args) {
    final String RESET = "\u001B[0m";
    final String RED = "\u001B[31m";
    Scanner userInput = new Scanner(System.in);
    int playerChoice = 0;
    boolean finCombate = false;
    int critStrike = 0;
    // Iniciamos los movimientos que vamos a usar en este ejemplo simplificado

    // Iniciamos el primer pokemon, Pikachu
    Pokemon pikachu = new Pokemon();
    pikachu.nombre = "Pikachu";
    pikachu.vida = 120;
    pikachu.mp = 50;
    pikachu.danhoAtk1 = 10;
    pikachu.defensaF = 5;
    pikachu.golpeCrit = 20;
    pikachu.pkmMoves.put("impactrueno", new Move("Impactrueno", 50, 15));
    // Iniciamos el segundo pokemon, Charmander
    Pokemon charmander = new Pokemon();
    charmander.nombre = "Charmander";
    charmander.vida = 120;
    charmander.mp = 50;
    charmander.danhoAtk1 = 10;
    charmander.defensaF = 5;
    charmander.pkmMoves.put("mordisco", new Move("Mordisco", 50, 15));
    charmander.golpeCrit = 20;

    // Combate
    int i = 1;
    int ronda = 1;
    while (!finCombate) {
      System.out.println("RONDA: " + ronda);
      if (ronda % 2 == 0) {
        i = 2;
        System.out.println("Empieza la máquina");
      } else if (ronda % 2 == 1) {
        i = 1;
        System.out.println("Empiezas tú");
      }
      ronda++;
      for (int i1 = 0; i1 < 2 && !finCombate; i1++) {
        if (i % 2 == 1) {
          // Combate sección jugador
          playerChoice = 0;
          while (playerChoice != 1) {
            System.out.println("Combate, escribe el número para ejecutar la acción");
            System.out.println("1: Atacar");
            System.out.println("2: Imprimir Stats");
            playerChoice = depurarInput(userInput, 2, 1);
            System.out.println();
            switch (playerChoice) {
              case 1:
                critStrike = (int) probabilidad(pikachu.golpeCrit, 1);
                if (critStrike == pikachu.golpeCrit) {
                  charmander.vida = pikachu.ataque(charmander.defensaF, charmander.vida, true, 1);
                  imprimirDanho(pikachu.pkmAttacks[playerChoice] * 1.5 - charmander.defensaF, pikachu.nombre,
                      charmander.nombre,
                      pikachu.pkmAttackNames[playerChoice], 2);
                  finCombate = vidasCombate(pikachu.vida, charmander.vida, pikachu.nombre, charmander.nombre);
                } else if (critStrike == 1) {
                  imprimirDanho(0, pikachu.nombre, charmander.nombre, pikachu.pkmAttackNames[playerChoice], 1);
                } else {
                  charmander.vida = pikachu.ataque(charmander.defensaF, charmander.vida, false, 1);
                  imprimirDanho(pikachu.pkmAttacks[playerChoice], pikachu.nombre, charmander.nombre,
                      pikachu.pkmAttackNames[playerChoice], 0);
                  finCombate = vidasCombate(pikachu.vida, charmander.vida, pikachu.nombre, charmander.nombre);
                }
                break;
              case 2:
                imprimirEstado(pikachu.vida, charmander.vida, pikachu.mp);
                break;
              default:
                System.out.println("Se ha roto el sistema de decision del jugador");
            }
          }
        }
        if (i % 2 == 0) {
          // Combate sección máquina
          critStrike = (int) probabilidad(charmander.golpeCrit, 1);
          if (critStrike == charmander.golpeCrit) {
            pikachu.vida = charmander.ataque(pikachu.defensaF, pikachu.vida, true, 1);
            imprimirDanho(charmander.pkmAttacks[1] * 1.5 - pikachu.defensaF, charmander.nombre, pikachu.nombre,
                charmander.pkmAttackNames[1], 2);
            finCombate = vidasCombate(pikachu.vida, charmander.vida, pikachu.nombre, charmander.nombre);
          } else if (critStrike == 0) {
            imprimirDanho(0, charmander.nombre, pikachu.nombre, charmander.pkmAttackNames[1], 1);
          } else {
            pikachu.vida = charmander.ataque(pikachu.defensaF, pikachu.vida, false, 1);
            imprimirDanho(charmander.pkmAttacks[1] - pikachu.defensaF, charmander.nombre, pikachu.nombre,
                charmander.pkmAttackNames[1], 0);
            finCombate = vidasCombate(pikachu.vida, charmander.vida, pikachu.nombre, charmander.nombre);
          }
        }
        i++;
      }
    }
  }
}
