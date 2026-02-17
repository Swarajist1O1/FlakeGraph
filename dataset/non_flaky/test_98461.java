class DummyClass_98461 {
  @Test
  public void testTagShouldHaveGoLiveAsIsAnActionableTag() {
    assertThat(Tag.go_live.isActionable(), Matchers.is(true));
  }

}