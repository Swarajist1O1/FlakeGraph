class DummyClass_91407 {
    @Test
    public void testCTor() {
        NodeID id1 = new NodeID(new byte[]{1, 1, 0, 0, 0, 4});
        NodeID id2 = new NodeID(new byte[]{1, 1, 0, 0, 4, 4});
        DatagramAcknowledgedMessage t = new DatagramAcknowledgedMessage(id1,id2);
        Assert.assertNotNull("exists",t);
    }

}