class DummyClass_84651 {
    @Test
    public void testParseWithExtraSpaces() throws Exception {
        final ZooKeeper zk = createClient();
        ZooKeeperMain zkMain = new ZooKeeperMain(zk);
        String cmdstring = "      ls       /  ";
        zkMain.cl.parseCommand(cmdstring);
        assertEquals(zkMain.cl.getNumArguments(), 2, "Spaces also considered as characters");
        assertEquals(zkMain.cl.getCmdArgument(0), "ls", "ls is not taken as first argument");
        assertEquals(zkMain.cl.getCmdArgument(1), "/", "/ is not taken as second argument");
    }

}