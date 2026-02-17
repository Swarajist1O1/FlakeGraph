class DummyClass_176833 {
  @Test
  public void testRandomState() {
    RandomGenerator generator = RandomManager.getRandom();
    double unseededValue = generator.nextDouble();
    RandomManager.useTestSeed();
    double seededValue = generator.nextDouble();
    assertNotEquals(unseededValue, seededValue);
    assertEquals(seededValue, RandomManager.getRandom().nextDouble());
  }

}