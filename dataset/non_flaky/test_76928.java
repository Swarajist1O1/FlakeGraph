class DummyClass_76928 {
  @Test
  public void test() {
    WriterBuffer wb = new WriterBuffer(32);
    assertEquals(0, wb.getMemoryUsed());
    assertEquals(0, wb.getDataLength());

    serializeData("key", "value");
    // size of serialized kv is 12
    wb.addRecord(serializedData, serializedDataLength);
    assertEquals(32, wb.getMemoryUsed());
    assertEquals(12, wb.getDataLength());
    wb.addRecord(serializedData, serializedDataLength);
    assertEquals(32, wb.getMemoryUsed());
    // case: data size < output buffer size, when getData(), [] + buffer with 24b = 24b
    assertEquals(24, wb.getData().length);
    wb.addRecord(serializedData, serializedDataLength);
    // case: data size > output buffer size, when getData(), [1 buffer] + buffer with 12 = 36b
    assertEquals(36, wb.getData().length);
    assertEquals(64, wb.getMemoryUsed());
    wb.addRecord(serializedData, serializedDataLength);
    wb.addRecord(serializedData, serializedDataLength);
    // case: data size > output buffer size, when getData(), 2 buffer + output with 12b = 60b
    assertEquals(60, wb.getData().length);
    assertEquals(96, wb.getMemoryUsed());

    wb = new WriterBuffer(32);

    serializeData("key1111111111111111111111111111", "value222222222222222222222222222");
    wb.addRecord(serializedData, serializedDataLength);
    assertEquals(67, wb.getMemoryUsed());
    assertEquals(67, wb.getDataLength());

    serializeData("key", "value");
    wb.addRecord(serializedData, serializedDataLength);
    // 67 + 32
    assertEquals(99, wb.getMemoryUsed());
    // 67 + 12
    assertEquals(79, wb.getDataLength());
    assertEquals(79, wb.getData().length);

    wb.addRecord(serializedData, serializedDataLength);
    assertEquals(99, wb.getMemoryUsed());
    assertEquals(91, wb.getDataLength());
    assertEquals(91, wb.getData().length);
  }

}