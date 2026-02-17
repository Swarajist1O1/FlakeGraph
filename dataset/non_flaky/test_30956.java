class DummyClass_30956 {
  @Test
  public void shouldEncodeDecodeBrokerInfoWithEmptyMaps() {
    // given
    final int nodeId = 123;
    final int partitionsCount = 345;
    final int clusterSize = 567;
    final int replicationFactor = 789;

    final BrokerInfo brokerInfo =
        new BrokerInfo()
            .setNodeId(nodeId)
            .setPartitionsCount(partitionsCount)
            .setClusterSize(clusterSize)
            .setReplicationFactor(replicationFactor);

    // when
    encodeDecode(brokerInfo);

    // then
    assertThat(brokerInfo.getNodeId()).isEqualTo(nodeId);
    assertThat(brokerInfo.getPartitionsCount()).isEqualTo(partitionsCount);
    assertThat(brokerInfo.getClusterSize()).isEqualTo(clusterSize);
    assertThat(brokerInfo.getReplicationFactor()).isEqualTo(replicationFactor);
    assertThat(brokerInfo.getAddresses()).isEmpty();
    assertThat(brokerInfo.getPartitionRoles()).isEmpty();
    assertThat(brokerInfo.getPartitionHealthStatuses()).isEmpty();
  }

}