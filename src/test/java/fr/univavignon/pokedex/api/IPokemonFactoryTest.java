package fr.univavignon.pokedex.api;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IPokemonFactoryTest {

    private IPokemonFactory pokemonFactory;

    @BeforeEach
    void setUp() {
        pokemonFactory = Mockito.mock(IPokemonFactory.class);
        when(pokemonFactory.createPokemon(0, 613, 64, 4000, 4)).thenReturn(
                new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56.0)
        );
        when(pokemonFactory.createPokemon(133, 2729, 202, 5000, 4)).thenReturn(
                new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100.0)
        );
    }

    @Test
    void testCreateBulbizarrePokemon() {
        Pokemon pokemon = pokemonFactory.createPokemon(0, 613, 64, 4000, 4);
        assertEquals(0, pokemon.getIndex());
        assertEquals("Bulbizarre", pokemon.getName());
        assertEquals(613, pokemon.getCp());
        assertEquals(64, pokemon.getHp());
        assertEquals(4000, pokemon.getDust());
        assertEquals(4, pokemon.getCandy());
        assertEquals(126, pokemon.getAttack());
        assertEquals(126, pokemon.getDefense());
        assertEquals(90, pokemon.getStamina());
        assertEquals(56.0, pokemon.getIv(), 0.01);
        verify(pokemonFactory).createPokemon(0, 613, 64, 4000, 4);
    }

    @Test
    void testCreateAqualiPokemon() {
        Pokemon pokemon = pokemonFactory.createPokemon(133, 2729, 202, 5000, 4);
        assertEquals(133, pokemon.getIndex());
        assertEquals("Aquali", pokemon.getName());
        assertEquals(2729, pokemon.getCp());
        assertEquals(202, pokemon.getHp());
        assertEquals(5000, pokemon.getDust());
        assertEquals(4, pokemon.getCandy());
        assertEquals(186, pokemon.getAttack());
        assertEquals(168, pokemon.getDefense());
        assertEquals(260, pokemon.getStamina());
        assertEquals(100.0, pokemon.getIv(), 0.01);
        verify(pokemonFactory).createPokemon(133, 2729, 202, 5000, 4);
    }

}