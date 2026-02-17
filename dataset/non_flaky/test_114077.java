class DummyClass_114077 {
    @Test
    public void joinExpressions_correctlyJoins() {
        String result = Expression.joinExpressions("one", "two", " AND ");
        assertThat(result, is("(one) AND (two)"));
    }

}