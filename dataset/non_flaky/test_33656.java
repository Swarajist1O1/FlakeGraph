class DummyClass_33656 {
    @Test
    public void testWriteTo() {
        final String user = target("user").request().accept("application/json").get(String.class);
        // {"createdOn":1412036891919,"id":12345,"name":"smallnest"}]
        assertTrue(user.indexOf("createdOn") > 0);
        assertTrue(user.indexOf("\"id\":12345") > 0);
        assertTrue(user.indexOf("\"name\":\"smallnest\"") > 0);
    }

}