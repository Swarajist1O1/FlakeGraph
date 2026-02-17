class DummyClass_60876 {
  @Test
  public void testGetBulk()
  {
    Assert.assertNull(cache.get(new Cache.NamedKey("the", HI)));

    put(cache, "the", HI, 1);
    put(cache, "the", HO, 10);

    Cache.NamedKey key1 = new Cache.NamedKey("the", HI);
    Cache.NamedKey key2 = new Cache.NamedKey("the", HO);
    Cache.NamedKey key3 = new Cache.NamedKey("a", HI);

    Map<Cache.NamedKey, byte[]> result = cache.getBulk(
        Lists.newArrayList(
            key1,
            key2,
            key3
        )
    );

    Assert.assertEquals(1, Ints.fromByteArray(result.get(key1)));
    Assert.assertEquals(10, Ints.fromByteArray(result.get(key2)));
    Assert.assertEquals(null, result.get(key3));
  }

}