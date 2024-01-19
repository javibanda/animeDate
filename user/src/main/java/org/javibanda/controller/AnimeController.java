package org.javibanda.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.javibanda.model.dto.ClaimDTO;
import org.javibanda.model.entity.anime.Anime;
import org.javibanda.model.entity.anime.AnimeCharacter;
import org.javibanda.model.enums.Role;
import org.javibanda.service.AnimeService;
import org.javibanda.service.CharacterService;
import org.javibanda.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/anime")
@RequiredArgsConstructor
public class AnimeController {

    private final AnimeService animeService;
    private final UserService userService;
    private final CharacterService characterService;
    @PostMapping
    public ResponseEntity<String> save(@RequestHeader("Authorization") String token,
                                       @RequestParam String animeName) {
        if (isRoleUnauthorized(token)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Role unauthorized");
        }
        if (animeExist(animeName, null)){
            return ResponseEntity.status(HttpStatus.FOUND).body("Anime exist");
        }
        animeService.save(animeName);
        return ResponseEntity.ok("OK");
    }

    @GetMapping
    public ResponseEntity<List<String>> getAllAnimes() {
        return ResponseEntity.ok(animeService.getAll());
    }

    @GetMapping("/favorites")
    public ResponseEntity<List<Anime>> getFavoriteAnimes(@RequestHeader("Authorization") String token) {
        val favoriteAnimes = animeService.getFavoriteAnimes(getProfileId(token));
        return ResponseEntity.ok(favoriteAnimes);
    }

    @PostMapping("/favorites")
    public ResponseEntity<String> saveFavoriteAnimes(@RequestHeader("Authorization") String token,
                                                     @RequestBody List<String> animes) {
        animeService.saveFavoriteAnime(getProfileId(token), animes);
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/character")
    public ResponseEntity<String> saveCharacter(@RequestHeader("Authorization") String token,
                                                @RequestParam String animeName,
                                                @RequestBody AnimeCharacter request) {
        if (isRoleUnauthorized(token)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Role unauthorized");
        }
        if (characterExist(request.getName())){
            return ResponseEntity.status(HttpStatus.FOUND).body("Character exist");
        }
        if (!animeExist(animeName, request)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Anime dont exist");
        }
        characterService.save(request);
        return ResponseEntity.ok("OK");

    }

    private boolean isRoleUnauthorized(String token){
        ClaimDTO claimDTO = getClaimDto(token);
        return claimDTO.getRole() != Role.ADMIN;
    }
    private ClaimDTO getClaimDto(String token){
        return userService.getUserFromToken(token);
    }

    private UUID getProfileId(String token){
        return getClaimDto(token).getProfileId();
    }

    private boolean animeExist(String animeName, AnimeCharacter animeCharacter){
        val anime = getAnime(animeName);
        if (animeCharacter != null){
            animeCharacter.setAnime(anime);
        }
        return getAnime(animeName) != null;
    }

    private boolean characterExist(String characterName){
        return characterService.characterExist(characterName);
    }

    private Anime getAnime(String animeName){
        return animeService.get(animeName);
    }
}
