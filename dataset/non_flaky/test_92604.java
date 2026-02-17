class DummyClass_92604 {
    @Test
    public void testImmutablesSimpleGenericRoundTrip() throws IOException {
        Key<Account> original = ImmutableKey.<Account>builder()
                .id(ImmutableAccount.builder()
                        .id(1L)
                        .name("foo")
                        .build())
                .build();
        String json = MAPPER.writeValueAsString(original);
        Key<Account> deserialized = MAPPER.readValue(json, new TypeReference<Key<Account>>() {});
        assertEquals(original, deserialized);
    }

}