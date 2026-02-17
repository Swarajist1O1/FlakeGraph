class DummyClass_98457 {
  @Test
  public void testProcessEventsNoScheduledEvents() {
    // Given
    List<Event> emptyList = Collections.emptyList();
    when(eventRepository.findByStatus(EventDTO.Status.SCHEDULED)).thenReturn(emptyList);

    // When
    eventService.processEvents();

    // Then
    verify(eventRepository, atMost(1)).findByStatus(EventDTO.Status.SCHEDULED);
    verify(actionSvcClient, never()).processEvent(any(), any());
  }

}