class DummyClass_84654 {
    @Test
    public void testParseWithEmptyQuotes() throws Exception {
        final ZooKeeper zk = createClient();
        ZooKeeperMain zkMain = new ZooKeeperMain(zk);
        String cmdstring = "create /node ''";
        zkMain.cl.parseCommand(cmdstring);
        assertEquals(zkMain.cl.getNumArguments(), 3, "empty quotes should produce arguments");
        assertEquals(zkMain.cl.getCmdArgument(0), "create", "create is not taken as first argument");
        assertEquals(zkMain.cl.getCmdArgument(1), "/node", "/node is not taken as second argument");
        assertEquals(zkMain.cl.getCmdArgument(2), "", "empty string is not taken as third argument");
    }

}