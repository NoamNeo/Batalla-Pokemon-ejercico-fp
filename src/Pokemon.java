import java.util.Map;
import java.util.HashMap;

public class Pokemon {
  String nombre, nmAtk1;
  double vida, mp, danhoAtk1, defensaF, defensaS, mpAtk1;
  int golpeCrit;
  Map<String, Move> pkmMoves = new HashMap<>();

  public double ataque(double defensaEnemigo, double vidaEnemigo, boolean crit, double danho) {
    if (crit) {
      vidaEnemigo = vidaEnemigo - (danho - defensaEnemigo) * 1.5;
    } else {
      vidaEnemigo = vidaEnemigo - danho + defensaEnemigo;
    }
    return vidaEnemigo;
  }
}
