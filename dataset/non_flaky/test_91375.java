class DummyClass_91375 {
    @Test
    public void testCTor() {
        NodeID nodeID = new NodeID(new byte[]{1,2,3,4,5,6});
        ScatterGather sg = new ScatterGather();
        EventID eid = new EventID(new byte[]{1,2,3,4,5,6,7,8});
        Connection testConnection = new AbstractConnection(){
            public void put(Message msg, Connection node) {
            }

}