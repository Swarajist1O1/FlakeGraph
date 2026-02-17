class DummyClass_30957 {
  @Test
  public void shouldEncodeDecodeNullValues() {
    // given
    final BrokerInfo brokerInfo = new BrokerInfo();

    // when
    encodeDecode(brokerInfo);

    // then
    assertThat(brokerInfo.getNodeId()).isEqualTo(BrokerInfoEncoder.nodeIdNullValue());
    assertThat(brokerInfo.getPartitionsCount())
        .isEqualTo(BrokerInfoEncoder.partitionsCountNullValue());
    assertThat(brokerInfo.getClusterSize()).isEqualTo(BrokerInfoEncoder.clusterSizeNullValue());
    assertThat(brokerInfo.getReplicationFactor())
        .isEqualTo(BrokerInfoEncoder.replicationFactorNullValue());
    assertThat(brokerInfo.getAddresses()).isEmpty();
    assertThat(brokerInfo.getPartitionRoles()).isEmpty();
    assertThat(brokerInfo.getPartitionHealthStatuses()).isEmpty();
  }

}