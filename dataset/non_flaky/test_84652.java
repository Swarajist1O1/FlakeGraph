class DummyClass_84652 {
    @Test
    public void testParseWithQuotes() throws Exception {
        final ZooKeeper zk = createClient();
        ZooKeeperMain zkMain = new ZooKeeperMain(zk);
        for (String quoteChar : new String[]{"'", "\""}) {
            String cmdstring = String.format("create /node %1$squoted data%1$s", quoteChar);
            zkMain.cl.parseCommand(cmdstring);
            assertEquals(zkMain.cl.getNumArguments(), 3, "quotes combine arguments");
            assertEquals(zkMain.cl.getCmdArgument(0), "create", "create is not taken as first argument");
            assertEquals(zkMain.cl.getCmdArgument(1), "/node", "/node is not taken as second argument");
            assertEquals(zkMain.cl.getCmdArgument(2), "quoted data", "quoted data is not taken as third argument");
        }
    }

}