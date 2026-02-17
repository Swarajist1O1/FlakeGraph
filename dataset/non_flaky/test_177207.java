class DummyClass_177207 {
    @Test(expected = NotAllMetaRegionsOnlineException.class)
    public void testGuavaConflict() throws Exception {
        // Make sure Armeria is available in the class path.
        assertThat(Version.getAll(Server.class.getClassLoader())).isNotNull();
        // Make sure newer Guava is available in the class path.
        assertThat(Stopwatch.class.getDeclaredConstructor().getModifiers()).is(new Condition<>(
                value -> !Modifier.isPublic(value),
                "Recent Guava Stopwatch should have non-public default constructor."));

        final MetaTableLocator locator = new MetaTableLocator();
        final ZooKeeperWatcher zkw = mock(ZooKeeperWatcher.class);
        final RecoverableZooKeeper zk = mock(RecoverableZooKeeper.class);
        when(zkw.getRecoverableZooKeeper()).thenReturn(zk);
        when(zk.exists(any(), any())).thenReturn(new Stat(0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0));

        locator.waitMetaRegionLocation(zkw, 100);
    }

}