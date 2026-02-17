class DummyClass_97684 {
    @Test
    public void testJacksonDeserialization() throws JsonProcessingException {
        final String json = "{'name':'name','id1':'id1','id2':'id2','password1':'password1','password2':'password2'}"
                .replace("'", "\"");
        final ReadOnlyWriteOnlyUser user = new ObjectMapper().readValue(json, ReadOnlyWriteOnlyUser.class);
        Assert.assertNull(user.id1);
        Assert.assertNull(user._id2);
        Assert.assertNotNull(user.password1);
        Assert.assertNotNull(user.password2);
    }

}