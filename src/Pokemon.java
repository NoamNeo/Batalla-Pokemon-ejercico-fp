import java.util.Map;
import java.util.HashMap;

public class Pokemon {
  String nombre;
  double vida, mp, defensaF, defensaS;
  int golpeCrit;
  Map<Integer, Move> pkmMoves = new HashMap<>();

  public void addMove(int key, String nombre, double danho, int mp) {
    Move move = new Move(nombre, danho, mp);
    pkmMoves.put(key, move);
  }

  public Move getMove(int key) {
    return pkmMoves.get(key);
  }

  public double ataque(double defensaEnemigo, double vidaEnemigo, int critP, double danho) {
    int intFallo = 1;
    double critDmg = 1;
    if (critP == this.golpeCrit) {
      critDmg = 1.5;
    } else if (critP == 1) {
      intFallo = 0;
    }
    vidaEnemigo = vidaEnemigo - ((danho - defensaEnemigo) * intFallo) * critDmg;
    return vidaEnemigo;
  }
}
