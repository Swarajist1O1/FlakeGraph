class DummyClass_98459 {
  @Test
  public void testProcessEventsTransitionGoLive() {
    // Given
    List<Event> list = new ArrayList<>();
    Event event = createEvent(Tag.go_live);
    CollectionExercise collectionExercise = new CollectionExercise();
    collectionExercise.setState(CollectionExerciseState.LIVE);
    event.setCollectionExercise(collectionExercise);
    list.add(event);

    when(eventRepository.findByStatus(EventDTO.Status.SCHEDULED)).thenReturn(list);

    // When
    eventService.processEvents();

    // Then
    verify(eventRepository, atMost(1)).findByStatus(EventDTO.Status.SCHEDULED);
    verify(actionSvcClient, atMost(1)).processEvent(any(), any());
    try {
      verify(collectionExerciseService, atMost(1))
          .transitionCollectionExercise(
              any(CollectionExercise.class),
              any(CollectionExerciseDTO.CollectionExerciseEvent.class));
    } catch (CTPException e) {
      fail();
    }
  }

}