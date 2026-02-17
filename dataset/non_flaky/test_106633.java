class DummyClass_106633 {
  @Test
  public void write() throws Exception {
    FileOutStream fos = mock(FileOutStream.class);
    AlluxioURI anyURI = any();
    CreateFilePOptions options = any();
    when(mFileSystem.createFile(anyURI, options)).thenReturn(fos);

    // open a file
    mFileInfo.flags.set(O_WRONLY.intValue());
    mFuseFs.create("/foo/bar", 0, mFileInfo);

    // prepare something to write into it
    ByteBuffer ptr = ByteBuffer.allocateDirect(4);
    byte[] expected = {42, -128, 1, 3};
    ptr.put(expected, 0, 4);
    ptr.flip();

    mFuseFs.write("/foo/bar", ptr, 4, 0, mFileInfo);
    verify(fos).write(expected);

    // the second write is no-op because the writes must be sequential and overwriting is supported
    mFuseFs.write("/foo/bar", ptr, 4, 0, mFileInfo);
    verify(fos, times(1)).write(expected);
  }

}