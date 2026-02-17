class DummyClass_176917 {
  @Test
  public void testComputeSilhouetteCoefficient() {
    assertEquals(5.0, SilhouetteCoefficient.calcSilhouetteCoefficient(-0.8, 0.2));
    assertEquals(-1.25, SilhouetteCoefficient.calcSilhouetteCoefficient(0.8, -0.2));
    assertEquals(0.0, SilhouetteCoefficient.calcSilhouetteCoefficient(1.5, 1.5));
    assertEquals(1.0, SilhouetteCoefficient.calcSilhouetteCoefficient(1.5, Double.POSITIVE_INFINITY));
    assertEquals(-1.0, SilhouetteCoefficient.calcSilhouetteCoefficient(Double.POSITIVE_INFINITY, 1.5));
  }

}