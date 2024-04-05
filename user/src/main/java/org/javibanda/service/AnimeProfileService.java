package org.javibanda.service;

import java.util.List;
import java.util.UUID;

public interface AnimeProfileService {

    void save(UUID profileId, List<String> animes);

}
