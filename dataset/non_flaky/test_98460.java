class DummyClass_98460 {
  @Test
  public void testTagShouldHaveMpsAsIsActionable() {
    assertThat(Tag.mps.isActionable(), Matchers.is(true));
  }

}