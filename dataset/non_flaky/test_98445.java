class DummyClass_98445 {
  @Test
  public void givenCollectionExcerciseDoesNotExistWhenEventIsUpdatedThenExceptionIsThrown() {
    final UUID collexUuid = UUID.randomUUID();

    when(collectionExerciseService.findCollectionExercise(collexUuid)).thenReturn(null);

    try {
      eventService.updateEvent(collexUuid, Tag.mps.name(), new Date());

      Assert.fail("Updated event with non-existent collection exercise");
    } catch (final CTPException e) {
      assertThat(e.getFault(), is(Fault.BAD_REQUEST));
    }
  }

}