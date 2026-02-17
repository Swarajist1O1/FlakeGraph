class DummyClass_76968 {
  @Test
  public void testByteString() {

    assertEquals(10 * PB, UnitConverter.byteStringAs("10PB", ByteUnit.BYTE));
    assertEquals(10 * PB, UnitConverter.byteStringAs("10pb", ByteUnit.BYTE));
    assertEquals(10 * PB, UnitConverter.byteStringAs("10pB", ByteUnit.BYTE));
    assertEquals(10 * PB, UnitConverter.byteStringAs("10p", ByteUnit.BYTE));
    assertEquals(10 * PB, UnitConverter.byteStringAs("10P", ByteUnit.BYTE));

    assertEquals(10 * TB, UnitConverter.byteStringAs("10TB", ByteUnit.BYTE));
    assertEquals(10 * TB, UnitConverter.byteStringAs("10tb", ByteUnit.BYTE));
    assertEquals(10 * TB, UnitConverter.byteStringAs("10tB", ByteUnit.BYTE));
    assertEquals(10 * TB, UnitConverter.byteStringAs("10T", ByteUnit.BYTE));
    assertEquals(10 * TB, UnitConverter.byteStringAs("10t", ByteUnit.BYTE));

    assertEquals(10 * GB, UnitConverter.byteStringAs("10GB", ByteUnit.BYTE));
    assertEquals(10 * GB, UnitConverter.byteStringAs("10gb", ByteUnit.BYTE));
    assertEquals(10 * GB, UnitConverter.byteStringAs("10gB", ByteUnit.BYTE));

    assertEquals(10 * MB, UnitConverter.byteStringAs("10MB", ByteUnit.BYTE));
    assertEquals(10 * MB, UnitConverter.byteStringAs("10mb", ByteUnit.BYTE));
    assertEquals(10 * MB, UnitConverter.byteStringAs("10mB", ByteUnit.BYTE));
    assertEquals(10 * MB, UnitConverter.byteStringAs("10M", ByteUnit.BYTE));
    assertEquals(10 * MB, UnitConverter.byteStringAs("10m", ByteUnit.BYTE));

    assertEquals(10 * KB, UnitConverter.byteStringAs("10KB", ByteUnit.BYTE));
    assertEquals(10 * KB, UnitConverter.byteStringAs("10kb", ByteUnit.BYTE));
    assertEquals(10 * KB, UnitConverter.byteStringAs("10Kb", ByteUnit.BYTE));
    assertEquals(10 * KB, UnitConverter.byteStringAs("10K", ByteUnit.BYTE));
    assertEquals(10 * KB, UnitConverter.byteStringAs("10k", ByteUnit.BYTE));

    assertEquals(1111, UnitConverter.byteStringAs("1111", ByteUnit.BYTE));
  }

}