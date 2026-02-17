class DummyClass_380 {
  @Test
  public void testMkdirRecursiveWithNonExistingDirClear() throws IOException {
    // by default parent directories have -wx------ bits set
    testMkdirRecursiveWithNonExistingDir(BLANK_TEST_UMASK, BLANK_PERMISSIONS, 
        PARENT_PERMS_FOR_BLANK_PERMISSIONS);
  }

}