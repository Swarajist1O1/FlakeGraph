class DummyClass_176831 {
    @Test
    public void readWithLineBreaks() throws Exception {
        String ldif = "dn: dc=example,dc=com\n" +
            "objectClass: top\n" +
            "test: this is\n" +
            " multi-line text\n" +
            "dc: example\n";
        Map<String, Attributes> entities = LDIFUtils.read(new ByteArrayInputStream(ldif.getBytes()));
        assertThat(entities.size(), is(1));
        Attributes entity = entities.get("dc=example,dc=com");
        assertThat(entity.get("test").get(), is("this is multi-line text"));

    }

}