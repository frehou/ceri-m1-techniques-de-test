package fr.univavignon.pokedex.api;



import java.util.HashMap;
import java.util.Map;

public class PokemonMetadataProvider implements IPokemonMetadataProvider {

    private final Map<Integer, PokemonMetadata> metadataMap = new HashMap<>();

    public PokemonMetadataProvider() {
        metadataMap.put(1, new PokemonMetadata(1, "Bul", 200, 150, 95));
        metadataMap.put(2, new PokemonMetadata(2, "Ivy", 122, 83, 40));
    }

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        PokemonMetadata metadata = metadataMap.get(index);
        if (metadata == null) {
            throw new PokedexException("Pokémon with index " + index + " not found.");
        }
        return metadata;
    }
}

