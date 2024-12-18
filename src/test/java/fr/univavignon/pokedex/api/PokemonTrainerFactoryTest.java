package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PokemonTrainerFactoryTest {

    private PokemonTrainerFactory trainerFactory;
    private IPokedexFactory pokedexFactory;
    private IPokedex pokedex;

    @BeforeEach
    void setUp() {
        pokedexFactory = Mockito.mock(IPokedexFactory.class);
        pokedex = Mockito.mock(IPokedex.class);

        when(pokedexFactory.createPokedex(any(PokemonMetadataProvider.class), any(PokemonFactory.class)))
                .thenReturn(pokedex);

        trainerFactory = new PokemonTrainerFactory();
    }

    @Test
    void testCreateTrainer() {
        PokemonTrainer trainer = trainerFactory.createTrainer("Ash", Team.VALOR, pokedexFactory);
        assertEquals("Ash", trainer.getName());
        assertEquals(Team.VALOR, trainer.getTeam());
        assertEquals(pokedex, trainer.getPokedex());
        verify(pokedexFactory).createPokedex(any(PokemonMetadataProvider.class), any(PokemonFactory.class));
    }
}
