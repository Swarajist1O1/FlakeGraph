class DummyClass_60926 {
  @Test
  public void testComputeResult()
  {
    BaseAverager<Number, Long> avg = new LongSumAverager(3, "test", "field", 1);

    Assert.assertEquals(0.0, avg.computeResult(), 0.0);

    avg.addElement(Collections.singletonMap("field", 3L), new HashMap<>());
    Assert.assertEquals(3.0, avg.computeResult(), 0.0);

    avg.addElement(Collections.singletonMap("field", 3L), new HashMap<>());
    Assert.assertEquals(6.0, avg.computeResult(), 0.0);

    avg.addElement(Collections.singletonMap("field", 3), new HashMap<>());
    Assert.assertEquals(9.0, avg.computeResult(), 0.0);

    avg.addElement(Collections.singletonMap("field", 2L), new HashMap<>());
    avg.addElement(Collections.singletonMap("field", 2L), new HashMap<>());
    avg.addElement(Collections.singletonMap("field", 2L), new HashMap<>());
    Assert.assertEquals(6.0, avg.computeResult(), 0.0);

    avg.skip();
    Assert.assertEquals(4.0, avg.computeResult(), 0.0);
  }

}