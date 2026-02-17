class DummyClass_135011 {
    @Test
    public void testExtractHandlerType() {

        Map<Class<?>, Boolean>  types = ClassUtils.getHandlerTypes(FinalIm.class);
        Assert.assertEquals(1, types.size());
        Assert.assertTrue(types.containsKey(ByteBuffer.class));

        types = ClassUtils.getHandlerTypes(ByteBufferFromSuperClassEncoder.class);
        Assert.assertEquals(1, types.size());
        Assert.assertTrue(types.containsKey(ByteBuffer.class));

        types = ClassUtils.getHandlerTypes(MessageHandlerImpl.class);
        Assert.assertEquals(1, types.size());
        Assert.assertTrue(types.containsKey(ByteBuffer.class));
        Assert.assertFalse(types.get(ByteBuffer.class));

        types = ClassUtils.getHandlerTypes(AsyncMessageHandlerImpl.class);
        Assert.assertEquals(1, types.size());
        Assert.assertTrue(types.containsKey(ByteBuffer.class));
        Assert.assertTrue(types.get(ByteBuffer.class));

        types = ClassUtils.getHandlerTypes(ComplexMessageHandlerImpl.class);
        Assert.assertEquals(2, types.size());
        Assert.assertTrue(types.containsKey(ByteBuffer.class));
        Assert.assertFalse(types.get(ByteBuffer.class));
        Assert.assertTrue(types.containsKey(String.class));
        Assert.assertTrue(types.get(String.class));
        Assert.assertFalse(types.containsKey(byte[].class));

    }

}