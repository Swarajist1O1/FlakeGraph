class DummyClass_91406 {
    @Test
    public void testCTor() {
        NodeID id1 = new NodeID(new byte[]{1, 1, 0, 0, 0, 4});
        NodeID id2 = new NodeID(new byte[]{1, 1, 0, 0, 4, 4});
        StreamInitiateReplyMessage t = new StreamInitiateReplyMessage(id1,id2,0,(byte)0x00,(byte)0x00);
        Assert.assertNotNull("exists",t);
    }

}