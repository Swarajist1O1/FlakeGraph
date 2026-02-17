class DummyClass_148869 {
    @Test
    public void testToAttachment() {
        Attachment attachment = getCard().toAttachment();
        Assert.assertNotNull(attachment);
        Assert.assertEquals("application/vnd.microsoft.card.signin", attachment.getContentType());
    }

}