package fr.univavignon.pokedex.api;



import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;

public class Pokedex implements IPokedex {

    private final List<Pokemon> pokemons = new ArrayList<>();
    private final IPokemonMetadataProvider metadataProvider;
    private final IPokemonFactory pokemonFactory;

    public Pokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
        this.metadataProvider = metadataProvider;
        this.pokemonFactory = pokemonFactory;
    }

    @Override
    public int size() {
        return pokemons.size();
    }

    @Override
    public int addPokemon(Pokemon pokemon) {
        pokemons.add(pokemon);
        return pokemon.getIndex();
    }

    @Override
    public Pokemon getPokemon(int id) throws PokedexException {
        if (id < 0 || id >= pokemons.size()) {
            throw new PokedexException("Pokémon with index " + id + " not found.");
        }
        return pokemons.get(id);
    }

    @Override
    public List<Pokemon> getPokemons() {
        return Collections.unmodifiableList(pokemons);
    }

    @Override
    public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
        List<Pokemon> sortedList = new ArrayList<>(pokemons);
        sortedList.sort(order);
        return Collections.unmodifiableList(sortedList);
    }

    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy)  {
        PokemonMetadata metadata;
        try {
            metadata = metadataProvider.getPokemonMetadata(index);
        } catch (PokedexException e) {
            throw new RuntimeException(e);
        }
        return pokemonFactory.createPokemon(
                index, cp, hp, dust, candy
        );
    }

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        PokemonMetadata metadata = metadataProvider.getPokemonMetadata(index);
        if (metadata == null) {
            throw new PokedexException("No metadata found for Pokémon with index " + index);
        }
        return metadata;
    }

}
