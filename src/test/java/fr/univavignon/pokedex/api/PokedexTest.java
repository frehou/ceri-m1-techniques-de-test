package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PokedexTest {

    private IPokemonMetadataProvider metadataProvider;
    private IPokemonFactory pokemonFactory;
    private Pokedex pokedex;

    @BeforeEach
    void setUp() throws PokedexException {
        metadataProvider = Mockito.mock(IPokemonMetadataProvider.class);
        pokemonFactory = Mockito.mock(IPokemonFactory.class);
        pokedex = new Pokedex(metadataProvider, pokemonFactory);

        when(metadataProvider.getPokemonMetadata(0)).thenReturn(new PokemonMetadata(0, "Bulbizarre", 126, 126, 90));
        when(metadataProvider.getPokemonMetadata(133)).thenReturn(new PokemonMetadata(133, "Aquali", 186, 168, 260));

        when(pokemonFactory.createPokemon(0, 613, 64, 4000, 4))
                .thenReturn(new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56.0));
        when(pokemonFactory.createPokemon(133, 2729, 202, 5000, 4))
                .thenReturn(new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100.0));
    }

    @Test
    void testAddPokemon() {
        Pokemon bulbasaur = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56.0);
        int index = pokedex.addPokemon(bulbasaur);

        assertEquals(0, index);
        assertEquals(1, pokedex.size());
        assertEquals(bulbasaur, pokedex.getPokemons().get(0));
    }

    @Test
    void testGetPokemon() throws PokedexException {
        Pokemon bulbasaur = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56.0);
        pokedex.addPokemon(bulbasaur);

        Pokemon rPok = pokedex.getPokemon(0);
        assertEquals(bulbasaur, rPok);
    }

    @Test
    void testGetPokemonInvalidIndex() {
        assertThrows(PokedexException.class, () -> pokedex.getPokemon(1));
    }

    @Test
    void testGetPokemons() {

        Pokemon bulbasaur = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56.0);
        Pokemon aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100.0);
        pokedex.addPokemon(bulbasaur);
        pokedex.addPokemon(aquali);

        List<Pokemon> allPokemons = pokedex.getPokemons();
        assertEquals(2, allPokemons.size());
        assertEquals(bulbasaur, allPokemons.get(0));
        assertEquals(aquali, allPokemons.get(1));
    }

    @Test
    void testGetPokemonsSorted() {
        Pokemon bulbasaur = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56.0);
        Pokemon vaporeon = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100.0);

        pokedex.addPokemon(vaporeon);
        pokedex.addPokemon(bulbasaur);

        List<Pokemon> sortedPokemons = pokedex.getPokemons(PokemonComparators.NAME);
        assertEquals("Aquali", sortedPokemons.get(0).getName());
        assertEquals("Bulbizarre", sortedPokemons.get(1).getName());
    }

    @Test
    void testCreatePokemon() {
        Pokemon pokemon = pokedex.createPokemon(0, 613, 64, 4000, 4);
        assertNotNull(pokemon);
        assertEquals("Bulbizarre", pokemon.getName());
    }

    @Test
    void testGetPokemonMetadata() throws PokedexException {
        PokemonMetadata metadata = pokedex.getPokemonMetadata(0);
        assertNotNull(metadata);
        assertEquals("Bulbizarre", metadata.getName());
    }

    @Test
    void testGetPokemonMetadataInvalidIndex() throws PokedexException {
        when(metadataProvider.getPokemonMetadata(-1)).thenThrow(new PokedexException("Invalid index"));
        assertThrows(PokedexException.class, () -> pokedex.getPokemonMetadata(-1));
    }
}
