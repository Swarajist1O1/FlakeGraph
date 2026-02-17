class DummyClass_38251 {
    @Test
    public void testKeyAlreadyExists() {
        // Test that it does not throw some random exceptions
        putTestDataForSingleTimestamp();
        try {
            putTestDataForSingleTimestamp();
            // Legal
        } catch (KeyAlreadyExistsException e) {
            Assert.fail("Must not throw when overwriting with same value!");
        }

        keyValueService.putWithTimestamps(
                TEST_TABLE,
                ImmutableMultimap.of(
                        Cell.create(row0, column0),
                        Value.create(value00, TEST_TIMESTAMP + 1)));
        try {
            keyValueService.putWithTimestamps(
                    TEST_TABLE,
                    ImmutableMultimap.of(
                            Cell.create(row0, column0),
                            Value.create(value00, TEST_TIMESTAMP + 1)));
            // Legal
        } catch (KeyAlreadyExistsException e) {
            Assert.fail("Must not throw when overwriting with same value!");
        }

        try {
            keyValueService.putWithTimestamps(TEST_TABLE, ImmutableMultimap.of(Cell.create(row0, column0), Value.create(value01, TEST_TIMESTAMP + 1)));
            // Legal
        } catch (KeyAlreadyExistsException e) {
            // Legal
        }

        // The first try might not throw as putUnlessExists must only be exclusive with other putUnlessExists.
        try {
            keyValueService.putUnlessExists(TEST_TABLE, ImmutableMap.of(Cell.create(row0, column0), value00));
            // Legal
        } catch (KeyAlreadyExistsException e) {
            // Legal
        }

        try {
            keyValueService.putUnlessExists(TEST_TABLE, ImmutableMap.of(Cell.create(row0, column0), value00));
            Assert.fail("putUnlessExists must throw when overwriting the same cell!");
        } catch (KeyAlreadyExistsException e) {
            // Legal
        }
    }

}