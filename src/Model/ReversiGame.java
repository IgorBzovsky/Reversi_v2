package Model;

public abstract class ReversiGame {
    public static PvPReversiGame createPvPGame(){
        return new PvPReversiGame();
    }
    public static PvAiReversiGame createPvAiEasyGame(){
        return new PvAiReversiGame(new AiEasyStrategy());
    }
    public static PvAiReversiGame createPvAiMediumGame(){
        return new PvAiReversiGame(new AiMediumStrategy());
    }
}
