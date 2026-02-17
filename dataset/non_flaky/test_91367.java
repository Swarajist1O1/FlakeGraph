class DummyClass_91367 {
    @Test
    public void testCTor() {
        NodeID id1 = new NodeID(new byte[]{1, 1, 0, 0, 0, 4});
        NodeID id2 = new NodeID(new byte[]{1, 1, 0, 0, 4, 4});
        int data[]={0x00,0x00,0x00,0x00};
        StreamDataSendMessage t = new StreamDataSendMessage(id1,id2,data);
        Assert.assertNotNull("exists",t);
    }

}