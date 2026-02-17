class DummyClass_38707 {
    @Test
    public void subscriptionTypeTest() throws Exception {
        try (KafkaConnectSink sink = new KafkaConnectSink()) {
            log.info("Failover is allowed");
            sink.open(props, context);
        }

        when(context.getSubscriptionType()).thenReturn(SubscriptionType.Exclusive);
        try (KafkaConnectSink sink = new KafkaConnectSink()) {
            log.info("Exclusive is allowed");
            sink.open(props, context);
        }

        when(context.getSubscriptionType()).thenReturn(SubscriptionType.Key_Shared);
        try (KafkaConnectSink sink = new KafkaConnectSink()) {
            log.info("Key_Shared is not allowed");
            sink.open(props, context);
            fail("expected exception");
        } catch (IllegalArgumentException iae) {
            // pass
        }

        when(context.getSubscriptionType()).thenReturn(SubscriptionType.Shared);
        try (KafkaConnectSink sink = new KafkaConnectSink()) {
            log.info("Shared is not allowed");
            sink.open(props, context);
            fail("expected exception");
        } catch (IllegalArgumentException iae) {
            // pass
        }

        when(context.getSubscriptionType()).thenReturn(null);
        try (KafkaConnectSink sink = new KafkaConnectSink()) {
            log.info("Type is required");
            sink.open(props, context);
            fail("expected exception");
        } catch (IllegalArgumentException iae) {
            // pass
        }

    }

}