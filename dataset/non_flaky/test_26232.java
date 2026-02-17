class DummyClass_26232 {
    @Test
    public void testEqualsContract()
    {
        EqualsVerifier.forClass(RepairResource.class).usingGetClass().verify();
    }

}