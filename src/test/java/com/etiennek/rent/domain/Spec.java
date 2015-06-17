package com.etiennek.rent.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.etiennek.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class Spec {

  @Before
  public void init() {
  }

  @Test
  public void A_landlord_should_be_able_to_add_a_new_property_to_their_portfolio() {

  }

}
