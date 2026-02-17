class DummyClass_60915 {
  @Test
  public void testCreateAverager()
  {
    AveragerFactory<?, ?> fac = new LongMaxAveragerFactory("test", 5, 1, "field");
    Assert.assertThat(fac.createAverager(), IsInstanceOf.instanceOf(LongMaxAverager.class));
  }

}