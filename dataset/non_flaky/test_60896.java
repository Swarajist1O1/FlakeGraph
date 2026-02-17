class DummyClass_60896 {
  @Test
  public void testJacksonModules()
  {
    Assert.assertTrue(new KafkaEmitterModule().getJacksonModules().isEmpty());
  }

}