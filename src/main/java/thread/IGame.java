package thread;

public interface IGame {
    void addPlayer(Game.Player player);
    void ready();
    void go();
    boolean started();
}
