class DummyClass_177193 {
    @Test
    public void notFound() {
        throw new AssumptionViolatedException(
                "Armeria yields 'get /*' as a span name for a non-existent mapping.");
    }

}