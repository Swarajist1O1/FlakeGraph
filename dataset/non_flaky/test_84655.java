class DummyClass_84655 {
    @Test
    public void testParseWithMultipleQuotes() throws Exception {
        final ZooKeeper zk = createClient();
        ZooKeeperMain zkMain = new ZooKeeperMain(zk);
        String cmdstring = "create /node '' ''";
        zkMain.cl.parseCommand(cmdstring);
        assertEquals(zkMain.cl.getNumArguments(), 4, "expected 5 arguments");
        assertEquals(zkMain.cl.getCmdArgument(0), "create", "create is not taken as first argument");
        assertEquals(zkMain.cl.getCmdArgument(1), "/node", "/node is not taken as second argument");
        assertEquals(zkMain.cl.getCmdArgument(2), "", "empty string is not taken as third argument");
        assertEquals(zkMain.cl.getCmdArgument(3), "", "empty string is not taken as fourth argument");
    }

}