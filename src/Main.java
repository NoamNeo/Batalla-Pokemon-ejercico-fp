import java.util.Scanner;
import java.util.Map;
import java.util.Random;

public class Main {
  // Función para imprimir los movimientos que puede usar el usuario. movimientos
  // es un Hashmap con la forma <Integer, Move> siendo Move una clase
  public static void imprimirMovimientos(Map<Integer, Move> movimientos) {
    for (int i = 1; i < movimientos.size(); i++) {
      System.out.println(i + ": " + movimientos.get(i).nombre + " mp: " + movimientos.get(i).mp);
    }
  }

  // Función que imprime la vida actual de tu pokemon y la de tu oponente en
  // combate.
  // vidaAlly es la vida de tu pokemon
  // vidaEnemy es la vida de tu oponente
  // mpAlly es el mp de tu pokemon
  public static void imprimirEstado(double vidaAlly, double vidaEnemy, double mpAlly) {
    final String RESET = "\u001B[0m";
    final String RED = "\u001B[31m";
    final String GREEN = "\u001B[32m";
    System.out.println(GREEN + "Tu Pokemon:" + RESET);
    System.out.println("Vida: " + vidaAlly);
    System.out.println("Mp: " + mpAlly);
    System.out.println();
    System.out.println(RED + "Pokemon enemigo:" + RESET);
    System.out.println("Vida: " + vidaEnemy);
    System.out.println();
  }

  // Función para imprimir el daño que se realiza en un ataque
  // Se puede llamar tanto en el ataque de tu oponente como en el ataque tuyo
  // danho es el daño que se va a imprimir en pantalla
  // nomAttacker es en nombre del pokemon que realizó el ataque
  // nomDeffender es el nombre del pokemon que recibió el daño
  // nomAtk es el nombre del movimiento
  // crit es la variable que usaoms para checkear si el atque fue crítico o no. En
  // el caso de que sí lo es se imprimirá a pantalla
  // CRÍTICO
  public static void imprimirDanho(double danho, String nomAttacker, String nomDeffender, String nomAtk, int crit) {
    final String RESET = "\u001B[0m";
    final String RED = "\u001B[31m";
    System.out.println(nomAttacker + " usó " + nomAtk);
    if (crit == 20) { // hardcoded valor temporal porque por ahora no tengo una buena manera de
                      // checkear si fue un crítico o no
      System.out.println(RED + "CRÍTICO" + RESET);
      System.out.println(nomAttacker + " hizo " + danho + " puntos de daño a " + nomDeffender);
      System.out.println();
    } else if (crit == 1) {
      System.out.println(nomAttacker + " ha fallado");
      System.out.println();
    } else {
      System.out.println(nomAttacker + " hizo " + danho + "puntos de vida a " + nomDeffender);
      System.out.println();
    }

  }

  // Función que maneja probabilidades dentro de un rango que le podemos pasar
  // maxRand es el número máximo que queremos como output
  // minRand es el número mínimo que queremos como output
  // Ambos están incluídos, es decir el resultado estará dentro del rango
  // [minRand, maxRand] no (minRand, maxRand)
  public static int probabilidad(int maxRand, int minRand) {
    Random rand = new Random();
    int randNum;
    randNum = rand.nextInt(maxRand - minRand + 1) + 1;
    return randNum;
  }

