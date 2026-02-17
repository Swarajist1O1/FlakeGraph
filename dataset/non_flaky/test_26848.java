class DummyClass_26848 {
    @Test
    public void testOnlyExternalContextAllowsCache() throws Exception {
        KernelServices services = createKernelServicesBuilder(AdditionalInitialization.MANAGEMENT)
                .build();
        Assert.assertTrue(services.isSuccessfulBoot());

        List<ModelNode> list = parse(ModelTestUtils.readResource(this.getClass(), "subsystem.xml"));

        for (ModelNode addOp : list) {
            PathAddress addr = PathAddress.pathAddress(addOp.require(ModelDescriptionConstants.OP_ADDR));
            if (addr.size() == 2 && addr.getLastElement().getKey().equals(NamingSubsystemModel.BINDING) && BindingType.forName(addOp.get(NamingBindingResourceDefinition.BINDING_TYPE.getName()).asString()) != BindingType.EXTERNAL_CONTEXT) {
                //Add the cache attribute and make sure it fails
                addOp.get(NamingBindingResourceDefinition.CACHE.getName()).set(true);
                services.executeForFailure(addOp);

                //Remove the cache attribute and make sure it succeeds
                addOp.remove(NamingBindingResourceDefinition.CACHE.getName());
                ModelTestUtils.checkOutcome(services.executeOperation(addOp));

                //Try to write the cache attribute, which should fail
                ModelTestUtils.checkFailed(services.executeOperation(Util.getWriteAttributeOperation(addr, NamingBindingResourceDefinition.CACHE.getName(), new ModelNode(true))));

            } else {
                ModelTestUtils.checkOutcome(services.executeOperation(addOp));
            }
        }


    }

}