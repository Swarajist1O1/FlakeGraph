class DummyClass_91399 {
    @Test
    public void testCTor() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        NodeID nodeID = new NodeID(new byte[]{1,2,3,4,5,6});
        EventID eventID = new EventID(new byte[]{1,0,0,0,0,0,1,0});
        Connection testConnection = new AbstractConnection(){
            public void put(Message msg, Connection sender) {
            }

}