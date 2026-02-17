class DummyClass_358 {
  @Test
  public void testCreate() throws Exception {
    Configuration conf = new HdfsConfiguration();
    conf.setBoolean(DFSConfigKeys.DFS_PERMISSIONS_ENABLED_KEY, true);
    conf.set(FsPermission.UMASK_LABEL, "000");
    MiniDFSCluster cluster = null;
    FileSystem fs = null;

    try {
      cluster = new MiniDFSCluster.Builder(conf).numDataNodes(3).build();
      cluster.waitActive();
      fs = FileSystem.get(conf);
      FsPermission rootPerm = checkPermission(fs, "/", null);
      FsPermission inheritPerm = FsPermission.createImmutable(
          (short)(rootPerm.toShort() | 0300));

      FsPermission dirPerm = new FsPermission((short)0777);
      fs.mkdirs(new Path("/a1/a2/a3"), dirPerm);
      checkPermission(fs, "/a1", dirPerm);
      checkPermission(fs, "/a1/a2", dirPerm);
      checkPermission(fs, "/a1/a2/a3", dirPerm);

      dirPerm = new FsPermission((short)0123);
      FsPermission permission = FsPermission.createImmutable(
        (short)(dirPerm.toShort() | 0300));
      fs.mkdirs(new Path("/aa/1/aa/2/aa/3"), dirPerm);
      checkPermission(fs, "/aa/1", permission);
      checkPermission(fs, "/aa/1/aa/2", permission);
      checkPermission(fs, "/aa/1/aa/2/aa/3", dirPerm);

      FsPermission filePerm = new FsPermission((short)0444);
      Path p = new Path("/b1/b2/b3.txt");
      FSDataOutputStream out = fs.create(p, filePerm,
          true, conf.getInt(CommonConfigurationKeys.IO_FILE_BUFFER_SIZE_KEY, 4096),
          fs.getDefaultReplication(p), fs.getDefaultBlockSize(p), null);
      out.write(123);
      out.close();
      checkPermission(fs, "/b1", inheritPerm);
      checkPermission(fs, "/b1/b2", inheritPerm);
      checkPermission(fs, "/b1/b2/b3.txt", filePerm);
      
      conf.set(FsPermission.UMASK_LABEL, "022");
      permission = 
        FsPermission.createImmutable((short)0666);
      FileSystem.mkdirs(fs, new Path("/c1"), new FsPermission(permission));
      FileSystem.create(fs, new Path("/c1/c2.txt"),
          new FsPermission(permission));
      checkPermission(fs, "/c1", permission);
      checkPermission(fs, "/c1/c2.txt", permission);
    } finally {
      try {
        if(fs != null) fs.close();
      } catch(Exception e) {
        LOG.error(StringUtils.stringifyException(e));
      }
      try {
        if(cluster != null) cluster.shutdown();
      } catch(Exception e) {
        LOG.error(StringUtils.stringifyException(e));
      }
    }
  }

}