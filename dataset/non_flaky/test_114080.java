class DummyClass_114080 {
    @Test
    public void joinNames_conflictingKey() {
        Map<String, String> names1 = new HashMap<>();
        names1.put("one", "1");
        names1.put("two", "2");
        Map<String, String> names2 = new HashMap<>();
        names2.put("three", "3");
        names2.put("two", "4");

        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("two");
        Expression.joinNames(names1, names2);
    }

}