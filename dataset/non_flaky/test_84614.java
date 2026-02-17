class DummyClass_84614 {
    @Test
    public void testGetEphemeralsSyncByPath() throws KeeperException, InterruptedException {
        final String prefixPath = BASE + 0;
        List<String> actual = zk.getEphemerals(prefixPath);
        assertEquals(actual.size(), EPHEMERAL_CNT, "Expected ephemeral count for allPaths");
        for (int i = 0; i < EPHEMERAL_CNT; i++) {
            String path = expected[i];
            assertTrue(actual.contains(path), String.format("Path=%s exists in getEphemerals(%s) list ", path, prefixPath));
        }
    }

}