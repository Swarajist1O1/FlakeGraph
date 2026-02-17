class DummyClass_122575 {
    @Test
    public void testCompatibility() throws IOException {
        StoredInteger storedInteger = new StoredInteger(path);
        assertFalse(storedBoolean.value());

        storedInteger.write(context, 1);
        assertTrue(storedBoolean.value());

        storedInteger.write(context, 2);
        assertTrue(storedBoolean.value());

        storedInteger.write(context, 0);
        assertFalse(storedBoolean.value());

        Files.delete(path);
        assertFalse(storedBoolean.value());
    }

}