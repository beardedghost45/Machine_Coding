package org.example.model;

import java.util.*;

public class Game {
    private List<Ladder> ladders;
    private List<Snake> snakes;
    private Queue<Player> turns;
    private int boardSize;
    private int diceFaces;

    public Game(int noOfPlayers, int boardSize, int noOfSnakes, int noOfLadders, int diceFaces) {
        this.diceFaces = diceFaces;
        this.ladders = new ArrayList<>(noOfLadders);
        this.snakes = new ArrayList<>(noOfSnakes);
        this.turns = new ArrayDeque<>();
        this.boardSize = boardSize;
        initializePlayers(noOfPlayers);
        initializeSnake(noOfSnakes);
        initializeLadder(noOfLadders);
        startGame();
    }

    private void initializeSnake(int noOfSnake) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 1; i <= noOfSnake; i++) {
            System.out.println("Enter start point of Snake");
            int head = scanner.nextInt();
            System.out.println("Enter end point of Snake");
            int tail = scanner.nextInt();

            Snake snake = new Snake(head, tail);
            snakes.add(snake);
        }
    }

    private void initializeLadder(int noOfLadder) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 1; i <= noOfLadder; i++) {
            System.out.println("Enter start point of Ladder");
            int start = scanner.nextInt();
            System.out.println("Enter end point of Ladder");
            int end = scanner.nextInt();

            Ladder ladder = new Ladder(start, end);
            ladders.add(ladder);
        }
    }

    private void initializePlayers(int noOfPlayers) {
        for (int i = 1; i <= noOfPlayers; i++) {
            Player player = new Player("Player " + i);
            turns.offer(player);
        }
    }

    private int rollDice() {
        Random random = new Random();
        return random.nextInt(diceFaces) + 1;
    }

    private int movePlayer(Player player, int rolledValue) {
        int currentPosition = player.getPosition();
        int reachedPosition = currentPosition + rolledValue;

        while (reachedPosition <= boardSize) {
            // Check if the player lands on a ladder or snake
            int newPosition = isLadderThere(reachedPosition);
            newPosition = isSnakeThere(newPosition);

            // If the player's new position goes beyond the board size after the snake/ladder movement,
            // don't move them further.
            if (newPosition == reachedPosition) {
                break;
            }

            reachedPosition = newPosition;
        }

        player.setPosition(reachedPosition);
        return reachedPosition;
    }

    private int isLadderThere(int position) {
        for (Ladder ladder : ladders) {
            if (ladder.getStart() == position) {
                System.out.println("Climbed a ladder!");
                return ladder.getEnd();
            }
        }
        return position;
    }

    private int isSnakeThere(int position) {
        for (Snake snake : snakes) {
            if (snake.getHead() == position) {
                System.out.println("Oops, bitten by a snake!");
                return snake.getTail();
            }
        }
        return position;
    }

    private void startGame() {
        while (turns.size() > 1) {
            Player currentPlayer = turns.poll();
            int rolledValue = rollDice();
            System.out.println(currentPlayer.getName() + " rolled a " + rolledValue);
            int newPosition = movePlayer(currentPlayer, rolledValue);

            if (newPosition == boardSize) {
                System.out.println(currentPlayer.getName() + " won the match!");
                return;
            }

            turns.offer(currentPlayer);
        }


        Player winner = turns.poll();
        System.out.println(winner.getName() + " won the match!");
    }
}