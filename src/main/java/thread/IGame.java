package thread;

interface IGame {
    void addPlayer(IPlayer player);
    void ready();
    void go();
    boolean started();
}
