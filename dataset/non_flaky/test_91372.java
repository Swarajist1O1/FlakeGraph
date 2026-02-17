class DummyClass_91372 {
    @Test
    public void testCTor() {
        NodeID id1 = new NodeID(new byte[]{1, 1, 0, 0, 0, 4});
        NodeID id2 = new NodeID(new byte[]{1, 1, 0, 0, 4, 4});
        StreamDataCompleteMessage t = new StreamDataCompleteMessage(id1,id2,(byte)0x00,(byte)0x00);
        Assert.assertNotNull("exists",t);
    }

}