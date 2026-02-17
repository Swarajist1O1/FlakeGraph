class DummyClass_60927 {
  @Test
  public void testCreateAverager()
  {
    AveragerFactory<?, ?> fac = new LongMeanAveragerFactory("test", 5, 1, "field");
    Assert.assertThat(fac.createAverager(), IsInstanceOf.instanceOf(LongMeanAverager.class));
  }

}