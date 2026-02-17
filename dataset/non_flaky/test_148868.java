class DummyClass_148868 {
    @Test
    public void testToAttachment() {
        Attachment attachment = getCard().toAttachment();
        Assert.assertNotNull(attachment);
        Assert.assertEquals("application/vnd.microsoft.card.receipt", attachment.getContentType());
    }

}