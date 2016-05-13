package thread;
import java.util.ArrayList;
import java.util.List;

public class Game implements IGame {
    private List<Player> players;
    private boolean started;

    public static void main(String[] args) {
        Game game = new Game();
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
        for (Player player : players) {
            new Thread(player).start();
        }
    }

    @Override
    public void addPlayer(Player player) {
        if (this.players == null) {
            this.players = new ArrayList<>();
        }

        player.joinGame(this);
        this.players.add(player);
    }

    public static class Player implements Runnable {
        private final String name;
        private Game currentGame;

        public Player(String name) {
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

        public void joinGame(Game game) {
            this.currentGame = game;
        }
    }

}
