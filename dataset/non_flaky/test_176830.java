class DummyClass_176830 {
    @Test
    public void readMultipleEntities() throws Exception {
        String ldif = "dn: dc=example,dc=com\n" +
            "objectClass: domain\n" +
            "objectClass: top\n" +
            "dc: example\n" +
            "\n" +
            "dn: ou=Users,dc=example,dc=com\n" +
            "objectClass: organizationalUnit\n" +
            "objectClass: top\n" +
            "ou: Users\n";

        Map<String, Attributes> entities = LDIFUtils.read(new ByteArrayInputStream(ldif.getBytes()));
        assertThat(entities.size(), is(2));
        assertThat(entities.containsKey("dc=example,dc=com"), is(true));
        assertThat(entities.containsKey("ou=Users,dc=example,dc=com"), is(true));
    }

}