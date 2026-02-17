class DummyClass_88864 {
    @Test
    public void testPartition() {
        doReturn(0).when(affinityFunction).partition(eq(42));

        int part = wrapper.partition(42);

        assertEquals(42, part);
        verify(affinityFunction, times(0)).partition(any());
    }

}