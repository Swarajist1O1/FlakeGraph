class DummyClass_77124 {
    @Test
    public void testNonNumeicValues() {
        for (String invalidValue : invalidNumericValues) {
            assertFalse(isNumeric(invalidValue), invalidValue);
        }
    }

}