class DummyClass_84618 {
    @Test
    public void testGetEphemeralsErrors() throws KeeperException {
        try {
            zk.getEphemerals(null, null, null);
            fail("Should have thrown a IllegalArgumentException for a null prefixPath");
        } catch (IllegalArgumentException e) {
            //pass
        }

        try {
            zk.getEphemerals("no leading slash", null, null);
            fail("Should have thrown a IllegalArgumentException " + "for a prefix with no leading slash");
        } catch (IllegalArgumentException e) {
            //pass
        }
    }

}