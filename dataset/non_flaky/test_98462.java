class DummyClass_98462 {
  @Test
  public void testTagShouldHaveExerciseEndAsNotIsAnActionableTag() {
    assertThat(Tag.exercise_end.isActionable(), Matchers.is(false));
  }

}