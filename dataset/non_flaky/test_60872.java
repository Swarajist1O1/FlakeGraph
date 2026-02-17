class DummyClass_60872 {
  @Test
  public void testCache()
  {
    Assert.assertNull(cache.get(new Cache.NamedKey("the", HI)));

    Cache.NamedKey key1 = new Cache.NamedKey("the", HI);
    Cache.NamedKey key2 = new Cache.NamedKey("the", HO);
    Cache.NamedKey key3 = new Cache.NamedKey("a", HI);
    Cache.NamedKey notExist = new Cache.NamedKey("notExist", HI);

    //test put and get
    cache.put(key1, new byte[]{1, 2, 3, 4});
    cache.put(key2, new byte[]{2, 3, 4, 5});
    cache.put(key3, new byte[]{3, 4, 5, 6});
    Assert.assertEquals(0x01020304, Ints.fromByteArray(cache.get(key1)));
    Assert.assertEquals(0x02030405, Ints.fromByteArray(cache.get(key2)));
    Assert.assertEquals(0x03040506, Ints.fromByteArray(cache.get(key3)));
    Assert.assertEquals(0x03040506, Ints.fromByteArray(cache.get(key3)));
    Assert.assertNull(cache.get(notExist));

    this.mgetCount.set(0);

    //test multi get
    Map<Cache.NamedKey, byte[]> result = cache.getBulk(
        Lists.newArrayList(
            key1,
            key2,
            key3,
            notExist
        )
    );

    // these 4 keys are distributed among different nodes, so there should be 4 times call of MGET
    Assert.assertEquals(mgetCount.get(), 4);
    Assert.assertEquals(result.size(), 3);
    Assert.assertEquals(0x01020304, Ints.fromByteArray(result.get(key1)));
    Assert.assertEquals(0x02030405, Ints.fromByteArray(result.get(key2)));
    Assert.assertEquals(0x03040506, Ints.fromByteArray(result.get(key3)));
  }

}