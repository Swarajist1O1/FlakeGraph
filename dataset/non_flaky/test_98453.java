class DummyClass_98453 {
  @Test
  public void givenAllEventsWhenScheduledIsCheckedThenTrue() throws CTPException {
    UUID collexUuid = UUID.randomUUID();
    List<Event> events = createEventList(Tag.values());
    when(eventRepository.findByCollectionExerciseId(collexUuid)).thenReturn(events);

    boolean scheduled = this.eventService.isScheduled(collexUuid);

    assertTrue(scheduled);
  }

}