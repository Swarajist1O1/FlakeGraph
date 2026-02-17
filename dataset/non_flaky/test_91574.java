class DummyClass_91574 {
    @Test
    public void testAESEncrypt(){
        String input = "hello world";
        String result = EncryptUtil.encrypt(input);
        Assert.assertEquals("4stv/RRleOtvie/8SLHmXA==", result);
    }

}