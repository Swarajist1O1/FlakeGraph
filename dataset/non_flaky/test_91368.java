class DummyClass_91368 {
    @Test
    public void testCTor() {
        NodeID src = new NodeID(new byte[]{6,5,5,4,4,3});
        NodeID dst = new NodeID(new byte[]{2,2,2,4,4,4});
        byte[] payload = new byte[]{0x40,0x01,0x00}; // Traciton Management Reply message
        TractionProxyReplyMessage t = new TractionProxyReplyMessage(src,dst,payload);
        Assert.assertNotNull("exists",t);
    }

}