class DummyClass_159578 {
    @Test
    public void nativeExceptions() {
        long maxExceptionNumber = TestUtil.getMaxExceptionNumber();
        for (long i = 0; i < maxExceptionNumber; i++) {
            String expect = TestUtil.getExpectedMessage(i);
            try {
                TestUtil.testThrowExceptions(i);
            } catch (Throwable throwable) {
                assertEquals("Exception kind: " + i, expect, throwable.toString());
            }
        }
    }

}