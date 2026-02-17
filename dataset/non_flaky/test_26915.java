class DummyClass_26915 {
    @Test
    public void testParseSubsystem() throws Exception {
        //Parse the subsystem xml into operations
        List<ModelNode> operations = super.parse(getSubsystemXml());

        ///Check that we have the expected number of operations
        //log.info("operations: " + operations);
        //log.info("operations.size: " + operations.size());
        Assert.assertEquals(7, operations.size());

        //Check that each operation has the correct content
        ModelNode addSubsystem = operations.get(0);
        Assert.assertEquals(ADD, addSubsystem.get(OP).asString());
        PathAddress addr = PathAddress.pathAddress(addSubsystem.get(OP_ADDR));
        Assert.assertEquals(1, addr.size());
        PathElement element = addr.getElement(0);
        Assert.assertEquals(SUBSYSTEM, element.getKey());
        Assert.assertEquals(MailExtension.SUBSYSTEM_NAME, element.getValue());
    }

}