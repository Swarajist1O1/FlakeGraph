class DummyClass_76965 {
  @Test
  public void testSerializeBitmap() throws Exception {
    Roaring64NavigableMap bitmap1 = Roaring64NavigableMap.bitmapOf(1, 2, 100, 10000);
    byte[] bytes = RssUtils.serializeBitMap(bitmap1);
    Roaring64NavigableMap bitmap2 = RssUtils.deserializeBitMap(bytes);
    assertEquals(bitmap1, bitmap2);
    assertEquals(Roaring64NavigableMap.bitmapOf(), RssUtils.deserializeBitMap(new byte[]{}));
  }

}