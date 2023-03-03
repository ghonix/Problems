package cruise
import java.io.*;
import java.rmi.Remote
import java.util.*;
import kotlin.collections.ArrayList

// To execute Kotlin code, please define a top level function named main

/*
Design a simulation for a card game. This game is playable by 2 or more players and consists of a series of rounds until a single player is determined to be the winner. Each round consists of four phases performed in order: shuffle, deal, exchange, and score.

1. shuffle: randomize the order of a deck of 52 playing cards
2. deal: from the top of the deck, give each player 5 cards
3. exchange: each player may discard as many cards (face down) as they would like. After they have discarded their cards, draw cards from the top of the deck until the player has 5 cards in their hand.
4. score: determine which player has the highest numerical value of cards in their hand (face cards are worth 10 points each). If exactly one player has the highest combined value, then that player is declared the winner of the round.

The first player to win 10 rounds is declared the winner of the game.

Deck of cards
- 13 cards/suit: A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K
- 4 suits: HEARTS, DIAMONDS, CLUBS, SPADES
*/

enum class CardType {
    HEARTS,
    DIAMONDS,
    CLUBS,
    SPADES
}

enum class CardValue(val value: Int) {
    A(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    J(10),
    Q(10),
    K(10)
}

data class Card(val type: CardType, val value: CardValue)

class Deck {
    private val cards: ArrayList<Card>
    get() {
        return cards
    }
    private var top = 0
    init {
        val cards = ArrayList<Card>()
        for (type in CardType.values()) {
            for (cardValue in CardValue.values()) {
                cards.add(Card(type, cardValue))
            }
        }
        top = cards.size - 1
    }


    fun shuffle() {
        // start from end
        // randomize and swap with last element
        // move pointer and redo
    }

    fun pop(): Card {
        val card = cards[top]
        top --
        return card
    }

    fun reset() {
        var top = cards.size - 1
    }


}

interface ExchangeStrategy {
    fun apply(cards: ArrayList<Card>): ArrayList<Card>
}


class Player(val exchangeStrategy: ExchangeStrategy) {
    val cards = ArrayList<Card>()
    var points = 0

    private fun removeCards(cardsToRemove: ArrayList<Card>) {
        for (card in cardsToRemove) {
            cards.remove(card)
        }
    }

    fun resetCards() {
        cards.clear()
    }

    fun applyExchangeStrategy(): ArrayList<Card> {
        val cardsToExchange = exchangeStrategy.apply(cards)
        removeCards(cardsToExchange)
        return cardsToExchange
    }

    fun score(): Int {
        var score = 0
        for (card in cards) {
            score += card.value.value
        }
        return score
    }
}

class Game(numPlayers: Int, val cardsPerPlayer: Int = 5) {
    private val players: ArrayList<Player>?
    private val cardDeck = Deck()

    init {
        players = ArrayList(numPlayers)
    }

    fun start() {
        // set time limit to rest, reconvene
        while(noWinner(players)) {
            shuffle()

            deal() //

            exchange()

            val winner = score()
            resetCards()
        }
    }

    private fun noWinner(players: java.util.ArrayList<Player>?): Boolean {
        return false
    }

    fun resetCards() {
        cardDeck.reset()
        if (players != null) {
            for (player in players) {
                player.resetCards()
            }
        }
    }

    fun shuffle() {
        cardDeck.shuffle()
    }

    fun deal() {
        if (players != null) {
            for (player in players) {
                for (i in 1..cardsPerPlayer) {
                    player.cards.add(cardDeck.pop())
                }
            }
        }
    }


    fun exchange() {
        if (players != null) {
            for (player in players) {
                val cardsToExchange = player.applyExchangeStrategy()
                for (i in cardsToExchange.indices) {
                    player.cards.add(cardDeck.pop())
                }
            }
        }
    }


    fun score(): Player? {
        // or priority queue and get two top
        var maxScore = 0
        var maxPlayer: Player? = null
        if (players != null) {
            for (player in players) {
                val currentScore = player.score()
                if (currentScore > maxScore) {
                    maxPlayer = player
                    maxScore = currentScore
                } else if (currentScore == maxScore){
                    if (maxPlayer != null) {
                        maxPlayer = null // Draw
                    }
                }
            }
        }
        return maxPlayer
    }
}
