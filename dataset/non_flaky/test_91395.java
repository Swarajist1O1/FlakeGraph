class DummyClass_91395 {
    @Test
    public void testCTor() {
        NodeID id1 = new NodeID(new byte[]{1, 1, 0, 0, 0, 4});
        NodeID id2 = new NodeID(new byte[]{1, 1, 0, 0, 4, 4});
        DatagramRejectedMessage t = new DatagramRejectedMessage(id1,id2,1);
        Assert.assertNotNull("exists",t);
    }

}