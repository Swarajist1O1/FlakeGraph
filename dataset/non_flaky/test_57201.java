class DummyClass_57201 {
  @Test
  public void testUpdateOmDB() throws Exception {

    OMMetadataManager omMetadataManager = getOMMetadataManager();
    //Make sure OM Metadata reflects the keys that were inserted.
    Assert.assertNotNull(omMetadataManager.getKeyTable(getBucketLayout())
        .get("/sampleVol/bucketOne/key_one"));
    Assert.assertNotNull(omMetadataManager.getKeyTable(getBucketLayout())
        .get("/sampleVol/bucketOne/key_two"));

    //Take checkpoint of OM DB.
    DBCheckpoint checkpoint = omMetadataManager.getStore()
        .getCheckpoint(true);
    Assert.assertNotNull(checkpoint.getCheckpointLocation());

    //Create new Recon OM Metadata manager instance.
    File reconOmDbDir = temporaryFolder.newFolder();
    OzoneConfiguration configuration = new OzoneConfiguration();
    configuration.set(OZONE_RECON_OM_SNAPSHOT_DB_DIR, reconOmDbDir
        .getAbsolutePath());
    ReconOMMetadataManager reconOMMetadataManager =
        new ReconOmMetadataManagerImpl(configuration, new ReconUtils());
    reconOMMetadataManager.start(configuration);

    //Before accepting a snapshot, the metadata should have null tables.
    Assert.assertNull(reconOMMetadataManager.getBucketTable());

    //Update Recon OM DB with the OM DB checkpoint location.
    reconOMMetadataManager.updateOmDB(
        checkpoint.getCheckpointLocation().toFile());

    //Now, the tables should have been initialized.
    Assert.assertNotNull(reconOMMetadataManager.getBucketTable());

    // Check volume and bucket entries.
    Assert.assertNotNull(reconOMMetadataManager.getVolumeTable()
        .get("/sampleVol"));
    Assert.assertNotNull(reconOMMetadataManager.getBucketTable()
        .get("/sampleVol/bucketOne"));

    //Verify Keys inserted in OM DB are available in Recon OM DB.
    Assert.assertNotNull(reconOMMetadataManager.getKeyTable(getBucketLayout())
        .get("/sampleVol/bucketOne/key_one"));
    Assert.assertNotNull(reconOMMetadataManager.getKeyTable(getBucketLayout())
        .get("/sampleVol/bucketOne/key_two"));

  }

}