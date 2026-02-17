class DummyClass_92605 {
    @Test
    public void testImmutablesMultipleTypeParametersDeserialization() throws IOException {
        Entry<Key<Account>, Account> expected = ImmutableEntry.<Key<Account>, Account>builder()
                .key(ImmutableKey.<Account>builder()
                        .id(ImmutableAccount.builder()
                                .id(1L)
                                .name("foo")
                                .build())
                        .build())
                .value(ImmutableAccount.builder()
                        .id(2L)
                        .name("bar")
                        .build())
                .build();
        Entry<Key<Account>, Account> actual = MAPPER.readValue(
                "{\"key\":{\"id\":{\"id\": 1,\"name\":\"foo\"}},\"value\":{\"id\":2,\"name\":\"bar\"}}",
                new TypeReference<Entry<Key<Account>, Account>>() {});
        assertEquals(expected, actual);
    }

}