package fr.univavignon.pokedex.api;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IPokedexTest {

    private IPokedex pokedex;
    private IPokemonFactory pokemonFactory;
    private IPokemonMetadataProvider metadataProvider;

    private List<Pokemon> pokemonList;

    @BeforeEach
    void setUp() throws PokedexException {
        pokedex = Mockito.mock(IPokedex.class);
        pokemonFactory = Mockito.mock(IPokemonFactory.class);
        metadataProvider = Mockito.mock(IPokemonMetadataProvider.class);

        pokemonList = new ArrayList<>();
        pokemonList.add(new Pokemon(0, "Bulbizarre", 600, 164, 90, 14, 64, 126, 4, 56.0));
        pokemonList.add(new Pokemon(1, "Aquali", 700, 80, 100, 25, 80, 158, 5, 70.0));

        when(metadataProvider.getPokemonMetadata(0)).thenReturn(new PokemonMetadata(0, "Bulbizarre", 600, 164, 90));
        when(metadataProvider.getPokemonMetadata(1)).thenReturn(new PokemonMetadata(1, "Aquali", 156, 158, 120));

        when(pokemonFactory.createPokemon(0, 14, 64, 126, 4)).thenReturn(pokemonList.get(0));
        when(pokemonFactory.createPokemon(1, 25, 80, 158, 5)).thenReturn(pokemonList.get(1));

        when(pokedex.size()).thenReturn(pokemonList.size());
        when(pokedex.getPokemon(0)).thenReturn(pokemonList.get(0));
        when(pokedex.getPokemon(1)).thenReturn(pokemonList.get(1));
        when(pokedex.getPokemons()).thenReturn(pokemonList);
    }

    @Test
    void testAddPokemon() {
        Pokemon newPokemon = new Pokemon(2, "Venusaur", 800, 100, 6000, 6, 198, 200, 160, 80.0);
        when(pokedex.addPokemon(newPokemon)).thenReturn(2);
        int index = pokedex.addPokemon(newPokemon);
        assertEquals(2, index);
        verify(pokedex).addPokemon(newPokemon);
    }

    @Test
    void testGetPokemon() throws PokedexException {
        Pokemon pokemon = pokedex.getPokemon(0);
        assertEquals("Bulbizarre", pokemon.getName());
        assertEquals(14, pokemon.getCp());
        assertEquals(4, pokemon.getCandy());

        Pokemon secondPokemon = pokedex.getPokemon(1);
        assertEquals("Aquali", secondPokemon.getName());
        assertEquals(25, secondPokemon.getCp());
        assertEquals(5, secondPokemon.getCandy());

        verify(pokedex).getPokemon(0);
        verify(pokedex).getPokemon(1);
    }

    @Test
    void testGetPokedexInvalidIndexExecption() throws PokedexException {
        when(pokedex.getPokemon(-1)).thenThrow(new PokedexException("Index invalid"));
        assertThrows(PokedexException.class,() ->{
            pokedex.getPokemon(-1);
        });
    }

    @Test
    void testSize() {
        assertEquals(2, pokedex.size());
        verify(pokedex).size();
    }

    @Test
    void testGetPokemons() {
        List<Pokemon> allPokemons = pokedex.getPokemons();
        assertEquals(2, allPokemons.size());
        assertEquals("Bulbizarre", allPokemons.get(0).getName());
        assertEquals("Aquali", allPokemons.get(1).getName());
        verify(pokedex).getPokemons();
    }


}