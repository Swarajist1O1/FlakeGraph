class DummyClass_91530 {
    @Test
    public void testIsScaleApplicable() {
        assertFalse(SqlUtil.isScaleApplicable("varchar"));
        assertTrue(SqlUtil.isScaleApplicable("decimal"));
    }

}