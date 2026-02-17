class DummyClass_98452 {
  @Test
  public void givenSomeEventsWhenScheduledIsCheckedThenFalse() throws CTPException {
    UUID collexUuid = UUID.randomUUID();
    List<Event> events = createEventList(Tag.mps, Tag.exercise_end);
    when(eventRepository.findByCollectionExerciseId(collexUuid)).thenReturn(events);

    boolean scheduled = this.eventService.isScheduled(collexUuid);

    assertFalse(scheduled);
  }

}