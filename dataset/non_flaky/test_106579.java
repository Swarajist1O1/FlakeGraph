class DummyClass_106579 {
  @Test
  public void versionParsing() {
    String versionStr = "Hadoop 2.7.2\n"
            + "Subversion https://git-wip-us.apache.org/repos/asf/hadoop.git "
            + "-r b165c4fe8a74265c792ce23f546c64604acf0e41\n"
            + "Compiled by jenkins on 2016-01-26T00:08Z\n"
            + "Compiled with protoc 2.5.0\n"
            + "From source with checksum d0fda26633fa762bff87ec759ebe689c\n"
            + "This command was run using "
            + "/tmp/hadoop/share/hadoop/common/hadoop-common-2.7.2.jar";

    HdfsVersionValidationTask task = new HdfsVersionValidationTask(sConf);
    String version = task.parseVersion(versionStr);
    assertEquals("2.7.2", version);
  }

}