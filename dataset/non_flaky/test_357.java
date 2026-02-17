class DummyClass_357 {
  @Test
  public void testBackwardCompatibility() {
    // Test 1 - old configuration key with decimal 
    // umask value should be handled when set using 
    // FSPermission.setUMask() API
    FsPermission perm = new FsPermission((short)18);
    Configuration conf = new Configuration();
    FsPermission.setUMask(conf, perm);
    assertEquals(18, FsPermission.getUMask(conf).toShort());

    // Test 2 - new configuration key is handled
    conf = new Configuration();
    conf.set(FsPermission.UMASK_LABEL, "022");
    assertEquals(18, FsPermission.getUMask(conf).toShort());

    // Test 3 - equivalent valid umask
    conf = new Configuration();
    conf.set(FsPermission.UMASK_LABEL, "0022");
    assertEquals(18, FsPermission.getUMask(conf).toShort());

    // Test 4 - invalid umask
    conf = new Configuration();
    conf.set(FsPermission.UMASK_LABEL, "1222");
    try {
      FsPermission.getUMask(conf);
      fail("expect IllegalArgumentException happen");
    } catch (IllegalArgumentException e) {
     //pass, exception successfully trigger
    }

    // Test 5 - invalid umask
    conf = new Configuration();
    conf.set(FsPermission.UMASK_LABEL, "01222");
    try {
      FsPermission.getUMask(conf);
      fail("expect IllegalArgumentException happen");
    } catch (IllegalArgumentException e) {
     //pass, exception successfully trigger
    }
  }

}