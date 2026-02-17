class DummyClass_59616 {
    @Test
    public void test() {
        // ä½¿ç¨@EnableSpringUtilæ³¨è§£å, è½è·åä¸ä¸æ
        Assert.assertNotNull(SpringUtil.getApplicationContext());
        // ä¸ä½¿ç¨æ¶, ä¸ºnull
//        Assert.assertNull(SpringUtil.getApplicationContext());
    }

}