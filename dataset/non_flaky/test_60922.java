class DummyClass_60922 {
  @Test
  public void testCreateAverager()
  {
    AveragerFactory<?, ?> fac = new DoubleMeanNoNullAveragerFactory("test", 5, 1, "field");
    Assert.assertThat(fac.createAverager(), IsInstanceOf.instanceOf(DoubleMeanNoNullAverager.class));
  }

}