class DummyClass_60936 {
  @Test
  public void testComputeResult()
  {
    BaseAverager<Number, Double> avg = new DoubleMinAverager(3, "test", "field", 1);

    Assert.assertEquals(Double.POSITIVE_INFINITY, avg.computeResult(), 0.0);

    avg.addElement(Collections.singletonMap("field", -1.1e100), new HashMap<>());
    Assert.assertEquals(-1.1e100, avg.computeResult(), 0.0);

    avg.addElement(Collections.singletonMap("field", 1.0), new HashMap<>());
    Assert.assertEquals(-1.1e100, avg.computeResult(), 0.0);

    avg.addElement(Collections.singletonMap("field", new Integer(1)), new HashMap<>());
    Assert.assertEquals(-1.1e100, avg.computeResult(), 0.0);

    avg.addElement(Collections.singletonMap("field", 5.0), new HashMap<>());
    avg.addElement(Collections.singletonMap("field", 2.0), new HashMap<>());
    avg.addElement(Collections.singletonMap("field", 3.0), new HashMap<>());
    Assert.assertEquals(2.0, avg.computeResult(), 0.0);

    avg.skip();
    avg.skip();
    Assert.assertEquals(3.0, avg.computeResult(), 0.0);
  }

}