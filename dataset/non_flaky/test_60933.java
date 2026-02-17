class DummyClass_60933 {
  @Test
  public void testHasData()
  {
    BaseAverager<Integer, Integer> avg = new TestAverager(Integer.class, 3, "test", "field", 1);

    Assert.assertFalse(avg.hasData());

    avg.addElement(Collections.singletonMap("field", 1), Collections.emptyMap());
    Assert.assertTrue(avg.hasData());

    avg.skip();
    avg.skip();
    avg.skip();

    Assert.assertFalse(avg.hasData());
  }

}