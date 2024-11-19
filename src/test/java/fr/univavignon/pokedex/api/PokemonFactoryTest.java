package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class PokemonFactoryTest {

    private PokemonFactory pokemonFactory;
    private IPokemonMetadataProvider metadataProvider;

    @BeforeEach
    void setUp() throws PokedexException {
        metadataProvider = Mockito.mock(IPokemonMetadataProvider.class);
        when(metadataProvider.getPokemonMetadata(133))
                .thenReturn(new PokemonMetadata(133, "Aquali", 186, 168, 260));
        pokemonFactory = new PokemonFactory(metadataProvider);
    }

    @Test
    void testCreateAqualiPokemon() {
        Pokemon pokemon = pokemonFactory.createPokemon(133, 2729, 202, 5000, 4);
        assertNotNull(pokemon, "Pokemon should not be null");
        assertEquals(133, pokemon.getIndex(), "Index should match");
        assertEquals("Aquali", pokemon.getName(), "Name should match");
        assertEquals(186, pokemon.getAttack(), "Attack stat should match");
        assertEquals(168, pokemon.getDefense(), "Defense stat should match");
        assertEquals(260, pokemon.getStamina(), "Stamina stat should match");
        assertEquals(2729, pokemon.getCp(), "CP should match");
        assertEquals(202, pokemon.getHp(), "HP should match");
        assertEquals(5000, pokemon.getDust(), "Dust cost should match");
        assertEquals(4, pokemon.getCandy(), "Candy count should match");
        assertEquals(1465.5, pokemon.getIv(), 0.01, "IV should match");
    }
}
