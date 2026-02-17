class DummyClass_33658 {
    @Test
    public void testReadFrom() {
        final User user = target("user").request().accept("application/json").get(User.class);
        assertNotNull(user);
        assertNotNull(user.getCreatedOn());
        assertEquals(user.getId().longValue(), 12345L);
        assertEquals(user.getName(), "smallnest");
    }

}