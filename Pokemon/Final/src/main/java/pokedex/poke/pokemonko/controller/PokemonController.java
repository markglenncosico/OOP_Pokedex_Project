package pokedex.poke.pokemonko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import pokedex.poke.pokemonko.model.Pokemon;
import pokedex.poke.pokemonko.repository.PokemonRepository;

@RestController
@RequestMapping("/pokemons")
@CrossOrigin(origins = "*") // Added to allow your frontend dashboard to communicate with this backend
public class PokemonController {

    @Autowired
    private PokemonRepository pokemonRepository;

    // Get all Pokémon
    @GetMapping
    public List<Pokemon> getAllPokemons() {
        return pokemonRepository.findAll();
    }

    // Get a single Pokémon by ID
    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> getPokemonById(@PathVariable Long id) {
        return pokemonRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Add a new Pokémon
    @PostMapping
    public Pokemon createPokemon(@RequestBody Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    // Update an existing Pokémon
    @PutMapping("/{id}")
    public ResponseEntity<Pokemon> updatePokemon(@PathVariable Long id, @RequestBody Pokemon updatedPokemon) {
        return pokemonRepository.findById(id).map(pokemon -> {
            pokemon.setName(updatedPokemon.getName());
            pokemon.setType(updatedPokemon.getType());
            pokemon.setHeight(updatedPokemon.getHeight());
            pokemon.setWeight(updatedPokemon.getWeight());
            pokemon.setDescription(updatedPokemon.getDescription());
            return ResponseEntity.ok(pokemonRepository.save(pokemon));
        }).orElse(ResponseEntity.notFound().build());
    }

    // Delete a Pokémon
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePokemon(@PathVariable Long id) {
        if (pokemonRepository.existsById(id)) {
            pokemonRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}