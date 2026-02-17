class DummyClass_89346 {
  @Test
  public void testInstantiateProducer() {
    KafkaSystemProducer ksp = new KafkaSystemProducer("SysName", new ExponentialSleepStrategy(2.0, 200, 10000),
      new AbstractFunction0<Producer<byte[], byte[]>>() {
        @Override
        public Producer<byte[], byte[]> apply() {
          return new KafkaProducer<>(new HashMap<String, Object>());
        }
      }, new KafkaSystemProducerMetrics("SysName", new MetricsRegistryMap()), new AbstractFunction0<Object>() {
        @Override
        public Object apply() {
          return System.currentTimeMillis();
        }

}