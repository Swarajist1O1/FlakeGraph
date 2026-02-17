class DummyClass_26850 {
    @Test
    public void testCompositeBindingUpdate() throws Exception {
        final KernelServices services = createKernelServicesBuilder(createAdditionalInitialization()).setSubsystemXml(getSubsystemXml()).build();
        // updates binding 'a' through composite op
        // binding-type used is lookup, op should succeed even if lookup value is set by a followup step
        final ModelNode addr = Operations.createAddress(ModelDescriptionConstants.SUBSYSTEM, NamingExtension.SUBSYSTEM_NAME, NamingSubsystemModel.BINDING, "java:global/a");
        final ModelNode compositeOp = Operations.CompositeOperationBuilder.create()
                .addStep(Operations.createWriteAttributeOperation(addr, NamingSubsystemModel.BINDING_TYPE, NamingSubsystemModel.LOOKUP))
                .addStep(Operations.createWriteAttributeOperation(addr, NamingSubsystemModel.LOOKUP, "java:global/b"))
                .build().getOperation();
        ModelTestUtils.checkOutcome(services.executeOperation(compositeOp));
    }

}