class DummyClass_92606 {
    @Test
    public void testImmutablesMultipleTypeParametersRoundTrip() throws IOException {
        Entry<Key<Account>, Account> original = ImmutableEntry.<Key<Account>, Account>builder()
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
        String json = MAPPER.writeValueAsString(original);
        Entry<Key<Account>, Account> deserialized = MAPPER.readValue(
                json, new TypeReference<Entry<Key<Account>, Account>>() {});
        assertEquals(original, deserialized);
    }

}