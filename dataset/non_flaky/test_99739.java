class DummyClass_99739 {
    @Test(expected = RuntimeException.class)
    public void testNoPass()
    {
        fromString("blabla@abc.com:1234");
    }

}