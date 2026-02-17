class DummyClass_176826 {
    @Test
    public void compare_differentTrees() throws Exception {
        assertThat(comparator.compare("cn=a,cn=root", "cn=b,cn=root"), is(more()));
        assertThat(comparator.compare("cn=b,cn=root", "cn=a,cn=root"), is(less()));
    }

}