public class Pokemon {
    String nombre, nmAtk1, nmAtk2;
    double vida, mp, danhoAtk1, danhoAtk2, defensaF, defensaS, mpAtk1, mpAtk2;
    int golpeCrit;
    double[] pkmAttacks = {danhoAtk1, danhoAtk2};
    public double ataque(double defensaEnemigo, double vidaEnemigo, boolean crit, int pkmAttack){
        if(crit){
            vidaEnemigo = vidaEnemigo - (pkmAttacks[pkmAttack]-defensaEnemigo)*1.5;
        }else {
            vidaEnemigo = vidaEnemigo - pkmAttacks[pkmAttack]-defensaEnemigo;
        }
        return vidaEnemigo;
    }
}
