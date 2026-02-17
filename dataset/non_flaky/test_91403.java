class DummyClass_91403 {
    @Test
    public void testCTor() {
        NodeID nodeID = new NodeID(new byte[]{1,2,3,4,5,6});
        CanInterface t = new CanInterface(nodeID, new CanFrameListenerScaffold() );
        Assert.assertNotNull("exists",t);
    }

}