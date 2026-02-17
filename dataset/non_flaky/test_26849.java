class DummyClass_26849 {
    @Test
    public void testCompositeBindingOps() throws Exception {
        final KernelServices services = createKernelServicesBuilder(createAdditionalInitialization()).setSubsystemXml(getSubsystemXml()).build();
        // add binding 'alookup' through composite op
        // note that a binding-type of 'lookup' requires 'lookup' attr value, which in this case is set by a followup step
        final ModelNode addr = Operations.createAddress(ModelDescriptionConstants.SUBSYSTEM, NamingExtension.SUBSYSTEM_NAME, NamingSubsystemModel.BINDING, "java:global/alookup");
        final ModelNode addOp = Operations.createAddOperation(addr);
        addOp.get(NamingSubsystemModel.BINDING_TYPE).set(NamingSubsystemModel.LOOKUP);
        final ModelNode compositeOp = Operations.CompositeOperationBuilder.create()
                .addStep(addOp)
                .addStep(Operations.createWriteAttributeOperation(addr, NamingSubsystemModel.LOOKUP, "java:global/a"))
                .build().getOperation();
        ModelTestUtils.checkOutcome(services.executeOperation(compositeOp));
    }

}