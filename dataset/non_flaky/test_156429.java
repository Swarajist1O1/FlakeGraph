class DummyClass_156429 {
    @Test
    public void testNullDate() {
        assertThrows(
                IllegalArgumentException.class,
                () -> DateUtils.getFragmentInMilliseconds((Date) null, Calendar.MILLISECOND));

        assertThrows(
                IllegalArgumentException.class,
                () -> DateUtils.getFragmentInSeconds((Date) null, Calendar.MILLISECOND));

        assertThrows(
                IllegalArgumentException.class,
                () -> DateUtils.getFragmentInMinutes((Date) null, Calendar.MILLISECOND));

        assertThrows(
                IllegalArgumentException.class,
                () -> DateUtils.getFragmentInHours((Date) null, Calendar.MILLISECOND));

        assertThrows(
                IllegalArgumentException.class,
                () -> DateUtils.getFragmentInDays((Date) null, Calendar.MILLISECOND));
    }

}