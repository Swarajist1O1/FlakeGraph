class DummyClass_148888 {
    @Test
    public void testToAttachment() {
        Attachment attachment = getCard().toAttachment();
        Assert.assertNotNull(attachment);
        Assert.assertEquals("application/vnd.microsoft.card.oauth", attachment.getContentType());
    }

}