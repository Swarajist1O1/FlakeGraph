class DummyClass_60935 {
  @Test
  public void testCreateAverager()
  {
    AveragerFactory<?, ?> fac = new DoubleMeanAveragerFactory("test", 5, 1, "field");
    Assert.assertThat(fac.createAverager(), IsInstanceOf.instanceOf(DoubleMeanAverager.class));
  }

}