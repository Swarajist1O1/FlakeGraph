class DummyClass_60930 {
  @Test
  public void testBaseAverager()
  {
    BaseAverager<Integer, Integer> avg = new TestAverager(Integer.class, 5, "test", "field", 1);

    Assert.assertEquals("test", avg.getName());
    Assert.assertEquals(5, avg.getNumBuckets());
    Assert.assertEquals(5, avg.getBuckets().length);
    Assert.assertTrue(avg.getBuckets().getClass().isArray());
  }

}