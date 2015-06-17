package com.etiennek.trade.integration.bgg;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.etiennek.util.Utils.*;

@Service
public class BggGateway {
  public Items search(String query) {
    RestTemplate restTemplate = new RestTemplate();
    Items response =
        restTemplate.getForObject("http://www.boardgamegeek.com/xmlapi2/search?query={query}&type={type}",
            Items.class, m().put("query", query)
                                 .put("type", "boardgame,boardgameexpansion,rpgitem")
                                 .build());
    return response;
  }
}
