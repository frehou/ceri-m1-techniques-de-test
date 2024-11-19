package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PokemonMetadataProviderTest {

    private PokemonMetadataProvider metadataProvider;

    @BeforeEach
    void setUp() {
        metadataProvider = new PokemonMetadataProvider();
    }

    @Test
    void testGetMetadataForExistingPokemon() throws PokedexException {
        PokemonMetadata metadata = metadataProvider.getPokemonMetadata(1);

        assertNotNull(metadata, "Metadata should not be null");
        assertEquals(1, metadata.getIndex(), "Index should match");
        assertEquals("Bul", metadata.getName(), "Name should match");
        assertEquals(200, metadata.getAttack(), "Attack should match");
        assertEquals(150, metadata.getDefense(), "Defense should match");
        assertEquals(95, metadata.getStamina(), "Stamina should match");
        metadata = metadataProvider.getPokemonMetadata(2);

        assertNotNull(metadata, "Metadata should not be null");
        assertEquals(2, metadata.getIndex(), "Index should match");
        assertEquals("Ivy", metadata.getName(), "Name should match");
        assertEquals(122, metadata.getAttack(), "Attack should match");
        assertEquals(83, metadata.getDefense(), "Defense should match");
        assertEquals(40, metadata.getStamina(), "Stamina should match");
    }

    @Test
    void testGetMetadataForNonExistentPokemon() {
        int invalidIndex = 999;

        PokedexException exception = assertThrows(PokedexException.class, () -> {
            metadataProvider.getPokemonMetadata(invalidIndex);
        });

        assertEquals("Pokémon with index " + invalidIndex + " not found.", exception.getMessage(),
                "Exception message should match");
    }
}
