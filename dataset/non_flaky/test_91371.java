class DummyClass_91371 {
    @Test
    public void testGetcommand(){
        NodeID src = new NodeID(new byte[]{6,5,5,4,4,3});
        NodeID dst = new NodeID(new byte[]{2,2,2,4,4,4});
        byte[] payload = new byte[]{0x40,0x01,0x00}; // Traciton Management Reply message
        TractionControlReplyMessage t = new TractionControlReplyMessage(src,dst,payload);
        Assert.assertEquals("command",0x40,t.getCmd());
    }

}