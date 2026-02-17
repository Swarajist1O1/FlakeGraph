class DummyClass_320 {
  @Test
  public void testDeprecatedKeys() {
    NfsConfiguration conf = new NfsConfiguration();
    conf.setInt("nfs3.server.port", 998);
    assertTrue(conf.getInt(NfsConfigKeys.DFS_NFS_SERVER_PORT_KEY, 0) == 998);

    conf.setInt("nfs3.mountd.port", 999);
    assertTrue(conf.getInt(NfsConfigKeys.DFS_NFS_MOUNTD_PORT_KEY, 0) == 999);

    conf.set("dfs.nfs.exports.allowed.hosts", "host1");
    assertTrue(conf.get(CommonConfigurationKeys.NFS_EXPORTS_ALLOWED_HOSTS_KEY)
        .equals("host1"));

    conf.setInt("dfs.nfs.exports.cache.expirytime.millis", 1000);
    assertTrue(conf.getInt(
        Nfs3Constant.NFS_EXPORTS_CACHE_EXPIRYTIME_MILLIS_KEY, 0) == 1000);

    conf.setInt("hadoop.nfs.userupdate.milly", 10);
    assertTrue(conf.getInt(IdMappingConstant.USERGROUPID_UPDATE_MILLIS_KEY, 0) == 10);

    conf.set("dfs.nfs3.dump.dir", "/nfs/tmp");
    assertTrue(conf.get(NfsConfigKeys.DFS_NFS_FILE_DUMP_DIR_KEY).equals(
        "/nfs/tmp"));

    conf.setBoolean("dfs.nfs3.enableDump", false);
    assertTrue(conf.getBoolean(NfsConfigKeys.DFS_NFS_FILE_DUMP_KEY, true) == false);

    conf.setInt("dfs.nfs3.max.open.files", 500);
    assertTrue(conf.getInt(NfsConfigKeys.DFS_NFS_MAX_OPEN_FILES_KEY, 0) == 500);

    conf.setInt("dfs.nfs3.stream.timeout", 6000);
    assertTrue(conf.getInt(NfsConfigKeys.DFS_NFS_STREAM_TIMEOUT_KEY, 0) == 6000);

    conf.set("dfs.nfs3.export.point", "/dir1");
    assertTrue(conf.get(NfsConfigKeys.DFS_NFS_EXPORT_POINT_KEY).equals("/dir1"));
  }

}