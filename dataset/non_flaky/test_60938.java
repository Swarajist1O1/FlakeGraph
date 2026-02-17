class DummyClass_60938 {
  @Test
  public void testComputeResult()
  {
    BaseAverager<Number, Long> avg = new LongMaxAverager(3, "test", "field", 1);

    Assert.assertEquals(Long.MIN_VALUE, (long) avg.computeResult());

    avg.addElement(Collections.singletonMap("field", -1000000L), new HashMap<>());
    Assert.assertEquals(-1000000, (long) avg.computeResult());

    avg.addElement(Collections.singletonMap("field", 1L), new HashMap<>());
    Assert.assertEquals(1, (long) avg.computeResult());

    avg.addElement(Collections.singletonMap("field", 1), new HashMap<>());
    Assert.assertEquals(1, (long) avg.computeResult());

    avg.addElement(Collections.singletonMap("field", 5L), new HashMap<>());
    avg.addElement(Collections.singletonMap("field", 3L), new HashMap<>());
    avg.addElement(Collections.singletonMap("field", 2L), new HashMap<>());
    Assert.assertEquals(5, (long) avg.computeResult());

    avg.skip();
    Assert.assertEquals(3, (long) avg.computeResult());
  }

}