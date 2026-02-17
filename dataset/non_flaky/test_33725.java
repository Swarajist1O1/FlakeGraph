class DummyClass_33725 {
    @Test
    public void testBug89() {
        try {
            String s = "{\"a\":Ð·ãâ )_,\"}";
            JSON.parseObject(s);
            fail("Expect JSONException");
        } catch (JSONException e) {
            // good
        }
    }

}