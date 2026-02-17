class DummyClass_86052 {
    @Test
    public void insideThenInsideGracePeriod() {
        final NotificationGracePeriodService notificationGracePeriodService = new NotificationGracePeriodService();

        when(settings.gracePeriodMs()).thenReturn(10L);
        when(definition.notificationSettings()).thenReturn(settings);
        when(definition.id()).thenReturn("1234");

        final Event event = new TestEvent(DateTime.now(UTC), "testkey");
        final Event event2 = new TestEvent(event.getEventTimestamp().plus(5L), "testkey");
        final Event event3 = new TestEvent(event2.getEventTimestamp().plus(4L), "testkey");

        assertThat(notificationGracePeriodService.inGracePeriod(definition, "5678", event)).isFalse();
        assertThat(notificationGracePeriodService.inGracePeriod(definition, "5678", event2)).isTrue();
        assertThat(notificationGracePeriodService.inGracePeriod(definition, "5678", event3)).isTrue();
    }

}