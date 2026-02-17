class DummyClass_60932 {
  @Test
  public void testSkip()
  {
    BaseAverager<Integer, Integer> avg = new TestAverager(Integer.class, 3, "test", "field", 1);
    Object[] buckets = avg.getBuckets();

    avg.addElement(Collections.singletonMap("field", 1), Collections.emptyMap());
    avg.addElement(Collections.singletonMap("field", 1), Collections.emptyMap());
    avg.addElement(Collections.singletonMap("field", 1), Collections.emptyMap());

    Assert.assertEquals(1, buckets[0]);
    Assert.assertEquals(1, buckets[1]);
    Assert.assertEquals(1, buckets[2]);

    avg.skip();
    Assert.assertNull(buckets[0]);
    Assert.assertNotNull(buckets[1]);
    Assert.assertNotNull(buckets[2]);

    avg.skip();
    Assert.assertNull(buckets[0]);
    Assert.assertNull(buckets[1]);
    Assert.assertNotNull(buckets[2]);

    avg.skip();
    Assert.assertNull(buckets[0]);
    Assert.assertNull(buckets[1]);
    Assert.assertNull(buckets[2]);

    // poke some test data into the array
    buckets[0] = 1;

    avg.skip();
    Assert.assertNull(buckets[0]);
    Assert.assertNull(buckets[1]);
    Assert.assertNull(buckets[2]);
  }

}