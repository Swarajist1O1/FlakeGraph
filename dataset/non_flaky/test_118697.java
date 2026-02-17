class DummyClass_118697 {
    @Test
    public void testEncodeRset() {
        testEncode(SmtpRequests.rset(), "RSET\r\n");
    }

}