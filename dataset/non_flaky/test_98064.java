class DummyClass_98064 {
  @Test
  public void testEquals() {
    BulkWriteOptions a = new BulkWriteOptions();
    BulkWriteOptions b = new BulkWriteOptions();
    assertEquals(a, b);

    a.setWriteOption(WriteOption.ACKNOWLEDGED);
    b.setWriteOption(WriteOption.JOURNALED);
    assertNotEquals(a, b);

    a.setWriteOption(WriteOption.MAJORITY);
    b.setWriteOption(WriteOption.MAJORITY);
    assertEquals(a, b);

    a.setOrdered(true);
    b.setOrdered(false);
    assertNotEquals(a, b);

    assertNotEquals(a, null);
  }

}