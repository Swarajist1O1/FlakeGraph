class DummyClass_91408 {
    @Test
    public void testCTor() {
        NodeID nodeID = new NodeID(new byte[]{1,2,3,4,5,6});
        Connection testConnection = new AbstractConnection(){
            public void put(Message msg, Connection node) {
            }

}