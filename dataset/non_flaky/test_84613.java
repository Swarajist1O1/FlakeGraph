class DummyClass_84613 {
    @Test
    public void testGetEphemeralsSync() throws KeeperException, InterruptedException {
        List<String> actual = zk.getEphemerals();
        assertEquals(actual.size(), expected.length, "Expected ephemeral count for allPaths");
        for (int i = 0; i < expected.length; i++) {
            String path = expected[i];
            assertTrue(actual.contains(path), String.format("Path=%s exists in get All Ephemerals list ", path));
        }
    }

}