class DummyClass_98463 {
  @Test
  public void testTagShouldHaveReminderAsAnActionableTag() {
    assertThat(Tag.reminder.isActionable(), Matchers.is(true));
  }

}