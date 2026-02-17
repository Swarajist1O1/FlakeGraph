class DummyClass_84636 {
    @Test
    public void testGetAllChildrenNumberSync() throws KeeperException, InterruptedException {
        //a bad case
        try {
            zk.getAllChildrenNumber(null);
            fail("the path for getAllChildrenNumber must not be null.");
        } catch (IllegalArgumentException e) {
            //expected
        }

        assertEquals(EPHEMERAL_CNT, zk.getAllChildrenNumber(BASE + "/0"));
        assertEquals(0, zk.getAllChildrenNumber(BASE + "/0/ephem0"));
        assertEquals(0, zk.getAllChildrenNumber(BASE_EXT));
        assertEquals(PERSISTENT_CNT + PERSISTENT_CNT * EPHEMERAL_CNT, zk.getAllChildrenNumber(BASE));
        // 6(EPHEMERAL) + 2(PERSISTENT) + 3("/zookeeper,/zookeeper/quota,/zookeeper/config") + 1(BASE_EXT) + 1(BASE) = 13
        assertEquals(13, zk.getAllChildrenNumber("/"));
    }

}