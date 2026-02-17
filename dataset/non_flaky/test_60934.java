class DummyClass_60934 {
  @Test
  public void testGetResult()
  {
    BaseAverager<Integer, Integer> avg = new TestAverager(Integer.class, 3, "test", "field", 1);

    Assert.assertNull(avg.getResult());

    avg.addElement(Collections.singletonMap("field", 1), Collections.emptyMap());
    Assert.assertEquals(Integer.valueOf(1), avg.getResult());
  }

}