package fr.univavignon.pokedex.api;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PokemonMetadataProviderTest {

    private IPokemonMetadataProvider provider;

    @BeforeEach
    void setUp() throws PokedexException {
        provider = Mockito.mock(IPokemonMetadataProvider.class);

        when(provider.getPokemonMetadata(0)).thenReturn(new PokemonMetadata(0, "Pick", 250, 120, 90));
        when(provider.getPokemonMetadata(1)).thenReturn(new PokemonMetadata(1, "Chu", 150, 100, 80));
        when(provider.getPokemonMetadata(2)).thenReturn(new PokemonMetadata(2, "Char", 200, 110, 70));
    }

    @Test
    void testGetMetadataForValidIndex() throws PokedexException {
        PokemonMetadata metadata = provider.getPokemonMetadata(1);
        assertEquals(1, metadata.getIndex());
        assertEquals("Chu", metadata.getName());
        assertEquals(150, metadata.getAttack());
        assertEquals(100, metadata.getDefense());
        assertEquals(80, metadata.getStamina());
    }

}