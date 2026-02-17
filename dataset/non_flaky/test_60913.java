class DummyClass_60913 {
  @Test
  public void testCreateAverager()
  {
    AveragerFactory<?, ?> fac = new DoubleMinAveragerFactory("test", 5, 1, "field");
    Assert.assertThat(fac.createAverager(), IsInstanceOf.instanceOf(DoubleMinAverager.class));
  }

}