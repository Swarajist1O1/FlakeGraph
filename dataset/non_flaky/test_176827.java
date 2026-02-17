class DummyClass_176827 {
    @Test
    public void sort() throws Exception {
        List<String> dns = new ArrayList<>(Arrays.asList(
            "cn=root",
            "cn=a,cn=root",
            "cn=b,cn=root",
            "cn=c,cn=a,cn=root"
        ));
        Collections.sort(dns, comparator);
        assertThat(dns.get(0), is("cn=root"));
        assertThat(dns.get(1), is("cn=a,cn=root"));
        assertThat(dns.get(2), is("cn=c,cn=a,cn=root"));
        assertThat(dns.get(3), is("cn=b,cn=root"));
    }

}