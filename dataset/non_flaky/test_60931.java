class DummyClass_60931 {
  @Test
  public void testAddElement()
  {
    BaseAverager<Integer, Integer> avg = new TestAverager(Integer.class, 3, "test", "field", 1);
    Object[] buckets = avg.getBuckets();

    avg.addElement(Collections.singletonMap("field", 1), Collections.emptyMap());
    Assert.assertEquals(1, buckets[0]);
    Assert.assertNull(buckets[1]);
    Assert.assertNull(buckets[2]);

    avg.addElement(Collections.singletonMap("field", 2), Collections.emptyMap());
    Assert.assertEquals(1, buckets[0]);
    Assert.assertEquals(2, buckets[1]);
    Assert.assertNull(buckets[2]);

    avg.addElement(Collections.singletonMap("field", 3), Collections.emptyMap());
    Assert.assertEquals(1, buckets[0]);
    Assert.assertEquals(2, buckets[1]);
    Assert.assertEquals(3, buckets[2]);

    avg.addElement(Collections.singletonMap("field", 4), Collections.emptyMap());
    Assert.assertEquals(4, buckets[0]);
    Assert.assertEquals(2, buckets[1]);
    Assert.assertEquals(3, buckets[2]);
  }

}