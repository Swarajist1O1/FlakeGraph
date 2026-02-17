class DummyClass_84590 {
    @Test
    public void testBasicUsageOfApisAndRecipes() throws Exception {
        try (TestingServer server = new TestingServer()) {
            doTest(server.getConnectString());
        }
    }

}