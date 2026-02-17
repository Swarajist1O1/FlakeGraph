class DummyClass_92601 {
    @Test
    public void testImmutablesSimpleDeserialization() throws IOException {
        Account expected = ImmutableAccount.builder()
                .id(1L)
                .name("foo")
                .build();
        Account actual = MAPPER.readValue("{\"id\": 1,\"name\":\"foo\"}", Account.class);
        assertEquals(expected, actual);
    }

}