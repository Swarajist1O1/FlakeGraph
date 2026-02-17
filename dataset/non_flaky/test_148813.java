class DummyClass_148813 {
    @Test
    public void DeserializeActivityWithDifferentTimeZone() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        Activity activity = objectMapper.readValue(this.serializedActivityWithDifferentTimeZone, Activity.class);

        Assert.assertNotNull(activity.getTimestamp());
        Assert.assertEquals("b18a1c99-7a29-4801-ac0c-579f2c36d52c", activity.getConversation().getId());
        Assert.assertNotNull(activity.getValue());
    }

}