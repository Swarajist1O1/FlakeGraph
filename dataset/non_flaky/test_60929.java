class DummyClass_60929 {
  @Test
  public void testComputeResult()
  {
    BaseAverager<Number, Double> averager = new DoubleMeanAverager(14, "test", "field", 7);

    averager.addElement(Collections.singletonMap("field", 7.0), new HashMap<>());
    averager.addElement(Collections.singletonMap("field", 1.0), new HashMap<>());
    averager.addElement(Collections.singletonMap("field", 2.0), new HashMap<>());
    averager.addElement(Collections.singletonMap("field", 3.0), new HashMap<>());
    averager.addElement(Collections.singletonMap("field", 4.0), new HashMap<>());
    averager.addElement(Collections.singletonMap("field", 5.0), new HashMap<>());
    averager.addElement(Collections.singletonMap("field", 6.0), new HashMap<>());
    averager.addElement(Collections.singletonMap("field", 7.0), new HashMap<>());
    averager.addElement(Collections.singletonMap("field", 1.0), new HashMap<>());
    averager.addElement(Collections.singletonMap("field", 2.0), new HashMap<>());
    averager.addElement(Collections.singletonMap("field", 3.0), new HashMap<>());
    averager.addElement(Collections.singletonMap("field", 4.0), new HashMap<>());
    averager.addElement(Collections.singletonMap("field", 5.0), new HashMap<>());
    averager.addElement(Collections.singletonMap("field", 6.0), new HashMap<>());

    Assert.assertEquals(7, averager.computeResult(), 0.0); // (7+7)/2

    averager.addElement(Collections.singletonMap("field", 3.0), new HashMap<>());
    Assert.assertEquals(1, averager.computeResult(), 0.0); // (1+1)/2

    BaseAverager<Number, Double> averager1 = new DoubleMeanAverager(14, "test", "field", 3);

    averager1.addElement(Collections.singletonMap("field", 1.0), new HashMap<>());
    averager1.addElement(Collections.singletonMap("field", 2.0), new HashMap<>());
    averager1.addElement(Collections.singletonMap("field", 3.0), new HashMap<>());
    averager1.addElement(Collections.singletonMap("field", 1.0), new HashMap<>());
    averager1.addElement(Collections.singletonMap("field", 2.0), new HashMap<>());
    averager1.addElement(Collections.singletonMap("field", 3.0), new HashMap<>());
    averager1.addElement(Collections.singletonMap("field", 1.0), new HashMap<>());
    averager1.addElement(Collections.singletonMap("field", 2.0), new HashMap<>());
    averager1.addElement(Collections.singletonMap("field", 3.0), new HashMap<>());
    averager1.addElement(Collections.singletonMap("field", 1.0), new HashMap<>());
    averager1.addElement(Collections.singletonMap("field", 2.0), new HashMap<>());
    averager1.addElement(Collections.singletonMap("field", 3.0), new HashMap<>());
    averager1.addElement(Collections.singletonMap("field", 1.0), new HashMap<>());
    averager1.addElement(Collections.singletonMap("field", 2.0), new HashMap<>());

    Assert.assertEquals(1, averager1.computeResult(), 0.0); // (1+1+1+1+1)/5

    Assert.assertEquals(2, averager1.computeResult(), 0.0); // (2+2+2+2+2)/5

    Assert.assertEquals(13.0 / 5, averager1.computeResult(), 0.0); // (3+3+3+3+1)/5
  }

}