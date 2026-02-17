class DummyClass_99719 {
    @Test
    public void defaultsToReplicationFactorOfOne() throws Exception
    {
        OptionReplication defaults = new OptionReplication();
        assertEquals(ImmutableMap.of("replication_factor", "1"), defaults.getOptions());
    }

}