class DummyClass_98592 {
    @Test
    public void test_issue_397_3() {
        int expect = 1 / 1 + 10 * (1400 - 1400) / 400;
        Object val = El.eval("1/1+10*(1400-1400)/400");
        assertEquals(expect, val);
    }

}