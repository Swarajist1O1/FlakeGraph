class DummyClass_98444 {
  @Test
  public void givenNoEventsWhenScheduledIsCheckedThenFalse() throws CTPException {
    UUID collexUuid = UUID.randomUUID();
    when(eventRepository.findByCollectionExerciseId(collexUuid)).thenReturn(new ArrayList<>());

    boolean scheduled = this.eventService.isScheduled(collexUuid);

    assertFalse(scheduled);
  }

}