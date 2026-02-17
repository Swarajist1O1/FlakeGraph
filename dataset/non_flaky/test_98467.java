class DummyClass_98467 {
  @Test
  public void testTagShouldHaveNudge1AsAnActionableTag() {
    assertThat(Tag.nudge_email_1.isActionable(), Matchers.is(true));
  }

}