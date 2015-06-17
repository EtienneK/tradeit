package com.etiennek.trade.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.etiennek.trade.integration.bgg.BggGateway;
import com.etiennek.trade.integration.bgg.Items;

import static com.etiennek.util.Utils.*;

@Controller
@RequestMapping("/board-game")
public class BoardGameController {

  private @Autowired BggGateway bggGateway;

  @RequestMapping("/search")
  public ModelAndView search(@RequestParam("q") String query) {
    Items items = bggGateway.search(query);
    return new ModelAndView("board_game/search", m().put("query", query)
                                                    .put("items", items.getItems())
                                                    .build());
  }

}
