class DummyClass_60892 {
  @Test(timeout = 15_000)
  public void testKafkaEmitter() throws InterruptedException
  {
    final List<ServiceMetricEvent> serviceMetricEvents = ImmutableList.of(
        ServiceMetricEvent.builder().build("m1", 1).build("service", "host")
    );

    final List<AlertEvent> alertEvents = ImmutableList.of(
        new AlertEvent("service", "host", "description")
    );

    final List<RequestLogEvent> requestLogEvents = ImmutableList.of(
        DefaultRequestLogEventBuilderFactory.instance().createRequestLogEventBuilder("requests",
            RequestLogLine.forSql("", null, DateTimes.nowUtc(), null, new QueryStats(ImmutableMap.of()))
        ).build("service", "host")
    );

    int totalEvents = serviceMetricEvents.size() + alertEvents.size() + requestLogEvents.size();
    int totalEventsExcludingRequestLogEvents = totalEvents - requestLogEvents.size();

    final CountDownLatch countDownSentEvents = new CountDownLatch(
        requestTopic == null ? totalEventsExcludingRequestLogEvents : totalEvents);
    final KafkaProducer<String, String> producer = EasyMock.createStrictMock(KafkaProducer.class);
    final KafkaEmitter kafkaEmitter = new KafkaEmitter(
        new KafkaEmitterConfig("", "metrics", "alerts", requestTopic, "test-cluster", null),
        new ObjectMapper()
    )
    {
      @Override
      protected Producer<String, String> setKafkaProducer()
      {
        return producer;
      }

      @Override
      protected void sendToKafka(final String topic, MemoryBoundLinkedBlockingQueue<String> recordQueue,
          Callback callback
      )
      {
        countDownSentEvents.countDown();
        super.sendToKafka(topic, recordQueue, callback);
      }
    };

    EasyMock.expect(producer.send(EasyMock.anyObject(), EasyMock.anyObject())).andReturn(null)
        .times(requestTopic == null ? totalEventsExcludingRequestLogEvents : totalEvents);
    EasyMock.replay(producer);
    kafkaEmitter.start();

    for (Event event : serviceMetricEvents) {
      kafkaEmitter.emit(event);
    }
    for (Event event : alertEvents) {
      kafkaEmitter.emit(event);
    }
    for (Event event : requestLogEvents) {
      kafkaEmitter.emit(event);
    }
    countDownSentEvents.await();

    Assert.assertEquals(0, kafkaEmitter.getMetricLostCount());
    Assert.assertEquals(0, kafkaEmitter.getAlertLostCount());
    Assert.assertEquals(requestTopic == null ? requestLogEvents.size() : 0, kafkaEmitter.getRequestLostCount());
    Assert.assertEquals(0, kafkaEmitter.getInvalidLostCount());

    while (true) {
      try {
        EasyMock.verify(producer);
        break;
      }
      catch (Throwable e) {
        // although the latch may have count down, producer.send may not have been called yet in KafkaEmitter
        // so wait for sometime before verifying the mock
        Thread.sleep(100);
        // just continue
      }
    }
  }

}