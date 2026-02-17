class DummyClass_60937 {
  @Test
  public void testComputeResult()
  {
    BaseAverager<Number, Double> avg = new LongMeanAverager(3, "test", "field", 1);

    Assert.assertEquals(0.0, avg.computeResult(), 0.0);

    avg.addElement(Collections.singletonMap("field", 3L), new HashMap<>());
    Assert.assertEquals(1.0, avg.computeResult(), 0.0);

    avg.addElement(Collections.singletonMap("field", 3L), new HashMap<>());
    Assert.assertEquals(2.0, avg.computeResult(), 0.0);

    avg.addElement(Collections.singletonMap("field", 3), new HashMap<>());
    Assert.assertEquals(3.0, avg.computeResult(), 0.0);

    avg.addElement(Collections.singletonMap("field", 2L), new HashMap<>());
    avg.addElement(Collections.singletonMap("field", 2L), new HashMap<>());
    avg.addElement(Collections.singletonMap("field", 2L), new HashMap<>());
    Assert.assertEquals(2.0, avg.computeResult(), 0.0);

    avg.skip();
    Assert.assertEquals(4.0 / 3, avg.computeResult(), 0.0);
  }

}