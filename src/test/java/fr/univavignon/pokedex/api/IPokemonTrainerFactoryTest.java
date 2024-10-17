package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IPokemonTrainerFactoryTest {
    private IPokemonTrainerFactory trainerFactory;
    private IPokedexFactory pokedexFactory;
    private IPokemonMetadataProvider metadataProvider;
    private IPokemonFactory pokemonFactory;
    private IPokedex pokedex;

    @BeforeEach
    void setUp() {
        trainerFactory = Mockito.mock(IPokemonTrainerFactory.class);
        pokedexFactory = Mockito.mock(IPokedexFactory.class);
        metadataProvider = Mockito.mock(IPokemonMetadataProvider.class);
        pokemonFactory = Mockito.mock(IPokemonFactory.class);
        pokedex = Mockito.mock(IPokedex.class);

        when(pokedexFactory.createPokedex(metadataProvider, pokemonFactory)).thenReturn(pokedex);

        PokemonTrainer trainer = new PokemonTrainer("Souf", Team.MYSTIC, pokedex);
        when(trainerFactory.createTrainer("Souf", Team.MYSTIC, pokedexFactory)).thenReturn(trainer);
    }

    @Test
    void testCreateTrainer() {
        PokemonTrainer trainer = trainerFactory.createTrainer("Souf", Team.MYSTIC, pokedexFactory);

        assertNotNull(trainer, "Trainer should not be null");
        assertEquals("Souf", trainer.getName(), "Trainer name should be Souf");
        assertEquals(Team.MYSTIC, trainer.getTeam(), "Trainer should belong to Team MYSTIC");
        assertNotNull(trainer.getPokedex(), "Trainer should have a Pokedex");
        verify(trainerFactory).createTrainer("Souf", Team.MYSTIC, pokedexFactory);
    }

}