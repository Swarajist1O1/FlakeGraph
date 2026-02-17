class DummyClass_99740 {
    @Test(expected = RuntimeException.class)
    public void testBadPort()
    {
        fromString("aaa:bbb@abc.com:xyz");
    }

}