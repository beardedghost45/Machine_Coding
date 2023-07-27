package org.example;

//Board can be of any size Each player puts their counter on the board starting position.
//        Take it in turns to roll the dice.
//        Move your counter forward the number of spaces shown on the dice.
//        If your counter lands at the bottom of a ladder, you can move up to the top of the ladder.
//        If your counter lands on the head of a snake, you must slide down to the bottom of the snake.
//        Each player will get a fair chance to roll the dice.
//        Game is continued till there are two players left.
//        When user roles dice and not reaching the destination i.e when the user is in poisiton 99 and rolling of dice yields any number more than one the user remains in the same poistion.

import org.example.model.Game;

import java.util.Scanner;

public class SnakeLeaderApplication {

        public static void main(String[] args) {
            int noOfPlayers = 2;
            int boardSize = 100;
            int noOfSnakes = 3;
            int noOfLadders = 3;
            int diceFaces = 6;

            Game game = new Game(noOfPlayers, boardSize, noOfSnakes, noOfLadders, diceFaces);
        }

}