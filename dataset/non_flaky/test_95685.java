class DummyClass_95685 {
    @Test
    public void test() {
        expectedException.expect(AssertionError.class);

        assertTrue(MyFeatures.F1.isActive());
    }

}