class DummyClass_98458 {
  @Test
  public void testProcessEventsOnlyEventInFuture() {
    // Given
    List<Event> list = new ArrayList<>();
    Event event = createEvent(Tag.mps, "31/12/2999");
    CollectionExercise collectionExercise = new CollectionExercise();
    collectionExercise.setState(CollectionExerciseState.LIVE);
    event.setCollectionExercise(collectionExercise);
    list.add(event);

    when(eventRepository.findByStatus(EventDTO.Status.SCHEDULED)).thenReturn(list);

    // When
    eventService.processEvents();

    // Then
    verify(eventRepository, atMost(1)).findByStatus(EventDTO.Status.SCHEDULED);
    verify(actionSvcClient, never()).processEvent(any(), any());
  }

}