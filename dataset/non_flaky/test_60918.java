class DummyClass_60918 {
  @Test
  public void testComputeResult()
  {
    BaseAverager<Number, Double> avg = new DoubleSumAverager(3, "test", "field", 1);

    Assert.assertEquals(0.0, avg.computeResult(), 0.0);

    avg.addElement(Collections.singletonMap("field", 3.0), new HashMap<>());
    Assert.assertEquals(3.0, avg.computeResult(), 0.0);

    avg.addElement(Collections.singletonMap("field", 3.0), new HashMap<>());
    Assert.assertEquals(6.0, avg.computeResult(), 0.0);

    avg.addElement(Collections.singletonMap("field", new Integer(0)), new HashMap<>());
    Assert.assertEquals(6.0, avg.computeResult(), 0.0);

    avg.addElement(Collections.singletonMap("field", 2.5), new HashMap<>());
    avg.addElement(Collections.singletonMap("field", 2.0), new HashMap<>());
    avg.addElement(Collections.singletonMap("field", 2.0), new HashMap<>());
    Assert.assertEquals(6.5, avg.computeResult(), 0.0);

    avg.skip();
    Assert.assertEquals(4.0, avg.computeResult(), 0.0);

  }

}