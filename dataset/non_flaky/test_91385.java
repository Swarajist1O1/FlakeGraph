class DummyClass_91385 {
    @Test
    public void testCTor() {
        NodeID nid = new NodeID(new byte[]{1,2,3,4,5,6});
        EventID eid = new EventID(new byte[]{1,2,3,4,5,6,7,8});
        Connection testConnection = new AbstractConnection(){
            public void put(Message msg, Connection node) {
            }

}