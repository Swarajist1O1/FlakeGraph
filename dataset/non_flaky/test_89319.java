class DummyClass_89319 {
  @Test
  public void testConfigValidations() {

    final KafkaSystemConsumer consumer = createConsumer(FETCH_THRESHOLD_MSGS, FETCH_THRESHOLD_BYTES);

    consumer.start();
    // should be no failures
  }

}