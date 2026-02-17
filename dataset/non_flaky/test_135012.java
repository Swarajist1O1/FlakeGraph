class DummyClass_135012 {
    @Test
    public void testExtractEncoderType() {
        Class<?> clazz = ClassUtils.getEncoderType(BinaryEncoder.class);
        Assert.assertEquals(String.class, clazz);

        Class<?> clazz2 = ClassUtils.getEncoderType(TextEncoder.class);
        Assert.assertEquals(String.class, clazz2);

        Class<?> clazz3 = ClassUtils.getEncoderType(TextStreamEncoder.class);
        Assert.assertEquals(String.class, clazz3);

        Class<?> clazz4 = ClassUtils.getEncoderType(BinaryStreamEncoder.class);
        Assert.assertEquals(String.class, clazz4);
    }

}