import java.util.Scanner;
import java.util.Random;
import java.io.*;
public class Main {
    public static int probabilidad(int maxRand, int minRand){
        //final String RESET = "\u001B[0m";  DEBUG
        //final String RED = "\u001B[31m";  DEBUG
        Random rand = new Random();
        int randNum;
        randNum = rand.nextInt(maxRand - minRand +1) +1;
        //System.out.println("Número aleatorio es: " + randNum );   DEBUG
        return randNum;
    }
    public static boolean vidasCombate(double hpPkm1, double hpPkmn2, String pkm1, String pkm2){
        boolean estado = false;
        if (hpPkm1 <= 0 || hpPkmn2 <=0){
            if (hpPkm1 <= 0){
                System.out.println(pkm1 + " ha sido derrotado, el ganador es " + pkm2);
                estado = true;
            }
            if (hpPkmn2 <= 0){
                System.out.println(pkm2 + " ha sido derrotado, el ganador es " + pkm1);
                estado = true;
            }
            return estado;
        }

        return estado;
    }
    public static int depurarInput(Scanner gameInput, int maxValue, int minValue){
        int numInput = -1; //Inicializamos la variable a -1 para que siempre entre en el loop
        numInput = gameInput.nextInt();
        while(!(minValue <= numInput && numInput <= maxValue)){
            System.out.println("Por favor escribe un valor válido:");
            numInput = gameInput.nextInt();
        }
        return(numInput);
    }
    public static void main (String[] args){
        final String RESET = "\u001B[0m";
        final String RED = "\u001B[31m";
        Scanner userInput = new Scanner(System.in);
        int playerChoice = 0;
        boolean finCombate = false;
        int critStrike = 0;
        //Iniciamos el primer pokemon, Pikachu
        Pokemon pikachu = new Pokemon();
        pikachu.nombre = "Pikachu";
        pikachu.vida = 120;
        pikachu.mp = 50;
        pikachu.danhoAtk1 = 10;
        pikachu.defensaF = 5;
        pikachu.nmAtk2 = "Impactrueno";
        pikachu.danhoAtk2 = 50;
        pikachu.mpAtk2 = 15;
        pikachu.golpeCrit = 20;
        pikachu.pkmAttacks[0]= pikachu.danhoAtk1;
        pikachu.pkmAttacks[1]= pikachu.danhoAtk2;
        //Iniciamos el segundo pokemon, Charmander
        Pokemon charmander= new Pokemon();
        charmander.nombre = "Charmander";
        charmander.vida = 120;
        charmander.mp = 50;
        charmander.danhoAtk1 = 10;
        charmander.defensaF = 5;
        charmander.nmAtk2 = "Mordisco";
        charmander.danhoAtk2 = 50;
        charmander.mpAtk2 = 15;
        charmander.golpeCrit = 20;
        charmander.pkmAttacks[0] = charmander.danhoAtk1;
        charmander.pkmAttacks[1] = charmander.danhoAtk2;

        //Combate
        int i = 1;
        int ronda = 1;
        while(!finCombate){
            System.out.println("Tu Pokemon:");
            System.out.println("Vida: "+pikachu.vida);
            System.out.println("Mp: " + pikachu.mp);
            System.out.println("Pokemon enemigo:");
            System.out.println("Vida: "+charmander.vida);
            System.out.println("RONDA: "+ ronda);
            if (ronda%2 == 0){
                i = 2;
                System.out.println("Empieza la máquina");
            }else if (ronda%2 ==1){
                i = 1;
                System.out.println("Empiezas tú");
            }
            ronda++;
            for(int i1=0; i1<2; i1++){
                if(i%2 == 1){
                    //Combate sección jugador
                    System.out.println("Combate, escribe el número para ejecutar la acción");
                    System.out.println("1: Atacar");
                    playerChoice = depurarInput(userInput, 1,1);
                    if(playerChoice == 1) {
                        critStrike = (int) probabilidad(pikachu.golpeCrit,1 );
                        if (!finCombate && critStrike == pikachu.golpeCrit) {
                            System.out.println(RED +"GOLPE CRÍTICO"+RESET);
                            charmander.vida =pikachu.ataque(charmander.defensaF, charmander.vida,true, 1);
                            System.out.println(pikachu.nombre + " le hace " + (pikachu.danhoAtk2*1.5 - charmander.defensaF) + " daños a " + charmander.nombre);
                            finCombate = vidasCombate(pikachu.vida, charmander.vida, pikachu.nombre, charmander.nombre);
                        } else if (!finCombate && critStrike == 1) {
                            System.out.println("HA FALLADO");
                        } else if (!finCombate) {
                            charmander.vida = pikachu.ataque(charmander.defensaF, charmander.vida,false, 1);
                            System.out.println(pikachu.nombre + " le hace " + (pikachu.danhoAtk2 - charmander.defensaF) + " daños a " + charmander.nombre);
                            finCombate = vidasCombate(pikachu.vida, charmander.vida, pikachu.nombre, charmander.nombre);
                        }
                    }
                }
                if (i%2 ==0){
                    //Combate sección máquina
                    critStrike = (int) probabilidad(charmander.golpeCrit, 1);
                    if (!finCombate && critStrike == charmander.golpeCrit){
                        System.out.println(RED +"CRÍTICO"+RESET);
                        pikachu.vida = charmander.ataque(pikachu.defensaF,pikachu.vida,true,1);
                        System.out.println(charmander.nombre + " le hace " + (charmander.danhoAtk2*1.5 - pikachu.defensaF) + " daños a "+ pikachu.nombre);
                        finCombate = vidasCombate(pikachu.vida,charmander.vida, pikachu.nombre, charmander.nombre);
                    }else if(!finCombate && critStrike == 0){
                        System.out.println("El enemigo ha fallado");
                    }else if (!finCombate){
                        pikachu.vida = charmander.ataque(pikachu.defensaF,pikachu.vida,false,1);
                        System.out.println(charmander.nombre + " le hace " + (charmander.danhoAtk2 - pikachu.defensaF) + " daños a "+ pikachu.nombre);
                        finCombate = vidasCombate(pikachu.vida,charmander.vida, pikachu.nombre, charmander.nombre);
                    }
                }
                i++;
            }
        }
    }
}
