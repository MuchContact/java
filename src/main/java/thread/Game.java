package thread;
import java.util.ArrayList;
import java.util.List;

public class Game implements IGame {
    private List<IPlayer> players;
    private boolean started;

    public static void main(String[] args) {
        IGame game = new Game();
        for (int i = 1; i <= 20; i++) {
            game.addPlayer(new Player(String.format("players-%d", i)));
        }
        game.ready();
        game.go();
    }

    private synchronized void prepare(Player player) throws InterruptedException {
        synchronized (this) {
            while (!started) {
                wait();
            }
            System.out.println(String.format("%s start to run", player.name));
        }
    }

    @Override
    public synchronized void go() {
        started = true;
        notifyAll();
    }

    @Override
    public boolean started() {
        return started;
    }

    @Override
    public void ready() {
        for (IPlayer player : players) {
            new Thread((Player)player).start();
        }
    }

    @Override
    public void addPlayer(IPlayer player) {
        if (this.players == null) {
            this.players = new ArrayList<>();
        }

        player.joinGame(this);
        this.players.add(player);
    }

    private static class Player implements Runnable, IPlayer {
        private final String name;
        private IGame currentGame;

        Player(String name) {
            this.name = name;
        }

        private void waitToStart() throws InterruptedException {
            synchronized (currentGame) {
                if (!currentGame.started()) {
                        currentGame.wait();
                }
                System.out.println(String.format("%s start to run", this.name));

            }
        }

        @Override
        public void run() {
            try {
                waitToStart();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void joinGame(IGame game) {
            this.currentGame = game;
        }
    }

}
