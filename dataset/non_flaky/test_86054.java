class DummyClass_86054 {
    @Test
    public void differentKey() {
        final NotificationGracePeriodService notificationGracePeriodService = new NotificationGracePeriodService();

        when(settings.gracePeriodMs()).thenReturn(10L);
        when(definition.notificationSettings()).thenReturn(settings);
        when(definition.id()).thenReturn("1234");

        final Event event = new TestEvent();
        event.setKeyTuple(ImmutableList.of("testkey"));
        final Event event2 = new TestEvent();
        event2.setKeyTuple(ImmutableList.of("otherkey"));
        event2.setEventTimestamp(event.getEventTimestamp().plus(1L));
        assertThat(notificationGracePeriodService.inGracePeriod(definition, "5678", event)).isFalse();
        assertThat(notificationGracePeriodService.inGracePeriod(definition, "5678", event2)).isFalse();
    }

}