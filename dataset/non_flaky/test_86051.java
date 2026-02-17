class DummyClass_86051 {
    @Test
    public void outsideGracePeriod() {
        final NotificationGracePeriodService notificationGracePeriodService = new NotificationGracePeriodService();

        when(settings.gracePeriodMs()).thenReturn(10L);
        when(definition.notificationSettings()).thenReturn(settings);
        when(definition.id()).thenReturn("1234");

        final Event event = new TestEvent();
        event.setKeyTuple(ImmutableList.of("testkey"));
        final Event event2 = new TestEvent();
        event2.setKeyTuple(ImmutableList.of("testkey"));
        event2.setEventTimestamp(event.getEventTimestamp().plus(11L));
        assertThat(notificationGracePeriodService.inGracePeriod(definition, "5678", event)).isFalse();
        assertThat(notificationGracePeriodService.inGracePeriod(definition, "5678", event2)).isFalse();
    }

}