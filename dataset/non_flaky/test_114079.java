class DummyClass_114079 {
    @Test
    public void joinNames_correctlyJoinsEmpty() {
        Map<String, String> names1 = new HashMap<>();
        names1.put("one", "1");
        names1.put("two", "2");
        Map<String, String> names2 = new HashMap<>();
        names2.put("three", "3");
        names2.put("four", "4");

        Map<String, String> result = Expression.joinNames(names1, null);
        assertThat(result.size(), is(2));
        assertThat(result, hasEntry("one", "1"));
        assertThat(result, hasEntry("two", "2"));

        result = Expression.joinNames(null, names2);
        assertThat(result.size(), is(2));
        assertThat(result, hasEntry("three", "3"));
        assertThat(result, hasEntry("four", "4"));

        result = Expression.joinNames(names1, Collections.emptyMap());
        assertThat(result.size(), is(2));
        assertThat(result, hasEntry("one", "1"));
        assertThat(result, hasEntry("two", "2"));

        result = Expression.joinNames(Collections.emptyMap(), names2);
        assertThat(result.size(), is(2));
        assertThat(result, hasEntry("three", "3"));
        assertThat(result, hasEntry("four", "4"));
    }

}