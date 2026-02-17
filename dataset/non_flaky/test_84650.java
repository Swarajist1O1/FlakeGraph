class DummyClass_84650 {
    @Test
    public void testStatWhenPathDoesNotExist() throws IOException, InterruptedException, MalformedCommandException {
        final ZooKeeper zk = createClient();
        ZooKeeperMain main = new ZooKeeperMain(zk);
        String cmdstring = "stat /invalidPath";
        main.cl.parseCommand(cmdstring);
        try {
            main.processZKCmd(main.cl);
            fail("As Node does not exist, command should fail by throwing No Node Exception.");
        } catch (CliException e) {
            assertEquals("Node does not exist: /invalidPath", e.getMessage());
        }
    }

}