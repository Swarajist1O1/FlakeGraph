class DummyClass_60917 {
  @Test
  public void testCreateAverager()
  {
    AveragerFactory<?, ?> fac = new LongMinAveragerFactory("test", 5, 1, "field");
    Assert.assertThat(fac.createAverager(), IsInstanceOf.instanceOf(LongMinAverager.class));
  }

}