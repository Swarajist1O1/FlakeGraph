class DummyClass_86038 {
    @Test
    public void testExecution() throws EventNotificationException {
        notificationResourceHandler.test(getHttpNotification(), "testUser");

        ArgumentCaptor<EventNotificationContext> captor = ArgumentCaptor.forClass(EventNotificationContext.class);
        verify(eventNotification, times(1)).execute(captor.capture());

        assertThat(captor.getValue()).satisfies(ctx -> {
            assertThat(ctx.event().message()).isEqualTo("Notification test message triggered from user <testUser>");
            assertThat(ctx.notificationId()).isEqualTo(NotificationTestData.TEST_NOTIFICATION_ID);
            assertThat(ctx.notificationConfig().type()).isEqualTo(HTTPEventNotificationConfig.TYPE_NAME);
            assertThat(ctx.eventDefinition().get().title()).isEqualTo("Event Definition Test Title");
        });
    }

}