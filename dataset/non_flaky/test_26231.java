class DummyClass_26231 {
    @Test
    public void testRepairResourceEquality()
    {
        RepairResource repairResource = new RepairResource("dc1", "my-resource");
        RepairResource equalRepairResource = new RepairResource("dc1", "my-resource");
        RepairResource repairResourceWithDifferentDc = new RepairResource("dc2", "my-resource");
        RepairResource repairResourceWithDifferentResource = new RepairResource("dc1", "not-my-resource");

        assertThat(repairResource).isEqualTo(equalRepairResource);
        assertThat(repairResource).isNotEqualTo(repairResourceWithDifferentDc);
        assertThat(repairResource).isNotEqualTo(repairResourceWithDifferentResource);
    }

}