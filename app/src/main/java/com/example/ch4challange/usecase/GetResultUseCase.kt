package com.example.ch4challange.usecase

import com.example.ch4challange.enum.PlayerChar
import com.example.ch4challange.ui.game.ResultPlayer

class GetResultUseCase : ResultPlayer {
    companion object{
        const val DRAW = 0
        const val PLAYER_ONE_WINNER = 1
        const val PLAYER_TWO_WINNER = 2
    }
    override fun onResultPlayer(playerOne: Int, playerTwo: Int): Int {
        return if (playerOne == playerTwo) {
            DRAW
        } else if (playerOne == PlayerChar.ROCK.ordinal) {
            if (playerTwo == PlayerChar.SCISSOR.ordinal) {
                PLAYER_ONE_WINNER
            } else {
                PLAYER_TWO_WINNER
            }
        } else if (playerOne == PlayerChar.PAPER.ordinal) {
            if (playerTwo == PlayerChar.ROCK.ordinal) {
                PLAYER_ONE_WINNER
            } else {
                PLAYER_TWO_WINNER
            }
        } else {
            if (playerTwo == PlayerChar.PAPER.ordinal) {
                PLAYER_ONE_WINNER
            } else {
                PLAYER_TWO_WINNER
            }
        }
    }
}