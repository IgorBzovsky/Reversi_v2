package Model;

public class PvAiReversiGame extends ReversiGame {
    private AiStrategy strategy;
    public PvAiReversiGame(AiStrategy strategy) {
        this.strategy = strategy;
    }
}
