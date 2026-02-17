class DummyClass_88853 {
    @Test
    public void testCompute() {
        doReturn(42).when(dataset).compute(any(IgniteBiFunction.class), any(), any());

        Integer res = (Integer) wrapper.compute(mock(IgniteBiFunction.class), mock(IgniteBinaryOperator.class),
            null);

        assertEquals(42, res.intValue());

        verify(dataset, times(1)).compute(any(IgniteBiFunction.class), any(), any());
    }

}