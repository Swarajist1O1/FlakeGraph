class DummyClass_91396 {
    @Test
    public void testCTor() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        MimicNodeStore store = null;
        NodeID nid1 = new NodeID(new byte[]{1,3,3,4,5,6});
        NodeID nid2 = new NodeID(new byte[]{2,3,3,4,5,6});
    
        ProducerIdentifiedMessage pim1 = new ProducerIdentifiedMessage(nid1, 
                                         new EventID(new byte[]{1,0,0,0,0,0,1,0}), EventState.Unknown);
        Connection connection = new AbstractConnection() {
            public void put(Message msg, Connection sender) {
            }

}