  // Función que maneja si el combate termina o no
  // hpPkm1 y hpPkm2 son las vidas de cada pokemon del combate
  // pkm1 y pkm2 es el nombre de cada pokemon en el combate, se usa para el output
  // a consola para que quede claro quien gana a quien
  public static boolean vidasCombate(double hpPkm1, double hpPkmn2, String pkm1, String pkm2) {
    boolean estado = false;
    if (hpPkm1 <= 0 || hpPkmn2 <= 0) {
      if (hpPkm1 <= 0) {
        System.out.println(pkm1 + " ha sido derrotado, el ganador es " + pkm2);
        System.out.println();
        estado = true;
      }
      if (hpPkmn2 <= 0) {
        System.out.println(pkm2 + " ha sido derrotado, el ganador es " + pkm1);
        System.out.println();
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
    Scanner userInput = new Scanner(System.in);
    boolean playerChoice = true;
    boolean finCombate = false;
    int moveOption = 0;
    int option = 0;
    int critStrike = 0;
    double tempLife = 0;

    // Iniciamos el primer pokemon, Pikachu
    Pokemon pikachu = new Pokemon();
    pikachu.nombre = "Pikachu";
    pikachu.vida = 120;
    pikachu.mp = 50;
    pikachu.defensaF = 5;
    pikachu.golpeCrit = 20;
    // Le damos movimientos
    pikachu.pkmMoves.put(0, new Move("Struggle", 20, 0));
    pikachu.pkmMoves.put(1, new Move("Impactrueno", 50, 15));
    pikachu.pkmMoves.put(2, new Move("Cola Ferrea", 60, 20));

    // Iniciamos el segundo pokemon, Charmander
    Pokemon charmander = new Pokemon();
    charmander.nombre = "Charmander";
    charmander.vida = 120;
    charmander.mp = 50;
    charmander.defensaF = 5;
    charmander.golpeCrit = 20;
    // Le damos movimientos
    charmander.pkmMoves.put(0, new Move("Struggle", 20, 0));
    charmander.pkmMoves.put(1, new Move("Mordisco", 50, 15));

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
          playerChoice = true;
          while (playerChoice) {
            System.out.println("Combate, escribe el número para ejecutar la acción");
            System.out.println("1: Atacar");
            System.out.println("2: Imprimir Stats");
            option = depurarInput(userInput, 2, 1);
            System.out.println();
            switch (option) {
              case 1:
                imprimirMovimientos(pikachu.pkmMoves);
                moveOption = depurarInput(userInput, pikachu.pkmMoves.size(), 1);
                Move ataque = pikachu.getMove(moveOption);
                if (pikachu.mp >= ataque.mp) {
                  critStrike = (int) probabilidad(pikachu.golpeCrit, 1);
                  tempLife = charmander.vida;
                  charmander.vida = pikachu.ataque(charmander.defensaF, charmander.vida, critStrike, ataque.danho);
                  imprimirDanho(tempLife - charmander.vida, pikachu.nombre, charmander.nombre, ataque.nombre,
                      critStrike);
                  pikachu.mp = pikachu.mp - ataque.mp;
                  playerChoice = false;
                } else if (pikachu.mp == 0) {
                  // Caso de Struggle
                  ataque = pikachu.getMove(0);
                  tempLife = charmander.vida;
                  charmander.vida = pikachu.ataque(charmander.defensaF, charmander.vida, critStrike, ataque.danho);
                  imprimirDanho(tempLife - charmander.vida, pikachu.nombre, charmander.nombre, ataque.nombre,
                      critStrike);
                  pikachu.mp = pikachu.mp - ataque.mp;
                  pikachu.vida = pikachu.vida - 10; // Al usar struggle perdemos vida
                  playerChoice = false;
                } else {
                  System.out.println("No tienes suficiente mp para ese atque");
                  System.out.println();
                }
                break;
              case 2:
                imprimirEstado(pikachu.vida, charmander.vida, pikachu.mp);
                break;
              default:
                System.out.println("Se ha roto el sistema de decision del jugador");
                playerChoice = false;
            }
          }
        }
        if (i % 2 == 0) {
          // Combate sección máquina
          Move ataque = charmander.getMove(1);
          critStrike = (int) probabilidad(charmander.golpeCrit, 1);
          tempLife = pikachu.vida;
          pikachu.vida = charmander.ataque(pikachu.defensaF, pikachu.vida, critStrike, ataque.danho);
          imprimirDanho(tempLife - pikachu.vida, charmander.nombre, pikachu.nombre, ataque.nombre, critStrike);
        }
        i++;
      }
    }
  }
}
