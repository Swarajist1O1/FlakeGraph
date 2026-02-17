class DummyClass_92603 {
    @Test
    public void testImmutablesSimpleGenericDeserialization() throws IOException {
        Key<Account> expected = ImmutableKey.<Account>builder()
                .id(ImmutableAccount.builder()
                        .id(1L)
                        .name("foo")
                        .build())
                .build();
        Key<Account> actual = MAPPER.readValue(
                "{\"id\":{\"id\": 1,\"name\":\"foo\"}}",
                new TypeReference<Key<Account>>() {});
        assertEquals(expected, actual);
    }

}