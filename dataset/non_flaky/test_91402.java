class DummyClass_91402 {
    @Test
    public void testCTor() {
        NodeID nodeID = new NodeID(new byte[]{1,2,3,4,5,6});
        OlcbConnection t = new OlcbConnection(nodeID,"test",5,new OlcbConnection.ConnectionListener(){
            @Override
            public void onConnect(){
            }

}