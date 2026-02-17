class DummyClass_162398 {
    @Test
    public void checkOutput() {
        String listing = toStringConsumer.toUtf8String();

        assertTrue("Directory listing contains expected /etc content", listing.contains("hostname"));
        assertTrue("Directory listing contains expected /etc content", listing.contains("init.d"));
        assertTrue("Directory listing contains expected /etc content", listing.contains("passwd"));
    }

}