class DummyClass_53186 {
    @Test
    public void deserializeTest() {
        UserInfoResponse response = JsonUtils.readValue(json, UserInfoResponse.class);
        assertEquals("olds@vmware.com", response.getEmail());
        assertEquals("Dale", response.getGivenName());
        assertEquals("Olds", response.getFamilyName());
        assertEquals("Dale Olds", response.getFullName());
        assertEquals("8505551234", response.getPhoneNumber());
        assertEquals("12345", response.getUserId());
        assertEquals("12345", response.getSub());
        assertEquals("olds", response.getUserName());
        assertEquals(true, response.isEmailVerified());

        assertThat(
            response.getUserAttributes().get("Key 1"),
            hasItems(CoreMatchers.is("Val 11"), CoreMatchers.is("Val 12"))
        );
        assertThat(
            response.getUserAttributes().get("Key 2"),
            hasItems(CoreMatchers.is("Val 21"), CoreMatchers.is("Val 22"))
        );

        assertThat(
            response.getRoles(),
            hasItems(
                CoreMatchers.is("role12"),
                CoreMatchers.is("role54"),
                CoreMatchers.is("role134"),
                CoreMatchers.is("role812")
            )
        );
        assertEquals(Long.valueOf(1000L), response.previousLogonSuccess);
    }

}