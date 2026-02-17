class DummyClass_106627 {
  @Test
  public void read() throws Exception {
    // mocks set-up
    AlluxioURI expectedPath = BASE_EXPECTED_URI.join("/foo/bar");
    setUpOpenMock(expectedPath);

    FileInStream fakeInStream = mock(FileInStream.class);
    when(fakeInStream.read(any(byte[].class),
        anyInt(), anyInt())).then((Answer<Integer>) invocationOnMock -> {
          byte[] myDest = (byte[]) invocationOnMock.getArguments()[0];
          for (byte i = 0; i < 4; i++) {
            myDest[i] = i;
          }
          return 4;
        });

    when(mFileSystem.openFile(expectedPath)).thenReturn(fakeInStream);
    mFileInfo.flags.set(O_RDONLY.intValue());

    // prepare something to read to it
    ByteBuffer ptr = ByteBuffer.allocateDirect(4);
    assertEquals(4, ptr.limit());

    // actual test
    mFuseFs.open("/foo/bar", mFileInfo);

    mFuseFs.read("/foo/bar", ptr, 4, 0, mFileInfo);
    ptr.flip();
    final byte[] dst = new byte[4];
    ptr.get(dst, 0, 4);
    final byte[] expected = new byte[] {0, 1, 2, 3};

    assertArrayEquals("Source and dst data should be equal", expected, dst);
  }

}