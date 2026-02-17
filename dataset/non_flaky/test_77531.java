class DummyClass_77531 {
    @Test
    public void testReentrantRuleStartsApplicationOnlyOnce() throws Throwable {
        @SuppressWarnings("deprecation")
        DropwizardAppRule<TestConfiguration> dropwizardAppRule = new DropwizardAppRule<>(testSupport);

        RuleChain.outerRule(dropwizardAppRule)
            .around(dropwizardAppRule) // recursive
            .apply(statement, description)
            .evaluate();

        InOrder inOrder = inOrder(testSupport, statement, description);
        inOrder.verify(testSupport, times(1)).before();
        inOrder.verify(statement).evaluate();
        inOrder.verify(testSupport, times(1)).after();
        inOrder.verifyNoMoreInteractions();
    }

}