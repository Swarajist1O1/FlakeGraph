class DummyClass_98468 {
  @Test
  public void testTagShouldHaveNudge2AsAnActionableTag() {
    assertThat(Tag.nudge_email_2.isActionable(), Matchers.is(true));
  }

}