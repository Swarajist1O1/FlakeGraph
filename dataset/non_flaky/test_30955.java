class DummyClass_30955 {
  @Test
  public void shouldEncodeDecodeBrokerInfo() {
    // given
    final int nodeId = 123;
    final int partitionsCount = 345;
    final int clusterSize = 567;
    final int replicationFactor = 789;
    final Map<DirectBuffer, DirectBuffer> addresses = new HashMap<>();
    addresses.put(wrapString("foo"), wrapString("192.159.12.1:23"));
    addresses.put(wrapString("bar"), wrapString("zeebe-0.cluster.loc:12312"));
    final Map<Integer, PartitionRole> partitionRoles = new HashMap<>();
    partitionRoles.put(1, PartitionRole.FOLLOWER);
    partitionRoles.put(2, PartitionRole.LEADER);
    partitionRoles.put(231, PartitionRole.FOLLOWER);
    final Map<Integer, PartitionHealthStatus> partitionHealthStatuses = new HashMap<>();
    partitionHealthStatuses.put(1, PartitionHealthStatus.HEALTHY);
    partitionHealthStatuses.put(2, PartitionHealthStatus.UNHEALTHY);
    partitionHealthStatuses.put(123, PartitionHealthStatus.HEALTHY);

    final BrokerInfo brokerInfo =
        new BrokerInfo()
            .setNodeId(nodeId)
            .setPartitionsCount(partitionsCount)
            .setClusterSize(clusterSize)
            .setReplicationFactor(replicationFactor);

    addresses.forEach(brokerInfo::addAddress);
    partitionRoles.forEach(brokerInfo::addPartitionRole);
    partitionHealthStatuses.forEach(brokerInfo::addPartitionHealth);

    // when
    encodeDecode(brokerInfo);

    // then
    assertThat(brokerInfo.getNodeId()).isEqualTo(nodeId);
    assertThat(brokerInfo.getPartitionsCount()).isEqualTo(partitionsCount);
    assertThat(brokerInfo.getClusterSize()).isEqualTo(clusterSize);
    assertThat(brokerInfo.getReplicationFactor()).isEqualTo(replicationFactor);
    assertThat(brokerInfo.getAddresses()).containsAllEntriesOf(addresses);
    assertThat(brokerInfo.getPartitionRoles()).containsAllEntriesOf(partitionRoles);
    assertThat(brokerInfo.getPartitionHealthStatuses())
        .containsAllEntriesOf(partitionHealthStatuses);
  }

}