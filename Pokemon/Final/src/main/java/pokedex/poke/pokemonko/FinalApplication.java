package pokedex.poke.pokemonko;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pokedex.poke.pokemonko.model.Pokemon;
import pokedex.poke.pokemonko.repository.PokemonRepository;

@SpringBootApplication
public class FinalApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinalApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(PokemonRepository repository) {
        return (args) -> {
            // Sample data
            repository.save(new Pokemon(null, "Bulbasaur", "Electric", "0.7 m", "6.9 kg", "A strange seed was planted on its back at birth. The plant sprouts and grows with this Pokémon."));
            repository.save(new Pokemon(null, "Charmander", "Fire", "0.6 m", "8.5 kg", "Obviously prefers hot places. When it rains, steam is said to spout from the tip of its tail."));
            repository.save(new Pokemon(null, "Squirtle", "Water", "0.5 m", "9.0 kg", "After birth, its back swells and hardens into a shell. Powerfully sprays foam from its mouth."));
        };
    }

}