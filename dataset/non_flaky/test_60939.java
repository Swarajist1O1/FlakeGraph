class DummyClass_60939 {
  @Test
  public void testComputeResult()
  {
    BaseAverager<Number, Long> avg = new LongMinAverager(3, "test", "field", 1);

    Assert.assertEquals(Long.MAX_VALUE, (long) avg.computeResult());

    avg.addElement(Collections.singletonMap("field", -10000L), new HashMap<>());
    Assert.assertEquals(-10000, (long) avg.computeResult());

    avg.addElement(Collections.singletonMap("field", 1L), new HashMap<>());
    Assert.assertEquals(-10000, (long) avg.computeResult());

    avg.addElement(Collections.singletonMap("field", 1000), new HashMap<>());
    Assert.assertEquals(-10000, (long) avg.computeResult());

    avg.addElement(Collections.singletonMap("field", 5L), new HashMap<>());
    avg.addElement(Collections.singletonMap("field", 2L), new HashMap<>());
    avg.addElement(Collections.singletonMap("field", 3L), new HashMap<>());
    Assert.assertEquals(2, (long) avg.computeResult());

    avg.skip();
    avg.skip();
    Assert.assertEquals(3, (long) avg.computeResult());
  }

}