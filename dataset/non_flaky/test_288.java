class DummyClass_288 {
  @Test
  public void testAlterWriteRequest() throws IOException {
    int len = 20;
    byte[] data = new byte[len];
    ByteBuffer buffer = ByteBuffer.wrap(data);

    for (int i = 0; i < len; i++) {
      buffer.put((byte) i);
    }
    buffer.flip();
    int originalCount = buffer.array().length;
    WRITE3Request request = new WRITE3Request(new FileHandle(), 0, data.length,
        WriteStableHow.UNSTABLE, buffer);

    WriteCtx writeCtx1 = new WriteCtx(request.getHandle(), request.getOffset(),
        request.getCount(), WriteCtx.INVALID_ORIGINAL_COUNT,
        request.getStableHow(), request.getData(), null, 1, false,
        WriteCtx.DataState.NO_DUMP);

    Assert.assertTrue(writeCtx1.getData().array().length == originalCount);

    // Now change the write request
    OpenFileCtx.alterWriteRequest(request, 12);

    WriteCtx writeCtx2 = new WriteCtx(request.getHandle(), request.getOffset(),
        request.getCount(), originalCount, request.getStableHow(),
        request.getData(), null, 2, false, WriteCtx.DataState.NO_DUMP);
    ByteBuffer appendedData = writeCtx2.getData();

    int position = appendedData.position();
    int limit = appendedData.limit();
    Assert.assertTrue(position == 12);
    Assert.assertTrue(limit - position == 8);
    Assert.assertTrue(appendedData.get(position) == (byte) 12);
    Assert.assertTrue(appendedData.get(position + 1) == (byte) 13);
    Assert.assertTrue(appendedData.get(position + 2) == (byte) 14);
    Assert.assertTrue(appendedData.get(position + 7) == (byte) 19);

    // Test current file write offset is at boundaries
    buffer.position(0);
    request = new WRITE3Request(new FileHandle(), 0, data.length,
        WriteStableHow.UNSTABLE, buffer);
    OpenFileCtx.alterWriteRequest(request, 1);
    WriteCtx writeCtx3 = new WriteCtx(request.getHandle(), request.getOffset(),
        request.getCount(), originalCount, request.getStableHow(),
        request.getData(), null, 2, false, WriteCtx.DataState.NO_DUMP);
    appendedData = writeCtx3.getData();
    position = appendedData.position();
    limit = appendedData.limit();
    Assert.assertTrue(position == 1);
    Assert.assertTrue(limit - position == 19);
    Assert.assertTrue(appendedData.get(position) == (byte) 1);
    Assert.assertTrue(appendedData.get(position + 18) == (byte) 19);

    // Reset buffer position before test another boundary
    buffer.position(0);
    request = new WRITE3Request(new FileHandle(), 0, data.length,
        WriteStableHow.UNSTABLE, buffer);
    OpenFileCtx.alterWriteRequest(request, 19);
    WriteCtx writeCtx4 = new WriteCtx(request.getHandle(), request.getOffset(),
        request.getCount(), originalCount, request.getStableHow(),
        request.getData(), null, 2, false, WriteCtx.DataState.NO_DUMP);
    appendedData = writeCtx4.getData();
    position = appendedData.position();
    limit = appendedData.limit();
    Assert.assertTrue(position == 19);
    Assert.assertTrue(limit - position == 1);
    Assert.assertTrue(appendedData.get(position) == (byte) 19);
  }

}