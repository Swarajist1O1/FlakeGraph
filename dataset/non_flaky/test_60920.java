class DummyClass_60920 {
  @Test
  public void testComputeResult()
  {
    BaseAverager<Number, Double> avg = new DoubleMaxAverager(3, "test", "field", 1);

    Assert.assertEquals(Double.NEGATIVE_INFINITY, avg.computeResult(), 0.0);

    avg.addElement(Collections.singletonMap("field", -1.1e100), new HashMap<>());
    Assert.assertEquals(-1.1e100, avg.computeResult(), 0.0);

    avg.addElement(Collections.singletonMap("field", 1.0), new HashMap<>());
    Assert.assertEquals(1.0, avg.computeResult(), 0.0);

    avg.addElement(Collections.singletonMap("field", 1), new HashMap<>());
    Assert.assertEquals(1.0, avg.computeResult(), 0.0);

    avg.addElement(Collections.singletonMap("field", 5.0), new HashMap<>());
    avg.addElement(Collections.singletonMap("field", 3.0), new HashMap<>());
    avg.addElement(Collections.singletonMap("field", 2.0), new HashMap<>());
    Assert.assertEquals(5.0, avg.computeResult(), 0.0);

    avg.skip();
    Assert.assertEquals(3.0, avg.computeResult(), 0.0);
  }

}