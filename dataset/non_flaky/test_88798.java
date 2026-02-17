class DummyClass_88798 {
    @Test
    public void testStart() {
        wrapper.start(Arrays.asList(1, 2, 3));

        verify(delegate).start(eq(Arrays.asList("1", "2", "3")));
    }

}