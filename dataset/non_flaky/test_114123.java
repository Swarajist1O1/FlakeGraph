class DummyClass_114123 {
    @Test
    public void localDateTimeAttributeConverterNormalTest() {
        verifyTransform(LocalDateTime.of(0, 1, 1, 0, 0, 0, 0), "0000-01-01T00:00");
    }

}