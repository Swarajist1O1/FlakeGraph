class DummyClass_92602 {
    @Test
    public void testImmutablesSimpleRoundTrip() throws IOException {
        Account original = ImmutableAccount.builder()
                .id(1L)
                .name("foo")
                .build();
        String json = MAPPER.writeValueAsString(original);
        Account deserialized = MAPPER.readValue(json, Account.class);
        assertEquals(original, deserialized);
    }

}