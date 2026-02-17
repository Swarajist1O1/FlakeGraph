class DummyClass_84589 {
    @Test
    public void testNullName() {
        assertThrows(NullPointerException.class, () -> {
            new ZNodeName(null);
        });
    }

}