class DummyClass_359 {
  @Test
  public void testFilePermission() throws Exception {
    final Configuration conf = new HdfsConfiguration();
    conf.setBoolean(DFSConfigKeys.DFS_PERMISSIONS_ENABLED_KEY, true);
    MiniDFSCluster cluster = new MiniDFSCluster.Builder(conf).numDataNodes(3).build();
    cluster.waitActive();

    try {
      nnfs = FileSystem.get(conf);
      // test permissions on files that do not exist
      assertFalse(nnfs.exists(CHILD_FILE1));
      try {
        nnfs.setPermission(CHILD_FILE1, new FsPermission((short)0777));
        assertTrue(false);
      }
      catch(java.io.FileNotFoundException e) {
        LOG.info("GOOD: got " + e);
      }
      
      // make sure nn can take user specified permission (with default fs
      // permission umask applied)
      FSDataOutputStream out = nnfs.create(CHILD_FILE1, new FsPermission(
          (short) 0777), true, 1024, (short) 1, 1024, null);
      FileStatus status = nnfs.getFileStatus(CHILD_FILE1);
      // FS_PERMISSIONS_UMASK_DEFAULT is 0022
      assertTrue(status.getPermission().toString().equals("rwxr-xr-x"));
      nnfs.delete(CHILD_FILE1, false);
      
      // following dir/file creations are legal
      nnfs.mkdirs(CHILD_DIR1);
      status = nnfs.getFileStatus(CHILD_DIR1);
      assertThat("Expect 755 = 777 (default dir) - 022 (default umask)",
          status.getPermission().toString(), is("rwxr-xr-x"));
      out = nnfs.create(CHILD_FILE1);
      status = nnfs.getFileStatus(CHILD_FILE1);
      assertTrue(status.getPermission().toString().equals("rw-r--r--"));
      byte data[] = new byte[FILE_LEN];
      RAN.nextBytes(data);
      out.write(data);
      out.close();
      nnfs.setPermission(CHILD_FILE1, new FsPermission("700"));
      status = nnfs.getFileStatus(CHILD_FILE1);
      assertTrue(status.getPermission().toString().equals("rwx------"));

      // mkdirs with null permission
      nnfs.mkdirs(CHILD_DIR3, null);
      status = nnfs.getFileStatus(CHILD_DIR3);
      assertThat("Expect 755 = 777 (default dir) - 022 (default umask)",
          status.getPermission().toString(), is("rwxr-xr-x"));

      // following read is legal
      byte dataIn[] = new byte[FILE_LEN];
      FSDataInputStream fin = nnfs.open(CHILD_FILE1);
      int bytesRead = fin.read(dataIn);
      assertTrue(bytesRead == FILE_LEN);
      for(int i=0; i<FILE_LEN; i++) {
        assertEquals(data[i], dataIn[i]);
      }

      // test execution bit support for files
      nnfs.setPermission(CHILD_FILE1, new FsPermission("755"));
      status = nnfs.getFileStatus(CHILD_FILE1);
      assertTrue(status.getPermission().toString().equals("rwxr-xr-x"));
      nnfs.setPermission(CHILD_FILE1, new FsPermission("744"));
      status = nnfs.getFileStatus(CHILD_FILE1);
      assertTrue(status.getPermission().toString().equals("rwxr--r--"));
      nnfs.setPermission(CHILD_FILE1, new FsPermission("700"));
      
      ////////////////////////////////////////////////////////////////
      // test illegal file/dir creation
      UserGroupInformation userGroupInfo = 
        UserGroupInformation.createUserForTesting(USER_NAME, GROUP_NAMES );
      
      userfs = DFSTestUtil.getFileSystemAs(userGroupInfo, conf);

      // make sure mkdir of a existing directory that is not owned by 
      // this user does not throw an exception.
      userfs.mkdirs(CHILD_DIR1);
      
      // illegal mkdir
      assertTrue(!canMkdirs(userfs, CHILD_DIR2));

      // illegal file creation
      assertTrue(!canCreate(userfs, CHILD_FILE2));

      // illegal file open
      assertTrue(!canOpen(userfs, CHILD_FILE1));

      nnfs.setPermission(ROOT_PATH, new FsPermission((short)0755));
      nnfs.setPermission(CHILD_DIR1, new FsPermission("777"));
      nnfs.setPermission(new Path("/"), new FsPermission((short)0777));
      final Path RENAME_PATH = new Path("/foo/bar");
      userfs.mkdirs(RENAME_PATH);
      assertTrue(canRename(userfs, RENAME_PATH, CHILD_DIR1));
      // test permissions on files that do not exist
      assertFalse(userfs.exists(CHILD_FILE3));
      try {
        userfs.setPermission(CHILD_FILE3, new FsPermission((short) 0777));
        fail("setPermission should fail for non-exist file");
      } catch (java.io.FileNotFoundException ignored) {
      }

      // Make sure any user can create file in root.
      nnfs.setPermission(ROOT_PATH, new FsPermission("777"));

      testSuperCanChangeOwnerGroup();
      testNonSuperCanChangeToOwnGroup();
      testNonSuperCannotChangeToOtherGroup();
      testNonSuperCannotChangeGroupForOtherFile();
      testNonSuperCannotChangeGroupForNonExistentFile();
      testNonSuperCannotChangeOwner();
      testNonSuperCannotChangeOwnerForOtherFile();
      testNonSuperCannotChangeOwnerForNonExistentFile();
    } finally {
      cluster.shutdown();
    }
  }

}