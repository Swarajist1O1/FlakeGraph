class DummyClass_106655 {
  @Test
  public void readOffset2() throws Exception {
    // mocks set-up
    AlluxioURI expectedPath = BASE_EXPECTED_URI.join("/foo/bar");
    setUpOpenMock(expectedPath);

    FileInStream fakeInStream = mock(FileInStream.class);
    when(fakeInStream.read(any(byte[].class),
        anyInt(), anyInt())).then((Answer<Integer>) invocationOnMock -> {
          byte[] myDest = (byte[]) invocationOnMock.getArguments()[0];
          for (byte i = 0; i < (int) invocationOnMock.getArgument(2); i++) {
            myDest[i] = i;
          }
          return myDest.length;
        });
    AtomicInteger callCounter = new AtomicInteger();
    when(fakeInStream.remaining()).then((Answer<Long>) invocationOnMock -> {
      if (callCounter.getAndIncrement() == 0) {
        return 4L;
      } else {
        return 3L;
      }
    });

    when(mFileSystem.openFile(expectedPath)).thenReturn(fakeInStream);
    mFileInfo.flags.set(O_RDONLY.intValue());

    // prepare something to read to it
    Runtime r = Runtime.getSystemRuntime();
    Pointer ptr = r.getMemoryManager().allocateTemporary(4, true);

    // actual test
    mFuseFs.open("/foo/bar", mFileInfo);

    mFuseFs.read("/foo/bar", ptr, 4, 4, mFileInfo);
    final byte[] dst = new byte[0];
    ptr.get(0, dst, 0, 0);
    final byte[] expected = new byte[0];

    assertArrayEquals("Source and dst data should be equal", expected, dst);
  }

}