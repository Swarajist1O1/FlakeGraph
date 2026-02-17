class DummyClass_122574 {
    @Test
    public void storedBoolean() {
        assertFalse(storedBoolean.value());
        storedBoolean.set(context);
        assertTrue(storedBoolean.value());
        storedBoolean.clear(context);
        assertFalse(storedBoolean.value());
    }

}