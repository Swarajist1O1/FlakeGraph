class DummyClass_148892 {
    @Test
    public void testToAttachment() {
        Attachment attachment = getCard().toAttachment();
        Assert.assertNotNull(attachment);
        Assert.assertEquals("application/vnd.microsoft.card.thumbnail", attachment.getContentType());
    }

}