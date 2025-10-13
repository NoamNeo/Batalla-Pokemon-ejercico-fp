import java.util.Scanner;
public class Main {
    public static boolean vidasCombate(int hpPkm1, int hpPkmn2, String pkm1, String pkm2){
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
        Scanner userInput = new Scanner(System.in);
        int playerChoice = 0;
        boolean finCombate = false;
        //Iniciamos el primer pokemon, Pikachu
        String nombre1 = "Pikachu";
        int vida1 = 120;
        int mp1 = 50;
        int danhoGolpe1 = 10;
        int defensa1 = 5;
        String golpeEspecial = "Impactrueno";
        int danhoEspecial = 40;
        int mpGolpeEspecial = 15;
        //Iniciamos el segundo pokemon, Charmander
        String nombre2 = "Charmander";
        int vida2 = 120;
        int mp2 = 50;
        int danhoGolpe2 = 10;
        int defensa2 = 5;
        String golpeFisico = "Mordisco";
        int danhoFisico = 40;
        int mpGolpeFisico = 15;

        //Combate
        int i = 1;
        while(!finCombate){
            System.out.println("Ronda: "+ i);
            System.out.println("Tu Pokemon:");
            System.out.println("Vida: "+vida1);
            System.out.println("Mp: " + mp1);
            System.out.println("Pokemon enemigo:");
            System.out.println("Vida: "+vida2);
            //Combate sección jugador
            System.out.println("Combate, escribe el número para ejecutar la acción");
            System.out.println("0: Atacar");
            playerChoice = depurarInput(userInput, 0,0);
            if (playerChoice == 0 && !finCombate){
               vida2 = vida2 - danhoEspecial + defensa2;
               System.out.println(nombre1 + " le hace "+ (danhoEspecial-defensa2) + " daños a " + nombre2);
               finCombate = vidasCombate(vida1,vida2, nombre1, nombre2);
            }
            //Combate sección máquina
            if (!finCombate){
                vida1 = vida1 - danhoFisico + defensa1;
                System.out.println(nombre2 + " le hace " + (danhoFisico - defensa1) + " daños a "+ nombre1);
                finCombate = vidasCombate(vida1,vida2, nombre1, nombre2);
            }
        }
    }
}
