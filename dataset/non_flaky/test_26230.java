class DummyClass_26230 {
    @Test
    public void testRepairResource()
    {
        RepairResource repairResource = new RepairResource("dc1", "my-resource");

        assertThat(repairResource.getDataCenter()).isEqualTo("dc1");
        assertThat(repairResource.getResourceName(1)).isEqualTo("RepairResource-my-resource-1");
        assertThat(repairResource.getResourceName(2)).isEqualTo("RepairResource-my-resource-2");
    }

}