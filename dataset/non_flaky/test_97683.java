class DummyClass_97683 {
    @Test
    public void testJacksonSerialization() throws JsonProcessingException {
        final ReadOnlyWriteOnlyUser user = new ReadOnlyWriteOnlyUser();
        user.name = "name";
        user.id1 = "id1";
        user._id2 = "id2";
        user.password1 = "password1";
        user.password2 = "password2";
        final String json = new ObjectMapper().writeValueAsString(user);
        Assert.assertTrue(json.contains("id1"));
        Assert.assertTrue(json.contains("id2"));
        Assert.assertTrue(!json.contains("password1"));
        Assert.assertTrue(!json.contains("password2"));
    }

}