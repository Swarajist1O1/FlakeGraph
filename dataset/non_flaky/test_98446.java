class DummyClass_98446 {
  @Test
  public void givenCollectionExcerciseDoesNotExistWhenEventIsDeletedThenExceptionIsThrown() {
    final UUID collexUuid = UUID.randomUUID();

    when(collectionExerciseService.findCollectionExercise(collexUuid)).thenReturn(null);

    try {
      eventService.deleteEvent(collexUuid, Tag.mps.name());

      Assert.fail("Deleted event with non-existent collection exercise");
    } catch (final CTPException e) {
      assertThat(e.getFault(), is(Fault.BAD_REQUEST));
    }
  }

}