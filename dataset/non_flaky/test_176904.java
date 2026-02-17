class DummyClass_176904 {
  @Test
  public void testJoinJSON() {
    assertEquals("[\"1\",\"2\",\"3\"]", TextUtils.joinJSON(Arrays.asList("1", "2", "3")));
    assertEquals("[\"1 \",\"2 \",\"3\"]", TextUtils.joinJSON(Arrays.asList("1 ", "2 ", "3")));
    assertEquals("[]", TextUtils.joinJSON(Collections.emptyList()));
  }

}