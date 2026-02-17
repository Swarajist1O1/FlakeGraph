class DummyClass_114076 {
    @Test
    public void join_correctlyWrapsExpressions() {
        Expression expression1 = Expression.builder().expression("one").build();
        Expression expression2 = Expression.builder().expression("two").build();
        Expression expression3 = Expression.builder().expression("three").build();

        Expression coalescedExpression = Expression.join(Expression.join(expression1, expression2, " AND "),
                                                         expression3, " AND ");

        String expectedExpression = "((one) AND (two)) AND (three)";
        assertThat(coalescedExpression.expression(), is(expectedExpression));
    }

}