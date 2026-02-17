class DummyClass_53135 {
    @Test
    public void testNotifications() {
        uaaMetricsEmitter.enableNotification();
        emitter.sendNotification(new Notification("/api", 45L, 0));
        Mockito.verify(statsDClient).time("requests.api.latency", 45L);
    }

}