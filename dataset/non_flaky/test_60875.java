class DummyClass_60875 {
  @Test
  public void testSanity()
  {
    Assert.assertNull(cache.get(new Cache.NamedKey("a", HI)));
    put(cache, "a", HI, 0);
    Assert.assertEquals(0, get(cache, "a", HI));
    Assert.assertNull(cache.get(new Cache.NamedKey("the", HI)));

    put(cache, "the", HI, 1);
    Assert.assertEquals(0, get(cache, "a", HI));
    Assert.assertEquals(1, get(cache, "the", HI));

    put(cache, "the", HO, 10);
    Assert.assertEquals(0, get(cache, "a", HI));
    Assert.assertNull(cache.get(new Cache.NamedKey("a", HO)));
    Assert.assertEquals(1, get(cache, "the", HI));
    Assert.assertEquals(10, get(cache, "the", HO));

    cache.close("the");
    Assert.assertEquals(0, get(cache, "a", HI));
    Assert.assertNull(cache.get(new Cache.NamedKey("a", HO)));
  }

}