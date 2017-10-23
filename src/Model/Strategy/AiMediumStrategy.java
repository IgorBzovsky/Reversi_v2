package Model.Strategy;

import Model.*;

import java.io.Serializable;
import java.util.LinkedList;

public class AiMediumStrategy implements AiStrategy, Serializable {
    private int currentMove;
    private boolean isAiPlayerWhite;
    private ReversiGame reversiGame;
    private GameBoard gameBoard;
    private CellCoord cellToMove;

    @Override
    public void move(ReversiGame reversiGame) {
        if(reversiGame == null || reversiGame.isGameOver())
            return;
        this.reversiGame = reversiGame;
        isAiPlayerWhite = !((PvAiReversiGame)reversiGame).isWhitePlayer();
        minMax(reversiGame.cloneGameBoard(), isAiPlayerWhite, isAiPlayerWhite, 0, 4);
        System.out.println(cellToMove.getRow() + ";" + cellToMove.getColumn());
        reversiGame.move(cellToMove.getRow(), cellToMove.getColumn());
    }

    private double minMax(GameBoard gameBoard, boolean player, boolean currentPlayer, int depth, int maxDepth) {
        double bestScore;
        double score;
        CellCoord bestMove = null;
        boolean noMoves = gameBoard.getAvailableMoves(currentPlayer).size() == 0;
        if(noMoves || depth == maxDepth){

            return evaluate(gameBoard, currentPlayer);
        }
        if(player == currentPlayer)
            bestScore = -1000000000;
        else bestScore = 1000000000;
        for (CellCoord move : gameBoard.getAvailableMoves(currentPlayer)) {
            GameBoard newGameBoard = new GameBoard(gameBoard);
            newGameBoard.putDisk(move.getRow(), move.getColumn(), currentPlayer);
            score = minMax(newGameBoard, player, !currentPlayer, depth+1, maxDepth);
            if(player == currentPlayer){
                if(score > bestScore){
                    bestScore = score;
                    bestMove = move;
                }
            }
            else {
                if(score < bestScore) {
                    bestScore = score;
                    bestMove = move;
                }
            }
        }
        cellToMove = bestMove;
        return bestScore;
    }

    private double evaluate(GameBoard gameBoard, boolean isWhite) {
        double aiDisks = gameBoard.getDiscs(isWhite).size();
        double playerDisks = gameBoard.getDiscs(!isWhite).size();
        double aiCornerDisks = gameBoard.getCornerDisksCount(isWhite);
        double playerCornerDisks = gameBoard.getCornerDisksCount(!isWhite);
        double aiCornerAdjacentDisks = gameBoard.getCornerAdjacentDisksCount(isWhite);
        double playerCornerAdjacentDisks = gameBoard.getCornerAdjacentDisksCount(!isWhite);
        double aiAvailableMoves = gameBoard.getAvailableMoves(isWhite).size();
        double playerAvailableMoves = gameBoard.getAvailableMoves(!isWhite).size();
        double aiFrontierDisks = gameBoard.getFrontierDisksCount(isWhite);
        double playerFrontierDisks = gameBoard.getFrontierDisksCount(!isWhite);

        /**
         * Piece difference
         */
        double pieceDifference;
        if(aiDisks > playerDisks)
            pieceDifference = 100 * (aiDisks/(aiDisks + playerDisks));
        else if(aiDisks < playerDisks)
            pieceDifference = -100 * (playerDisks/(aiDisks + playerDisks));
        else pieceDifference = 0;

        /**
         * Corner occupancy
         */
        double cornerOccupancy = 25 * aiCornerDisks - 25 * playerCornerDisks;

        /**
         * Corner closeness
         */
        double cornerCloseness = -12.5 * aiCornerAdjacentDisks + 12.5 * playerCornerAdjacentDisks;

        /**
         * Mobility
         */
        double mobility = 0;
        if(aiAvailableMoves == playerAvailableMoves || aiAvailableMoves == 0 || playerAvailableMoves == 0)
            mobility = 0;
        else if(aiAvailableMoves > playerAvailableMoves)
            pieceDifference = 100 * (aiAvailableMoves/(aiAvailableMoves + playerAvailableMoves));
        else if(aiAvailableMoves < playerAvailableMoves)
            pieceDifference = -100 * (playerAvailableMoves/(aiAvailableMoves + playerAvailableMoves));

        /**
         * Frontier
         */
        double frontierDisks;
        if(aiFrontierDisks > playerFrontierDisks)
            frontierDisks = -100 * (aiFrontierDisks / (aiFrontierDisks + playerFrontierDisks));
        else if(aiFrontierDisks < playerFrontierDisks)
            frontierDisks = 100 * (playerFrontierDisks / (aiFrontierDisks + playerFrontierDisks));
        else frontierDisks = 0;

        /**
         * Square coefficient
         */
        double squareCoefficient = diskSquareCoefficient(gameBoard, isWhite);
        double evaluation = pieceDifference + cornerOccupancy + cornerCloseness + mobility + frontierDisks + squareCoefficient;
        return evaluation;
    }

    private double diskSquareCoefficient(GameBoard gameBoard, boolean isWhite){
        CellType aiType = isWhite ? CellType.White : CellType.Black;
        CellType playerType = isWhite ? CellType.Black : CellType.White;
        double[][] values = {
                {20, -3, 11, 8, 8, 11, -3, 20},
                {-3, -7, -4, 1, 1, -4, -7, -3},
                {11, -4, 2, 2, 2, 2, -4, 11},
                {8, 1, 2, -3, -3, 2, 1, 8},
                {8, 1, 2, -3, -3, 2, 1, 8},
                {11, -4, 2, 2, 2, 2, -4, 11},
                {-3, -7, -4, 1, 1, -4, -7, -3},
                {20, -3, 11, 8, 8, 11, -3, 20}
        };
        double counter = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                CellType cellType = gameBoard.getCellType(i, j);
                if(cellType == aiType)
                    counter += values[i][j];
                else if(cellType == playerType)
                    counter -= values[i][j];
            }
        }
        return counter;
    }
}
