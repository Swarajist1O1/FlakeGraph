class DummyClass_57200 {
  @Test
  public void testStart() throws Exception {

    OMMetadataManager omMetadataManager = getOMMetadataManager();

    //Take checkpoint of the above OM DB.
    DBCheckpoint checkpoint = omMetadataManager.getStore()
        .getCheckpoint(true);
    File snapshotFile = new File(
        checkpoint.getCheckpointLocation().getParent() + "/" +
            "om.snapshot.db_" + System.currentTimeMillis());
    checkpoint.getCheckpointLocation().toFile().renameTo(snapshotFile);

    //Create new Recon OM Metadata manager instance.
    File reconOmDbDir = temporaryFolder.newFolder();
    OzoneConfiguration configuration = new OzoneConfiguration();
    configuration.set(OZONE_RECON_OM_SNAPSHOT_DB_DIR, reconOmDbDir
        .getAbsolutePath());
    FileUtils.copyDirectory(snapshotFile.getParentFile(), reconOmDbDir);

    ReconOMMetadataManager reconOMMetadataManager =
        new ReconOmMetadataManagerImpl(configuration, new ReconUtils());
    reconOMMetadataManager.start(configuration);

    Assert.assertNotNull(reconOMMetadataManager.getBucketTable());
    Assert.assertNotNull(reconOMMetadataManager.getVolumeTable()
        .get("/sampleVol"));
    Assert.assertNotNull(reconOMMetadataManager.getBucketTable()
        .get("/sampleVol/bucketOne"));
    Assert.assertNotNull(reconOMMetadataManager.getKeyTable(getBucketLayout())
        .get("/sampleVol/bucketOne/key_one"));
    Assert.assertNotNull(reconOMMetadataManager.getKeyTable(getBucketLayout())
        .get("/sampleVol/bucketOne/key_two"));
  }

}