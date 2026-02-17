class DummyClass_374 {
  @Test
  public void testAccess() throws Exception {
    fs.setPermission(target, new FsPermission((short) 0002));
    fs.setAcl(target, Arrays.asList(
        aclEntry(ACCESS, USER, ALL),
        aclEntry(ACCESS, GROUP, NONE),
        aclEntry(ACCESS, USER, user.getShortUserName(), WRITE),
        aclEntry(ACCESS, OTHER, WRITE)));
    FileContext myfc = user.doAs(new PrivilegedExceptionAction<FileContext>() {
      @Override
      public FileContext run() throws IOException {
        return FileContext.getFileContext(conf);
      }

}