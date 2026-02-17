class DummyClass_176825 {
    @Test
    public void compare_sameTree() throws Exception {
        assertThat(comparator.compare("cn=root", "cn=b,cn=root"), is(more()));
        assertThat(comparator.compare("cn=a,cn=root", "cn=root"), is(less()));
        assertThat(comparator.compare("cn=a,cn=root", "cn=a,cn=root"), is(same()));
    }

}