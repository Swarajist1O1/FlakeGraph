class DummyClass_84653 {
    @Test
    public void testParseWithMixedQuotes() throws Exception {
        final ZooKeeper zk = createClient();
        ZooKeeperMain zkMain = new ZooKeeperMain(zk);
        for (String[] quoteChars : new String[][]{{"'", "\""}, {"\"", "'"}}) {
            String outerQuotes = quoteChars[0];
            String innerQuotes = quoteChars[1];
            String cmdstring = String.format("create /node %1$s%2$squoted data%2$s%1$s", outerQuotes, innerQuotes);
            zkMain.cl.parseCommand(cmdstring);
            assertEquals(zkMain.cl.getNumArguments(), 3, "quotes combine arguments");
            assertEquals(zkMain.cl.getCmdArgument(0), "create", "create is not taken as first argument");
            assertEquals(zkMain.cl.getCmdArgument(1), "/node", "/node is not taken as second argument");
            assertEquals(zkMain.cl.getCmdArgument(2), innerQuotes + "quoted data" + innerQuotes,
                    "quoted data is not taken as third argument");
        }
    }

}