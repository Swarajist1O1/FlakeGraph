class DummyClass_38256 {
    @Test
    public void testCannotModifyValuesAfterWrite() {
        byte[] data = new byte[1];
        byte[] unmodifiedData = Arrays.copyOf(data, data.length);

        Cell cell = Cell.create(row0, column0);

        Value val = Value.create(data, TEST_TIMESTAMP + 1);

        keyValueService.putWithTimestamps(TEST_TABLE, ImmutableMultimap.of(cell, val));

        data[0] = (byte) 50;

        assertThat(keyValueService.get(TEST_TABLE, ImmutableMap.of(cell, TEST_TIMESTAMP + 3)).get(cell).getContents(),
                is(unmodifiedData));

        keyValueService.delete(TEST_TABLE, ImmutableMultimap.of(cell, TEST_TIMESTAMP + 1));
    }

}