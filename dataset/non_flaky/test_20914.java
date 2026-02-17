class DummyClass_20914 {
    @Test
    public void testAvg() throws Exception {
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += i;
        }
        Assert.assertEquals((sum / 100.0D), m.avg(), 0.0D);
    }

}