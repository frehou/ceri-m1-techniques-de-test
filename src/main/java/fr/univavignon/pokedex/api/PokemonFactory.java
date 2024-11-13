package fr.univavignon.pokedex.api;

public class PokemonFactory implements IPokemonFactory {

    private final IPokemonMetadataProvider metadataProvider;

    public PokemonFactory(IPokemonMetadataProvider metadataProvider) {
        this.metadataProvider = metadataProvider;
    }

    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        try {
            PokemonMetadata metadata = metadataProvider.getPokemonMetadata(index);
            double iv = (cp + hp) / 2.0;
            return new Pokemon(metadata.getIndex(), metadata.getName(),
                    metadata.getAttack(), metadata.getDefense(),
                    metadata.getStamina(), cp, hp, dust, candy, iv);
        } catch (PokedexException e) {
            throw new RuntimeException("Failed " + e.getMessage(), e);
        }
    }
}

