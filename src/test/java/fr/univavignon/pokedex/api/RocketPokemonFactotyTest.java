package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RocketPokemonFactotyTest {
    private RocketPokemonFactory pokemonFactory;

    @BeforeEach
    void setUp() throws PokedexException {
        pokemonFactory = new RocketPokemonFactory();
    }

    @Test
    void testCreatePokemonValid() {
        Pokemon pokemon = pokemonFactory.createPokemon(1, 800, 220, 8000, 10);

        assertEquals(1, pokemon.getIndex());
        assertEquals("Bulbasaur", pokemon.getName());
        assertEquals(800, pokemon.getCp());
        assertEquals(220, pokemon.getHp());
        assertEquals(8000, pokemon.getDust());
        assertEquals(10, pokemon.getCandy());
        assertNotNull(pokemon.getAttack());
        assertNotNull(pokemon.getDefense());
        assertNotNull(pokemon.getStamina());
    }
}




