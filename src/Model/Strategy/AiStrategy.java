package Model.Strategy;

import Model.ReversiGame;

import java.io.Serializable;

public interface AiStrategy extends Serializable {
    void move(ReversiGame reversiGameI);
}
