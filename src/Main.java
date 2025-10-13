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
        String nombre1 = "Pikachu";
        double vida1 = 120;
        double mp1 = 50;
        double danhoGolpe1 = 10;
        double defensa1 = 5;
        String golpeEspecial = "Impactrueno";
        double danhoEspecial = 50;
        double mpGolpeEspecial = 15;
        int golpeCritPkm1 = 20;
        //Iniciamos el segundo pokemon, Charmander
        String nombre2 = "Charmander";
        double vida2 = 120;
        double mp2 = 50;
        double danhoGolpe2 = 10;
        double defensa2 = 5;
        String golpeFisico = "Mordisco";
        double danhoFisico = 40;
        double mpGolpeFisico = 15;
        int golpeCritPkm2 = 20;

        //Combate
        int i = 1;
        int ronda = 1;
        while(!finCombate){
            System.out.println("Tu Pokemon:");
            System.out.println("Vida: "+vida1);
            System.out.println("Mp: " + mp1);
            System.out.println("Pokemon enemigo:");
            System.out.println("Vida: "+vida2);
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
                    System.out.println("0: Atacar");
                    playerChoice = depurarInput(userInput, 0,0);
                    if(playerChoice == 0) {
                        critStrike = (int) probabilidad(golpeCritPkm1,1 );
                        if (!finCombate && critStrike == golpeCritPkm1) {
                            System.out.println(RED +"GOLPE CRÍTICO"+RESET);
                            vida2 = vida2 - (danhoEspecial * 1.5) + defensa2;
                            System.out.println(nombre1 + " le hace " + (danhoEspecial*1.5 - defensa2) + " daños a " + nombre2);
                            finCombate = vidasCombate(vida1, vida2, nombre1, nombre2);
                        } else if (!finCombate && critStrike == 1) {
                            System.out.println("HA FALLADO");
                        } else if (!finCombate) {
                            vida2 = vida2 - danhoEspecial + defensa2;
                            System.out.println(nombre1 + " le hace " + (danhoEspecial - defensa2) + " daños a " + nombre2);
                            finCombate = vidasCombate(vida1, vida2, nombre1, nombre2);
                        }
                    }
                }
                if (i%2 ==0){
                    //Combate sección máquina
                    critStrike = (int) probabilidad(golpeCritPkm2, 1);
                    if (!finCombate && critStrike == golpeCritPkm2){
                        System.out.println(RED +"CRÍTICO"+RESET);
                        vida1 = vida1 - (danhoFisico * 1.5) + defensa1;
                        System.out.println(nombre2 + " le hace " + (danhoFisico*1.5 - defensa1) + " daños a "+ nombre1);
                        finCombate = vidasCombate(vida1,vida2, nombre1, nombre2);
                    }else if(!finCombate && critStrike == 0){
                        System.out.println("El enemigo ha fallado");
                    }else if (!finCombate){
                        vida1 = vida1 - danhoFisico + defensa1;
                        System.out.println(nombre2 + " le hace " + (danhoFisico - defensa1) + " daños a "+ nombre1);
                        finCombate = vidasCombate(vida1,vida2, nombre1, nombre2);
                    }
                }
                i++;
            }
        }
    }
}
