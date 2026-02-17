class DummyClass_106580 {
  @Test
  public void cdhVersionParsing() {
    String versionStr = "Hadoop 2.6.0-cdh5.16.2\n"
            + "Subversion http://github.com/cloudera/hadoop -r "
            + "4f94d60caa4cbb9af0709a2fd96dc3861af9cf20\n"
            + "Compiled by jenkins on 2019-06-03T10:41Z\n"
            + "Compiled with protoc 2.5.0\n"
            + "From source with checksum 79b9b24a29c6358b53597c3b49575e37\n"
            + "This command was run using /usr/lib/hadoop/hadoop-common-2.6.0-cdh5.16.2.jar";

    HdfsVersionValidationTask task = new HdfsVersionValidationTask(sConf);
    String version = task.parseVersion(versionStr);
    assertEquals("cdh5.16.2", version);
  }

}