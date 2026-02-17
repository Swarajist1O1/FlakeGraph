class DummyClass_60919 {
  @Test
  public void testComputeResult()
  {
    BaseAverager<Number, Double> avg = new LongMeanNoNullAverager(3, "test", "field", 1);

    Assert.assertEquals(Double.NaN, avg.computeResult(), 0.0);

    avg.addElement(Collections.singletonMap("field", 3L), new HashMap<>());
    Assert.assertEquals(3.0, avg.computeResult(), 0.0);

    avg.addElement(Collections.singletonMap("field", 3L), new HashMap<>());
    Assert.assertEquals(3.0, avg.computeResult(), 0.0);

    avg.addElement(Collections.singletonMap("field", 0), new HashMap<>());
    Assert.assertEquals(2.0, avg.computeResult(), 0.0);

    avg.addElement(Collections.singletonMap("field", 2L), new HashMap<>());
    avg.addElement(Collections.singletonMap("field", 2L), new HashMap<>());
    avg.addElement(Collections.singletonMap("field", 2L), new HashMap<>());
    Assert.assertEquals(2.0, avg.computeResult(), 0.0);

    avg.skip();
    Assert.assertEquals(2.0, avg.computeResult(), 0.0);
  }

}