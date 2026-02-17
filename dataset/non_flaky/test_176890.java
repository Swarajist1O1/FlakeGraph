class DummyClass_176890 {
  @Test
  public void testEquals() {
    assertEquals(new Pair<>(3.0, "foo"), new Pair<>(3.0, "foo"));
    assertEquals(new Pair<>(null, null), new Pair<>(null, null));
    assertFalse(new Pair<>(3.0, "foo").equals(new Pair<>(4.0, "foo")));
    assertNotEquals(new Pair<>(3.0, "foo"), new Pair<>("foo", 3.0));
    assertNotEquals("3.0,foo", new Pair<>(3.0, "foo"));
  }

}