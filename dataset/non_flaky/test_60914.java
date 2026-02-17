class DummyClass_60914 {
  @Test
  public void testCreateAverager()
  {
    AveragerFactory<?, ?> fac = new DoubleSumAveragerFactory("test", 5, 1, "field");
    Assert.assertThat(fac.createAverager(), IsInstanceOf.instanceOf(DoubleSumAverager.class));
  }

}