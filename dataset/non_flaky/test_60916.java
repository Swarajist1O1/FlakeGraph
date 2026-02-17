class DummyClass_60916 {
  @Test
  public void testCreateAverager()
  {
    AveragerFactory<?, ?> fac = new LongMeanNoNullAveragerFactory("test", 5, 1, "field");
    Assert.assertThat(fac.createAverager(), IsInstanceOf.instanceOf(LongMeanNoNullAverager.class));
  }

}