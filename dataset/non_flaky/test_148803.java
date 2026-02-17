class DummyClass_148803 {
    @Test
    public void testToAttachment() {
        Attachment attachment = getCard().toAttachment();
        Assert.assertNotNull(attachment);
        Assert.assertEquals("application/vnd.microsoft.card.hero", attachment.getContentType());
    }

}