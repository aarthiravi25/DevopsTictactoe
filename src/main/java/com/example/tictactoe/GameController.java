package com.example.tictactoe;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
public class GameController {
    
    // In-memory game instance
    private Game game = new Game();

    @GetMapping("/state")
    public Game getGameState() {
        return game;
    }

    @PostMapping("/moves")
    public Game makeMove(@RequestParam int index) {
        game.makeMove(index);
        return game;
    }

    @PostMapping("/reset")
    public Game resetGame() {
        game.reset();
        return game;
    }
}