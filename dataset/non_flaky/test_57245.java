class DummyClass_57245 {
  @Test
  public void testProcessICR() throws IOException, NodeNotFoundException {

    ContainerID containerID = ContainerID.valueOf(100L);
    DatanodeDetails datanodeDetails = randomDatanodeDetails();
    IncrementalContainerReportFromDatanode reportMock =
        mock(IncrementalContainerReportFromDatanode.class);
    when(reportMock.getDatanodeDetails()).thenReturn(datanodeDetails);
    IncrementalContainerReportProto containerReport =
        getIncrementalContainerReportProto(containerID,
            State.OPEN,
            datanodeDetails.getUuidString());
    when(reportMock.getReport()).thenReturn(containerReport);

    final String path =
        GenericTestUtils.getTempPath(UUID.randomUUID().toString());
    Path scmPath = Paths.get(path, "scm-meta");
    final OzoneConfiguration conf = new OzoneConfiguration();
    conf.set(HddsConfigKeys.OZONE_METADATA_DIRS, scmPath.toString());
    NetworkTopology clusterMap = new NetworkTopologyImpl(conf);
    EventQueue eventQueue = new EventQueue();
    SCMStorageConfig storageConfig = new SCMStorageConfig(conf);
    this.versionManager =
        Mockito.mock(HDDSLayoutVersionManager.class);
    Mockito.when(versionManager.getMetadataLayoutVersion())
        .thenReturn(maxLayoutVersion());
    Mockito.when(versionManager.getSoftwareLayoutVersion())
        .thenReturn(maxLayoutVersion());

    NodeManager nodeManager = new SCMNodeManager(conf, storageConfig,
        eventQueue, clusterMap, SCMContext.emptyContext(), versionManager);

    nodeManager.register(datanodeDetails, null, null);

    ReconContainerManager containerManager = getContainerManager();
    ReconIncrementalContainerReportHandler reconIcr =
        new ReconIncrementalContainerReportHandler(nodeManager,
            containerManager, SCMContext.emptyContext());
    EventPublisher eventPublisherMock = mock(EventPublisher.class);

    reconIcr.onMessage(reportMock, eventPublisherMock);
    nodeManager.addContainer(datanodeDetails, containerID);
    assertTrue(containerManager.containerExist(containerID));
    assertEquals(1, containerManager.getContainerReplicas(containerID).size());
    assertEquals(OPEN, containerManager.getContainer(containerID).getState());
  }

}