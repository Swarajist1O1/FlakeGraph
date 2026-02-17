class DummyClass_60925 {
  @Test
  public void testComputeResult()
  {
    BaseAverager<Number, Double> avg = new DoubleMeanNoNullAverager(3, "test", "field", 1);

    Assert.assertEquals(Double.NaN, avg.computeResult(), 0.0);

    avg.addElement(Collections.singletonMap("field", 3.0), new HashMap<>());
    Assert.assertEquals(3.0, avg.computeResult(), 0.0);

    avg.addElement(Collections.singletonMap("field", 3.0), new HashMap<>());
    Assert.assertEquals(3.0, avg.computeResult(), 0.0);

    avg.addElement(Collections.singletonMap("field", 0), new HashMap<>());
    Assert.assertEquals(2.0, avg.computeResult(), 0.0);

    avg.addElement(Collections.singletonMap("field", 2.0), new HashMap<>());
    avg.addElement(Collections.singletonMap("field", 2.0), new HashMap<>());
    avg.addElement(Collections.singletonMap("field", 2.0), new HashMap<>());
    Assert.assertEquals(2.0, avg.computeResult(), 0.0);

    avg.skip();
    Assert.assertEquals(2.0, avg.computeResult(), 0.0);

    // testing cycleSize functionality
    BaseAverager<Number, Double> averager = new DoubleMeanNoNullAverager(14, "test", "field", 7);

    averager.addElement(Collections.singletonMap("field", 2.0), new HashMap<>());
    Assert.assertEquals(2.0, averager.computeResult(), 0.0);

    averager.addElement(Collections.singletonMap("field", 4.0), new HashMap<>());
    averager.addElement(Collections.singletonMap("field", 5.0), new HashMap<>());
    averager.addElement(Collections.singletonMap("field", 6.0), new HashMap<>());
    averager.addElement(Collections.singletonMap("field", 7.0), new HashMap<>());
    averager.addElement(Collections.singletonMap("field", 8.0), new HashMap<>());
    averager.addElement(Collections.singletonMap("field", 9.0), new HashMap<>());
    averager.addElement(Collections.singletonMap("field", null), new HashMap<>());
    averager.addElement(Collections.singletonMap("field", 11.0), new HashMap<>());
    averager.addElement(Collections.singletonMap("field", 12.0), new HashMap<>());
    averager.addElement(Collections.singletonMap("field", 13.0), new HashMap<>());
    averager.addElement(Collections.singletonMap("field", 14.0), new HashMap<>());
    averager.addElement(Collections.singletonMap("field", 15.0), new HashMap<>());
    averager.addElement(Collections.singletonMap("field", 16.0), new HashMap<>());

    Assert.assertEquals(7.5, averager.computeResult(), 0.0);

    averager.addElement(Collections.singletonMap("field", 3.0), new HashMap<>());
    Assert.assertEquals(8.5, averager.computeResult(), 0.0);
  }

}