class DummyClass_88851 {
    @Test
    public void testComputeWithCtx2() {
        doReturn(42).when(dataset).computeWithCtx(any(IgniteTriFunction.class), any(), any());

        Integer res = (Integer) wrapper.computeWithCtx(mock(IgniteBiFunction.class), mock(IgniteBinaryOperator.class),
            null);

        assertEquals(42, res.intValue());

        verify(dataset, times(1)).computeWithCtx(any(IgniteTriFunction.class), any(), any());
    }

}