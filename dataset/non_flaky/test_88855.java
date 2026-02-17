class DummyClass_88855 {
    @Test
    public void testClose() throws Exception {
        wrapper.close();

        verify(dataset, times(1)).close();
    }

}