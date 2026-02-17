class DummyClass_176894 {
  @Test
  public void testPredicate() {
    Collection<Integer> contains = Arrays.asList(1, 3, 5);
    NotContainsPredicate<Integer> predicate = new NotContainsPredicate<>(contains);
    assertTrue(predicate.test(2));
    assertFalse(predicate.test(5));
  }

}