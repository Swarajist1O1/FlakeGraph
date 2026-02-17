class DummyClass_60912 {
  @Test
  public void testCreateAverager()
  {
    AveragerFactory<?, ?> fac = new DoubleMaxAveragerFactory("test", 5, 1, "field");
    Assert.assertThat(fac.createAverager(), CoreMatchers.instanceOf(DoubleMaxAverager.class));
  }

}