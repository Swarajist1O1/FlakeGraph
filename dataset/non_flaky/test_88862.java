class DummyClass_88862 {
    @Test
    public void testReset() {
        wrapper.reset();

        verify(affinityFunction, times(1)).reset();
    }

}