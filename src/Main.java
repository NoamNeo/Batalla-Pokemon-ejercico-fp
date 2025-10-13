public class Main() {
    public static void main (String[] args){
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
        while(i!=0){
            System.out.println("Ronda: "+ i);
            System.out.println("Tu Pokemon:");
            System.out.println("Vida: "+vida1);
            if (vida1 == 0 || vida2 ==0){
                if (vida1 == 0){
                    System.out.println(nombre1 + " ha sido derrotado, el ganador es " + nombre2);
                }
                if (vida2 == 0){
                    System.out.println(nombre2 + " ha sido derrotado, el ganador es " + nombre1);
                }
                i = 0;
            }
        }
    }
}
