class DummyClass_176893 {
  @Test
  public void testAnd() {
    NotContainsPredicate<String> a = new NotContainsPredicate<>(Arrays.asList("foo"));
    NotContainsPredicate<String> b = new NotContainsPredicate<>(Arrays.asList("bar", "baz"));
    AndPredicate<String> and = new AndPredicate<>(a, b);
    assertFalse(and.test("foo"));
    assertFalse(and.test("bar"));
    assertFalse(and.test("baz"));
    assertTrue(and.test("bing"));
  }

}