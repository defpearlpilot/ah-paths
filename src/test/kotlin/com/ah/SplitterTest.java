package com.ah;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class SplitterTest {
  @Test
  void getBatchSize() {
    Splitter s = new Splitter(11);
    assertEquals(3, s.getBatchSize());
  }

}