class DummyClass_60928 {
  @Test
  public void testCreateAverager()
  {
    AveragerFactory<?, ?> fac = new LongSumAveragerFactory("test", 5, 1, "field");
    Assert.assertThat(fac.createAverager(), IsInstanceOf.instanceOf(LongSumAverager.class));
  }

}