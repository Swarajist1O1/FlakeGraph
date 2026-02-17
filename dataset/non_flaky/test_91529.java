class DummyClass_91529 {
    @Test
    public void testIsPrecisionApplicable() {
        assertFalse(SqlUtil.isPrecisionApplicable("boolean"));
        assertTrue(SqlUtil.isPrecisionApplicable("varchar"));
    }

}