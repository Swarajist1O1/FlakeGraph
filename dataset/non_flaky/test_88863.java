class DummyClass_88863 {
    @Test
    public void testPartitions() {
        doReturn(42).when(affinityFunction).partitions();

        int partitions = wrapper.partitions();

        assertEquals(42, partitions);
        verify(affinityFunction, times(1)).partitions();
    }

}