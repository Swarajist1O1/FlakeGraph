class DummyClass_133928 {
@Test(groups = "live")
    public String payloadFromResource(String resource) {
        try {
            return new String(toStringAndClose(getClass().getResourceAsStream(resource)).getBytes(Charsets.UTF_8));
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

}