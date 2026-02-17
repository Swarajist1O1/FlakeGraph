class DummyClass_91380 {
    @Test
    public void testCTor() {
        NodeID nodeID = new NodeID(new byte[]{1,2,3,4,5,6});
        RemoteDccProxy t = new RemoteDccProxy(nodeID);
        Assert.assertNotNull("exists",t);
    }

}