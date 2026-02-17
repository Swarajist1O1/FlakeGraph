class DummyClass_98065 {
  @Test
  public void testHashCode() {
    BulkWriteOptions a = new BulkWriteOptions()
      .setWriteOption(WriteOption.JOURNALED)
      .setOrdered(false);
    int hash = a.hashCode();

    a.setWriteOption(WriteOption.ACKNOWLEDGED);
    assertNotEquals(hash, a.hashCode());

    a.setWriteOption(WriteOption.JOURNALED);
    a.setOrdered(true);
    assertNotEquals(hash, a.hashCode());

    a.setWriteOption(WriteOption.JOURNALED);
    a.setOrdered(false);
    assertEquals(hash, a.hashCode());
  }

}