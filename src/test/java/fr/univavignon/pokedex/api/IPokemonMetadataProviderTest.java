package fr.univavignon.pokedex.api;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class IPokemonMetadataProviderTest {

    private IPokemonMetadataProvider metadataProvider;

    @BeforeEach
    void setUp() throws PokedexException {
        metadataProvider = Mockito.mock(IPokemonMetadataProvider.class);

        when(metadataProvider.getPokemonMetadata(0)).thenReturn(new PokemonMetadata(0, "Bulbizarre", 126, 126, 90));
        when(metadataProvider.getPokemonMetadata(133)).thenReturn(new PokemonMetadata(133, "Aquali", 186, 168, 260));
    }

    @Test
    void testGetBulbizarreMetadata() throws PokedexException {
        PokemonMetadata metadata = metadataProvider.getPokemonMetadata(0);
        assertEquals(0, metadata.getIndex());
        assertEquals("Bulbizarre", metadata.getName());
        assertEquals(126, metadata.getAttack());
        assertEquals(126, metadata.getDefense());
        assertEquals(90, metadata.getStamina());
        verify(metadataProvider).getPokemonMetadata(0);
    }

    @Test
    void testGetAqualiMetadata() throws PokedexException {
        PokemonMetadata metadata = metadataProvider.getPokemonMetadata(133);
        assertEquals(133, metadata.getIndex());
        assertEquals("Aquali", metadata.getName());
        assertEquals(186, metadata.getAttack());
        assertEquals(168, metadata.getDefense());
        assertEquals(260, metadata.getStamina());
        verify(metadataProvider).getPokemonMetadata(133);
    }

}