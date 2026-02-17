class DummyClass_176829 {
    @Test
    public void readSingleEntity() throws Exception {
        String ldif = "dn: dc=example,dc=com\n" +
            "objectClass: domain\n" +
            "objectClass: top\n" +
            "dc: example\n";
        Map<String, Attributes> entities = LDIFUtils.read(new ByteArrayInputStream(ldif.getBytes()));
        assertThat(entities.size(), is(1));
        String dn = "dc=example,dc=com";
        Attributes entity = entities.get(dn);
        assertThat(entity, notNullValue());

        assertThat(entity.get("dn"), nullValue());
        assertThat(entity.get("dc").get(), is("example"));
        assertThat(entity.get("objectClass").contains("domain"), is(true));
        assertThat(entity.get("objectClass").contains("top"), is(true));
    }

}