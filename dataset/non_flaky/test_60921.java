class DummyClass_60921 {
  @Test
  public void testComputeResult()
  {
    BaseAverager<Number, Double> avg = new DoubleMeanAverager(3, "test", "field", 1);

    Assert.assertEquals(0.0, avg.computeResult(), 0.0);

    avg.addElement(Collections.singletonMap("field", 3.0), new HashMap<>());
    Assert.assertEquals(1.0, avg.computeResult(), 0.0);

    avg.addElement(Collections.singletonMap("field", 3.0), new HashMap<>());
    Assert.assertEquals(2.0, avg.computeResult(), 0.0);

    avg.addElement(Collections.singletonMap("field", 0), new HashMap<>());
    Assert.assertEquals(2.0, avg.computeResult(), 0.0);

    avg.addElement(Collections.singletonMap("field", 2.0), new HashMap<>());
    avg.addElement(Collections.singletonMap("field", 2.0), new HashMap<>());
    avg.addElement(Collections.singletonMap("field", 2.0), new HashMap<>());
    Assert.assertEquals(2.0, avg.computeResult(), 0.0);

    avg.skip();
    Assert.assertEquals(4.0 / 3, avg.computeResult(), 0.0);
  }

}